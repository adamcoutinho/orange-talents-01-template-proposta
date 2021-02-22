package br.com.zup.propostas.schedules;

import br.com.zup.propostas.api.cartoes.CartaoDetailResponse;
import br.com.zup.propostas.api.cartoes.CartaoFormRequest;
import br.com.zup.propostas.api.cartoes.CartoesFeign;
import br.com.zup.propostas.core.proposta.Cartao;
import br.com.zup.propostas.core.proposta.Proposta;
import br.com.zup.propostas.core.proposta.StatusProposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@EnableScheduling
public class VerificaPropostaCartaoAgendamento {
    private final Logger logger = LoggerFactory.getLogger(VerificaPropostaCartaoAgendamento.class);
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;
    private final long TEMPO_CHAMADA = 1000 * 20;

    @Autowired
    private EntityManager manager;

    @Autowired
    private CartoesFeign cartoesFeign;


//    @Scheduled(fixedDelay = 60*60*1000)
    @Scheduled(fixedDelay = 6000)
    @Transactional
    public void consultar() {
        try {
            List<Proposta> propostas = this.manager
                    .createQuery("SELECT p FROM Proposta p where p.status like :status   ORDER BY p.id desc  ", Proposta.class)
                    .setParameter("status", StatusProposta.ELEGIVEL)
                    .getResultList();

            for (Proposta proposta : propostas) {
                if (proposta.elegivel() && proposta.naoPossuiNumeroCartao()) {
                    CartaoDetailResponse response = this.cartoesFeign.gerarCartao(new CartaoFormRequest(proposta));
                    proposta.adicionarIdCartao(response);
                    this.manager.merge(proposta);
                    Cartao cartao = new Cartao(proposta.getIdCartao());
                    this.manager.persist(cartao);
                    logger.info("proposta gerada");
                }
            }
        } catch (FeignException e) {
                logger.error("serviço da api fora do ar.");
        }
    }
}
