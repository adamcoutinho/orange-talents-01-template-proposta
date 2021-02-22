package br.com.zup.propostas.core.bloqueiocartao;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.api.cartoes.ResultadoBloqueioDetailResponse;
import br.com.zup.propostas.api.cartoes.SolicitacaoBloqueioFormRequest;
import br.com.zup.propostas.core.proposta.Cartao;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import feign.FeignException;
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

@RestController
@RequestMapping("/cartao")
@Validated
public class BloqueioCartaoEndPoint {

    @Autowired
    private EntityManager manager;

    @Autowired
    private CartoesFeign cartoesFeign;

    @PostMapping("/{identificador-cartao}/bloqueio")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao")   @NaoExisteNumeroCartao String identificadorCartao, HttpServletRequest request , UriComponentsBuilder uriComponentsBuilder) {
        try {

            Cartao cartao = manager.createQuery("SELECT c FROM Cartao c WHERE c.identificadorCartao like :id_cartao", Cartao.class)
                    .setParameter("id_cartao", identificadorCartao)
                    .getSingleResult();


            if (cartao.bloqueado()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão se encontra bloqueado.");
            }

            URI location = uriComponentsBuilder
                    .path("/cartao/{identificador-cartao}/bloqueio")
                    .buildAndExpand(identificadorCartao).toUri();

            ResultadoBloqueioDetailResponse cartaoDetailResponse = cartoesFeign.solicitarBloqueioCartao(new SolicitacaoBloqueioFormRequest(identificadorCartao));

            BloqueioCartao bloqueioCartao = new BloqueioCartao(
                    identificadorCartao,
                    request.getRemoteAddr(),
                    request.getHeader("User-Agent"),
                    cartao.bloquear()
            );

            this.manager.persist(bloqueioCartao);

            return ResponseEntity.ok().build();
        }catch (FeignException e){
            return ResponseEntity.ok().body("não houver alteração de estado.");
        }
    }

}
