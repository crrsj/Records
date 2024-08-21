package com.eleicao.testes;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.eleicao.controle.CandidatoControle;
import com.eleicao.dto.CandidatoDto;
import com.eleicao.entidade.Candidato;
import com.eleicao.enums.Partido;
import com.eleicao.servico.CandidatoServico;

@SpringBootTest
public class CandidatoControleTeste {

	private Candidato candidato;
	
	private CandidatoDto candidatoDto;
	
	@Mock
	private CandidatoServico candidatoServico;
	
	@InjectMocks
	private CandidatoControle candidatoControle;
	
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	

	
	
	void start() {
		 candidato = new Candidato(1L,"Carlos",Partido.PL,123,1,15);
		 candidatoDto = new CandidatoDto(1L,"Carlos",Partido.PL,123,1,15);
	}
}
