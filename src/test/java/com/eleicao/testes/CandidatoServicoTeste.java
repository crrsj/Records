package com.eleicao.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.eleicao.dto.CandidatoDto;
import com.eleicao.entidade.Candidato;
import com.eleicao.enums.Partido;
import com.eleicao.repositorio.candidatoRepositorio;
import com.eleicao.servico.CandidatoServico;

@SpringBootTest
public class CandidatoServicoTeste {
	
	private Candidato candidato;
	private Optional<Candidato>optionalCandidato;
	private CandidatoDto candidatoDto;
	
	@Mock
	private candidatoRepositorio candidatoRepositorio;
	
	@InjectMocks
	private CandidatoServico candidatoServico;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Nested
	class TestesNoMetodoCadastrarCandidato{
		
		@Test
		@DisplayName("Quando cadastrar um candidato retorne sucesso.")
		void quandoCadastrarCandidatoRetorneSucesso() { 
			
			when(candidatoRepositorio.save(candidato)).thenReturn(candidato);
			var resposta = candidatoServico.cadastrarCandidato(candidatoDto);
			assertNotNull(resposta);
			assertEquals(Candidato.class, resposta.getClass());
			assertEquals(1L, resposta.getId());
			assertEquals("Carlos", resposta.getNome());
			assertEquals(Partido.PL, resposta.getPartido());
			assertEquals(123, resposta.getNumero());
			assertEquals(1, resposta.getZona());
			assertEquals(15, resposta.getSessao());
		}
		
		@Test
		@DisplayName("Falha ao cadastrar usuario")
		void falhaAocadastrarCandidato() {
			doThrow(new RuntimeException()).when(candidatoRepositorio).save(candidato);
			assertThrows(RuntimeException.class, ()-> candidatoServico.cadastrarCandidato(candidatoDto));
		}
		
	}
	
	
	
	void start() {
		 candidato = new Candidato(1L,"Carlos",Partido.PL,123,1,15);
		 candidatoDto = new CandidatoDto(1L,"Carlos",Partido.PL,123,1,15);
		 optionalCandidato = Optional.of(new Candidato(1L,"Carlos",Partido.PL,123,1,15));
	}
	

}
