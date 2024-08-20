package com.eleicao.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.eleicao.entidade.Candidato;
import com.eleicao.repositorio.candidatoRepositorio;
import com.eleicao.servico.dto.CandidatoDto;

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
  
}
