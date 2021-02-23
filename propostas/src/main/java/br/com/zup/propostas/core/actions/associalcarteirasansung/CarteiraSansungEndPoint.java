package br.com.zup.propostas.core.actions.associalcarteirasansung;

import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.api.cartoes.SolicitacaoInclusaoCarteiraFormRequest;
import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.CartaoRepository;
import br.com.zup.propostas.core.models.CarteiraSansung;
import br.com.zup.propostas.core.models.CarteiraSansungRepository;
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
@RequestMapping("/sansung")
@Validated
public class CarteiraSansungEndPoint {
    private final Logger logger = LoggerFactory.getLogger(CarteiraSansungEndPoint.class);

    @Autowired
    private CarteiraSansungRepository carteiraSansungRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartoesFeign cartoesFeign;


    @PostMapping("/criar/{identificador-cartao}")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable("identificador-cartao") @NaoExisteNumeroCartao String identificadorCartao, @RequestBody @Valid CarteiraSanungFormRequest request, UriComponentsBuilder uriComponentsBuilder) {
        try {

            Optional<Cartao> cartao = cartaoRepository.findByIdentificadorCartao(identificadorCartao);

            if(carteiraSansungRepository.findByCartaoIdentificadorCartao(identificadorCartao).isPresent()) return ResponseEntity.badRequest().body("já existe carteira cadastrada.");

            cartoesFeign.solicitarCarteiraDigital(cartao.get().getIdentificadorCartao(), new SolicitacaoInclusaoCarteiraFormRequest(request.getEmail(), Carteiras.SANSUNG.name()));

            CarteiraSansung carteiraSansung = new CarteiraSansung(request.getEmail(), cartao.get());

            carteiraSansungRepository.save(carteiraSansung);

            URI location = uriComponentsBuilder
                    .path("sansung/criar/{identificador-cartao}")
                    .buildAndExpand(carteiraSansung.getId())
                    .toUri();

            logger.info("carteira digital sansung pay processada com sucesso.");
            return ResponseEntity.created(location).build();
        } catch (FeignException e) {
            logger.info("carteira digital não processada.");
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
