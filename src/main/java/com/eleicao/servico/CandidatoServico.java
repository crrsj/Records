package com.eleicao.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleicao.dto.CandidatoDto;
import com.eleicao.entidade.Candidato;
import com.eleicao.repositorio.candidatoRepositorio;


@Service
public class CandidatoServico {
  @Autowired
  private candidatoRepositorio repositorio; 
  
  public Candidato cadastrarCandidato(CandidatoDto candidato) {
	  var cadastrar = new Candidato(candidato);
	  return repositorio.save(cadastrar);
  }
  
  public Iterable<Candidato>listarTodos(){
	  return repositorio.findAll();
  }
  public Candidato buscarPorId(Long id) {
	  Optional<Candidato>buscar = repositorio.findById(id);
	  return buscar.get();
  }
  public Candidato atualizar(CandidatoDto candidato) {
	  var atualizar = new Candidato(candidato);
	  return repositorio.save(atualizar);
  }
  
  public void excluir(Long id) {
	  repositorio.deleteById(id);
  }
}
