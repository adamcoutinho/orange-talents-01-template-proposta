package br.com.zup.propostas.core.actions.biometria;

import br.com.zup.propostas.core.models.Biometria;
import br.com.zup.propostas.core.models.BiometriaRepository;
import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.CartaoRepository;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
@Validated
public class BiometriaEndPoint {
    private final Logger logger = LoggerFactory.getLogger(BiometriaEndPoint.class);

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private VerificaBiometriaValidator biometriaValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        binder.addValidators(this.biometriaValidator);
    }


    @PostMapping("/{identificador-cartao}/criar")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid BiometriaFormRequest request, @PathVariable("identificador-cartao")  @NaoExisteNumeroCartao(message = "número de cartão não existe.") String identificadorCartao, UriComponentsBuilder uriComponentsBuilder ) {

        Optional<Cartao> cartao = this.cartaoRepository.findByIdentificadorCartao(identificadorCartao);

        Biometria biometria = request.toModel(cartao.get());

        biometriaRepository.save(biometria);

        URI uri = uriComponentsBuilder
                .path("/biometria/{identificador-cartao}/criar")
                .buildAndExpand(identificadorCartao)
                .toUri();

        logger.info("biometria efetuada com sucesso.");

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscaBiometria(@PathVariable("id")Long id) {

        if(!biometriaRepository.findById(id).isPresent()) return ResponseEntity.notFound().build();


        return ResponseEntity.ok().body(Base64.getDecoder().decode(biometriaRepository.findById(id).get().getFingerPrint().getBytes()));
    }

}
