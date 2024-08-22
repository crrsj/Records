package com.eleicao.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
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
		void falhaAoCadastrarCandidato() {
			doThrow(new RuntimeException()).when(candidatoRepositorio).save(candidato);
			assertThrows(RuntimeException.class, ()-> candidatoServico.cadastrarCandidato(candidatoDto));
		}
		
	}
	   @Nested	   
	   class TesteNoMetodoBuscarPorId{
		   
		   @Test
		   @DisplayName("Quando buscar por id retorne sucesso")
		   void quandoBuscarPorIdRetorneSucesso(){
			   when(candidatoRepositorio.findById(anyLong())).thenReturn(optionalCandidato);
			   var resposta = candidatoServico.buscarPorId(1L);
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
		   @DisplayName("Falha ao buscar por id")
		   void falhaAoBuscarPorId() {
			   when(candidatoRepositorio.findById(anyLong())).thenThrow(new NoSuchElementException("ID não encontrado."));
			   try {
				   candidatoServico.buscarPorId(1L);
			   }catch(Exception ex) {
				 assertEquals(NoSuchElementException.class, ex.getClass());  
				 assertEquals("ID não encontrado.", ex.getMessage());
			   }
		   }
	   }
	   
	   @Nested
	   class TesteNoMetodoAtualizarCandidato{
		   
		   @Test
		   @DisplayName("Quando atualizar retorne sucesso")
		   void quandoAtualizarRetorneSucesso() {
				when(candidatoRepositorio.save(candidato)).thenReturn(candidato);
				var resposta = candidatoServico.atualizar(candidatoDto);
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
			void falhaAoAtualizarCandidato() {
				doThrow(new RuntimeException()).when(candidatoRepositorio).save(candidato);
				assertThrows(RuntimeException.class, ()-> candidatoServico.cadastrarCandidato(candidatoDto));
			}
	   }
	   
	   @Nested
	   class TestandoOmetodoExcluir{
	   
	   @Test
	   @DisplayName("Sucesso ao excluir")
	   void sucessoAoExcluir() {
		 doNothing().when(candidatoRepositorio).deleteById(anyLong());
		 candidatoServico.excluir(1L);
		 verify(candidatoRepositorio,times(1)).deleteById(1L);
	   }
	   }
	   
	   @Nested
	   class TesteDoMetodoListarTodos{
		   
		   @Test
		   @DisplayName("Quando listar todos retorne sucesso")
		   void quandoListarTodosRetorneSucesso() {
			   when(candidatoRepositorio.findAll()).thenReturn(List.of(candidato));
			   List<Candidato>resposta = candidatoServico.listarTodos();
			   assertEquals(1, resposta.size());
			   assertNotNull(resposta);
			   assertEquals(Candidato.class, resposta.get(0).getClass());
			   assertEquals(1L, resposta.get(0).getId() );			   
			   assertEquals("Carlos", resposta.get(0).getNome());	
			   assertEquals(Partido.PL, resposta.get(0).getPartido());
			   assertEquals(1, resposta.get(0).getZona());
			   assertEquals(15, resposta.get(0).getSessao());
		   }
	   }
	   
	     private void start() {
		 candidato = new Candidato(1L,"Carlos",Partido.PL,123,1,15);
		 candidatoDto = new CandidatoDto(1L,"Carlos",Partido.PL,123,1,15);
		 optionalCandidato = Optional.of(new Candidato(1L,"Carlos",Partido.PL,123,1,15));
	}
	

}
