package com.eleicao.dto;

import com.eleicao.enums.Partido;

public record CandidatoDto(       	
		
		Long id,
		String nome,
		Partido partido,
		Integer numero,
		Integer zona,
		Integer sessao) {

	
}
