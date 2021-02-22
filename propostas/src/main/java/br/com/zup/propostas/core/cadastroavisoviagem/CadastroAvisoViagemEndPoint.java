package br.com.zup.propostas.core.cadastroavisoviagem;

import br.com.zup.propostas.models.AvisoViagem;
import br.com.zup.propostas.models.Cartao;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/avisoviagem")
@Validated
public class CadastroAvisoViagemEndPoint {

    @Autowired
    private EntityManager manager;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao")  @NaoExisteNumeroCartao  String identificadorCartao,HttpServletRequest request ,CadastroAvisoViagemFormRequest formRequest , UriComponentsBuilder uriComponentsBuilder) {


        Cartao cartao = manager.createQuery("SELECT c FROM Cartao c WHERE c.identificadorCartao like :id_cartao", Cartao.class)
                .setParameter("id_cartao", identificadorCartao)
                .getSingleResult();


        AvisoViagem avisoViagem = new AvisoViagem(cartao,formRequest.getDestinoViagem(),request.getRemoteAddr(),request.getHeader("User-Agent"),formRequest.getDateTerminoViagem());

        URI location = uriComponentsBuilder
                .path("proposta/criar/")
                .buildAndExpand(avisoViagem.getUuidAvisoViagem())
                .toUri();

        return ResponseEntity.noContent().build();
    }

}
