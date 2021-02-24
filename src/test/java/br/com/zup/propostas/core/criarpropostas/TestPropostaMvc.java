package br.com.zup.propostas.core.criarpropostas;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.propostas.core.actions.proposta.PropostaFormRequest;

public class TestPropostaMvc {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@DisplayName("")
	public void criarProposta () {
		
		PropostaFormRequest request = null;
		
		
		
		
		
	}

}
