package com.eleicao.dto;

import com.eleicao.enums.Partido;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record CandidatoDto(       	
		
		Long id,
		@NotBlank(message = "Não pode estar em branco")
		String nome,
		@Enumerated(EnumType.STRING)
		Partido partido,
		Integer numero,
		Integer zona,
		Integer sessao) {

	
}
