package br.com.zup.propostas.core.actions.cadastroavisoviagem;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.api.cartoes.ResultadoAvisoViagemDetailResponse;
import br.com.zup.propostas.api.cartoes.SolicitacaoAvisoViagemFormRequest;
import br.com.zup.propostas.core.models.AvisoViagem;
import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.CartaoRepository;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/avisoviagem")
@Validated
public class CadastroAvisoViagemEndPoint {

    @Autowired
    private EntityManager manager;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartoesFeign cartoesFeign;

    @PostMapping("/criar/{identificador-cartao}")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao")  @NaoExisteNumeroCartao  String identificadorCartao, HttpServletRequest request , @RequestBody @Valid  CadastroAvisoViagemFormRequest formRequest , UriComponentsBuilder uriComponentsBuilder) {
        try {

            Optional<Cartao> cartao =cartaoRepository.findByIdentificadorCartao(identificadorCartao);

            cartoesFeign.solicitarAvisoViagem(identificadorCartao,new SolicitacaoAvisoViagemFormRequest(formRequest.getDestinoViagem(), formRequest.getDataTerminoViagem().toString()));

            AvisoViagem avisoViagem = new AvisoViagem(cartao.get(), formRequest.getDestinoViagem(), request.getRemoteAddr(), request.getHeader("User-Agent"), formRequest.getDataTerminoViagem());

            URI location = uriComponentsBuilder
                    .path("proposta/criar/")
                    .buildAndExpand(identificadorCartao)
                    .toUri();

            this.manager.persist(avisoViagem);

            return ResponseEntity.created(location).build();
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não pode amarznenar aviso de viagem.");
        }
    }

}
