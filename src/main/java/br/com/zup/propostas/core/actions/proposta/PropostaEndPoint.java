package br.com.zup.propostas.core.actions.proposta;

import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseFeign;
import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseRequest;
import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseResponse;
import br.com.zup.propostas.core.models.Proposta;
import br.com.zup.propostas.core.models.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaEndPoint {

    private final Logger logger = LoggerFactory.getLogger(PropostaEndPoint.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SolicitacaoAnaliseFeign solicitacaoAnaliseFeign;

    @GetMapping("/")
    public ResponseEntity<?> listarPropostas(){
        List<Proposta> propostas = propostaRepository.findAll();
        if(propostas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não possui dados para essa consulta.");
        }
        return ResponseEntity.ok().body(propostas);
    }

    @GetMapping("/{identificador-proposta}")
    public ResponseEntity<?> consultarProposta(@PathVariable("identificador-proposta")  String identificador) {

            Optional<Proposta> proposta  = propostaRepository.findByUuid(identificador);

            return ResponseEntity.ok().body(proposta.get());

    }


    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaFormRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Proposta proposta = request.toModel();
        try {

            if(propostaRepository.existsBySolicitanteDocumento(request.getSolicitanteFormRequest().getDocumento()))
                return ResponseEntity.unprocessableEntity().body("já existe documento cadastrado.");

            SolicitacaoAnaliseResponse solicitacaoAnaliseResponse = solicitacaoAnaliseFeign.analiseProposta(new SolicitacaoAnaliseRequest(proposta));

            proposta.atualizaStatusProposta(solicitacaoAnaliseResponse);

            this.propostaRepository.save(proposta);

            URI location = uriComponentsBuilder
                    .path("proposta/criar/")
                    .buildAndExpand(proposta.getUuid())
                    .toUri();

            logger.info("proposta efetuada com sucesso.");
            return ResponseEntity.created(location).build();

        } catch (FeignException.UnprocessableEntity e) {
            logger.error("proposta não processada.");
            return ResponseEntity.unprocessableEntity().body(new PropostaDetailResponse(proposta));
        }
    }



}
