package com.eleicao.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eleicao.entidade.Candidato;
import com.eleicao.servico.CandidatoServico;
import com.eleicao.servico.dto.CandidatoDto;

@RestController
@RequestMapping
public class CandidatoControle {

	@Autowired
	private CandidatoServico servico;
	
	@PostMapping
	public ResponseEntity<Candidato>cadastrarCandidato(@RequestBody CandidatoDto candidato){
		var cadastrar = servico.cadastrarCandidato(candidato);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(cadastrar.id()).toUri();
		return ResponseEntity.created(uri).body(cadastrar);
	}
}
