package br.com.zup.propostas.core.actions.associarcarteirapaypal;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.api.cartoes.SolicitacaoInclusaoCarteiraFormRequest;
import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.CartaoRepository;
import br.com.zup.propostas.core.models.CarteiraPaypal;
import br.com.zup.propostas.core.models.CarteiraPaypalRepository;
import br.com.zup.propostas.shared.Carteiras;
import br.com.zup.propostas.shared.NaoExisteNumeroCartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/paypal")
@Validated
public class CarteiraPaypalEndPoint {

    private final Logger logger = LoggerFactory.getLogger(CarteiraPaypalEndPoint.class);

    @Autowired
    private CarteiraPaypalRepository carteiraPaypalRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartoesFeign cartoesFeign;

    @PostMapping("/criar/{identificador-cartao}")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao") @NaoExisteNumeroCartao String identificadorCartao, @RequestBody @Valid CarteiraPaypalFormRequest request, UriComponentsBuilder uriComponentsBuilder) {
        try {

            Optional<Cartao> cartao = cartaoRepository.findByIdentificadorCartao(identificadorCartao);

            if(carteiraPaypalRepository.findByCartaoIdentificadorCartao(identificadorCartao).isPresent()) return ResponseEntity.badRequest().body("já existe carteira cadastrada.");

            cartoesFeign.solicitarCarteiraDigital(cartao.get().getIdentificadorCartao(), new SolicitacaoInclusaoCarteiraFormRequest(request.getEmail(), Carteiras.PAYPAL.name()));

            CarteiraPaypal carteiraPaypal = new CarteiraPaypal(request.getEmail(), cartao.get());

            this.carteiraPaypalRepository.save(carteiraPaypal);

            URI location = uriComponentsBuilder
                    .path("paypal/criar/{identificador-cartao}")
                    .buildAndExpand(carteiraPaypal.getId())
                    .toUri();

            logger.info("carteira digital paypal processadaa com sucesso.");
            return ResponseEntity.created(location).build();
        }catch (FeignException e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
