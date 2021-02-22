package br.com.zup.propostas.core.biometria;

import br.com.zup.propostas.core.proposta.Cartao;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;

@RestController
@RequestMapping("/biometria")
@Validated
public class BiometriaEndPoint {

    @Autowired
    private EntityManager manager;

    @Autowired
    private VerificaBiometriaValidator biometriaValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        binder.addValidators(this.biometriaValidator);
    }


    @PostMapping("/{identificador-cartao}/criar")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid BiometriaFormRequest request, @PathVariable("identificador-cartao")  @NaoExisteNumeroCartao(message = "número de cartão não existe.") String identificadorCartao, UriComponentsBuilder uriComponentsBuilder ) {

        Biometria biometria = request.toModel(this.manager,identificadorCartao);

        this.manager.persist(biometria);


        URI uri = uriComponentsBuilder
                .path("/biometria/{identificador-cartao}/criar")
                .buildAndExpand(identificadorCartao)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscaBiometria(@PathVariable("id")Long id) {

        Query query = this.manager.createQuery("SELECT b FROM Biometria b WHERE b.id =:id")
                .setParameter("id" , id);
        Biometria biometria  = (Biometria) query.getSingleResult();
        return ResponseEntity.ok().body(Base64.getDecoder().decode(biometria.getFingerPrint().getBytes()));
    }

}
