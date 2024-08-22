package com.eleicao.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
		 start();
	}
	
	@Nested
	class TesteDoMetodoCadastrarCandidato{
		
		@Test
		@DisplayName("Quando cadastrar candidato retorne sucesso")
		void quandoCadastrarCandidatoRetorneSucesso() {
			when(candidatoServico.cadastrarCandidato(candidatoDto)).thenReturn(candidato);
			ResponseEntity<Candidato>resposta = candidatoControle.cadastrarCandidato(candidatoDto);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().getId());
			assertEquals("Carlos", resposta.getBody().getNome());
			assertEquals(Partido.PL, resposta.getBody().getPartido());
			assertEquals(123, resposta.getBody().getNumero());
			assertEquals(1, resposta.getBody().getZona());
			assertEquals(15, resposta.getBody().getSessao());
			
			
			
		}
		
		
		
	}

	@Nested
	class TesteNoMetodoBuscarPorId{
		
		@Test
		@DisplayName("Quando buscar por id retorne sucesso")
		void quandoBuscarPorIdRertorneSucesso() {
			when(candidatoServico.buscarPorId(anyLong())).thenReturn(candidato);
			ResponseEntity<Candidato>resposta = candidatoControle.buscarPorId(1L);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().getId());
			assertEquals("Carlos", resposta.getBody().getNome());
			assertEquals(Partido.PL, resposta.getBody().getPartido());
			assertEquals(123, resposta.getBody().getNumero());
			assertEquals(1, resposta.getBody().getZona());
			assertEquals(15, resposta.getBody().getSessao());
		}
		
	  
	}
	
	@Nested
	class TesteNoMetodoAtualizar{
		
		@Test
		@DisplayName("Quando atualizar retorne sucesso")
		void quandoAtualizarRetorneSucessio() {
			when(candidatoServico.atualizar(candidatoDto)).thenReturn(candidato);
			ResponseEntity<Candidato>resposta = candidatoControle.atualizarCandidato(candidatoDto);
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().getId());
			assertEquals("Carlos", resposta.getBody().getNome());
			assertEquals(Partido.PL, resposta.getBody().getPartido());
			assertEquals(123, resposta.getBody().getNumero());
			assertEquals(1, resposta.getBody().getZona());
			assertEquals(15, resposta.getBody().getSessao());
			
			
		}
		
	}
	
	@Nested
	class TesteAoListarTodos{
		
		@Test
		@DisplayName("Quando listar todos retorne sucesso")
		void quandoListarTodosRetorneSucesso() {
			when(candidatoServico.listarTodos()).thenReturn(List.of(candidato));
			ResponseEntity<List<Candidato>>resposta = candidatoControle.listarTodos();
			assertNotNull(resposta);
			assertEquals(ResponseEntity.class, resposta.getClass());
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(1L, resposta.getBody().get(0).getId());
			assertEquals("Carlos", resposta.getBody().get(0).getNome());
			assertEquals(Partido.PL, resposta.getBody().get(0).getPartido());
			assertEquals(123, resposta.getBody().get(0).getNumero());
			assertEquals(1, resposta.getBody().get(0).getZona());
			assertEquals(15, resposta.getBody().get(0).getSessao());
			
		}
	  }
	    @Nested
	     class TesteExcluir{
	    	@Test
	    	@DisplayName("Quando excluir retorne sucesso")
	    	 void quandoExcluirRetorneSucesso() {
	    		 doNothing().when(candidatoServico).excluir(anyLong());
	    		 ResponseEntity<Void> resposta = candidatoControle.excluir(1L);
	    		 assertNotNull(resposta);
	    		 verify(candidatoServico,times(1)).excluir(anyLong());
	    		 assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	    	 }
	     }
	
	     private void start() {
		 candidato = new Candidato(1L,"Carlos",Partido.PL,123,1,15);
		 candidatoDto = new CandidatoDto(1L,"Carlos",Partido.PL,123,1,15);
	}
}
