package com.eleicao.entidade;

import com.eleicao.enums.Partido;
import com.eleicao.servico.dto.CandidatoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "candidatos")
public record Candidato(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long id,
		String nome,
		Partido partido,
		Integer numero,
		Integer zona,
		Integer sessao) {

	public Candidato(CandidatoDto candidato) {
		this(
				candidato.id(),
				candidato.nome(),
				candidato.partido(),
				candidato.numero(),
				candidato.zona(),
				candidato.sessao());
	}

}
