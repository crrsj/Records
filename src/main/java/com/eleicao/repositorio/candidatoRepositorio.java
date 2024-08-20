package com.eleicao.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eleicao.entidade.Candidato;

public interface candidatoRepositorio extends JpaRepository<Candidato, Long>{

}
