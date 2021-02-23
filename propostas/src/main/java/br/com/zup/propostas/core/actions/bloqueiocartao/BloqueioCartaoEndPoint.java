package br.com.zup.propostas.core.actions.bloqueiocartao;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.api.cartoes.ResultadoBloqueioDetailResponse;
import br.com.zup.propostas.api.cartoes.SolicitacaoBloqueioFormRequest;
import br.com.zup.propostas.core.models.BloqueioCartaoRepository;
import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.BloqueioCartao;
import br.com.zup.propostas.core.models.CartaoRepository;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
@Validated
public class BloqueioCartaoEndPoint {
    private final Logger logger = LoggerFactory.getLogger(BloqueioCartaoEndPoint.class);

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @Autowired
    private CartoesFeign cartoesFeign;

    @PostMapping("/{identificador-cartao}/bloqueio")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao")   @NaoExisteNumeroCartao String identificadorCartao, HttpServletRequest request , UriComponentsBuilder uriComponentsBuilder) {
        try {

            Optional<Cartao> cartao = cartaoRepository.findByIdentificadorCartao(identificadorCartao);


            if (cartao.get().bloqueado()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão ja se encontra bloqueado.");
            }

            URI location = uriComponentsBuilder
                    .path("/cartao/{identificador-cartao}/bloqueio")
                    .buildAndExpand(identificadorCartao).toUri();

            ResultadoBloqueioDetailResponse cartaoDetailResponse = cartoesFeign.solicitarBloqueioCartao(identificadorCartao, new SolicitacaoBloqueioFormRequest("string"));


            BloqueioCartao bloqueioCartao = new BloqueioCartao(
                    identificadorCartao,
                    request.getRemoteAddr(),
                    request.getHeader("User-Agent"),
                    cartao.get().bloquear()
            );

            this.bloqueioCartaoRepository.save(bloqueioCartao);
            logger.info("bloqueio de cartão efetuado com sucesso.");
            return ResponseEntity.ok().build();
        }catch (FeignException e){
            logger.error("bloqueio de cartão já existe || não atendeu as regras de bloqueio.");
            return ResponseEntity.ok().body("não houver alteração de estado.");
        }
    }

}
