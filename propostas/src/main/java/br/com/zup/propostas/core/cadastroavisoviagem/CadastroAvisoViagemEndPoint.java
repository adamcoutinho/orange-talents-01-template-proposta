package br.com.zup.propostas.core.cadastroavisoviagem;

import br.com.zup.propostas.models.AvisoViagem;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/avisoviagem")
public class CadastroAvisoViagemEndPoint {

    @Autowired
    private EntityManager manager;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao")  @NaoExisteNumeroCartao  String identificadorCartao, @RequestBody @Valid CadastroAvisoViagemFormRequest request, HttpServletRequest httpServletRequest) {

        AvisoViagem avisoViagem = request.toModel(httpServletRequest);




        return ResponseEntity.noContent().build();
    }

}
