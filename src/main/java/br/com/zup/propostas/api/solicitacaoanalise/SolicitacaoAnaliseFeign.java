package br.com.zup.propostas.api.solicitacaoanalise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "solicitacao-analise-resource",url = "http://localhost:9999/api/solicitacao" )
public interface SolicitacaoAnaliseFeign {
			
	   @PostMapping
	   SolicitacaoAnaliseResponse analiseProposta(SolicitacaoAnaliseRequest solicitacaoAnalise); 
	 

}
