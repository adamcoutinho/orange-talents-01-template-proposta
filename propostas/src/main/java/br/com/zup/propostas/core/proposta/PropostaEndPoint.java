package br.com.zup.propostas.core.proposta;

import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseFeign;
import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseRequest;
import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaEndPoint {

    private final Logger logger = LoggerFactory.getLogger(PropostaEndPoint.class);

    @Autowired
    private EntityManager manager;

    @Autowired
    private SolicitacaoAnaliseFeign solicitacaoAnaliseFeign;


    @GetMapping("/")
    public ResponseEntity<?> listarPropostas(){
        List<Proposta> propostas = this.manager
                .createQuery("SELECT p FROM Proposta p", Proposta.class)
                .getResultList();
        if(propostas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("não possui dados para essa consulta.");
        }
        return ResponseEntity.ok().body(propostas);
    }

    @GetMapping("/{identificador-proposta}")
    public ResponseEntity<?> consultarProposta(@PathVariable("identificador-proposta")  String identificador) {
        try {
            Proposta proposta = this.manager
                    .createQuery("SELECT p FROM Proposta p  WHERE p.uuidProposta like :identificador", Proposta.class)
                    .setParameter("identificador", identificador)
                    .getSingleResult();
            return ResponseEntity.ok().body(proposta);
        }catch (NoResultException e){
            return ResponseEntity.badRequest().body("não existe identificador para essa proposta.");
        }
    }


    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaFormRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Proposta proposta = request.toModel();

        try {

            if (request.existeDocumento(manager)) return ResponseEntity.unprocessableEntity().body("já existe documento cadastrado.");

            SolicitacaoAnaliseResponse solicitacaoAnaliseResponse = solicitacaoAnaliseFeign.analiseProposta(new SolicitacaoAnaliseRequest(proposta));

            proposta.atualizaStatusProposta(solicitacaoAnaliseResponse);

            this.manager.persist(proposta);



            URI location = uriComponentsBuilder
                    .path("proposta/criar/")
                    .buildAndExpand(proposta.getUuidProposta())
                    .toUri();

            logger.info("proposta processada.");
            return ResponseEntity.created(location).build();

        } catch (FeignException e) {
            logger.error("proposta não processada.");
            return ResponseEntity.unprocessableEntity().body(new PropostaDetailResponse(proposta));
        }
    }



}
