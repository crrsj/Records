package com.eleicao.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eleicao.entidade.Candidato;
import com.eleicao.servico.CandidatoServico;
import com.eleicao.servico.dto.CandidatoDto;

@RestController
@RequestMapping("candidato")
public class CandidatoControle {

	@Autowired
	private CandidatoServico servico;
	
	@PostMapping
	public ResponseEntity<Candidato>cadastrarCandidato(@RequestBody CandidatoDto candidato){
		var cadastrar = servico.cadastrarCandidato(candidato);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(cadastrar.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrar);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Candidato>>listarTodos(){
		var listar = servico.listarTodos();
		return ResponseEntity.ok().body(listar);
	}
	@GetMapping("{id}")
	public ResponseEntity<Candidato>buscarPorId(@PathVariable Long id){
		var buscar = servico.buscarPorId(id);
		return ResponseEntity.ok().body(buscar);
	}
	
	@PutMapping
	public ResponseEntity<Candidato>atualizarCandidato(@RequestBody CandidatoDto candidato){
		var atualizar = servico.atualizar(candidato);
		return ResponseEntity.ok().body(atualizar);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
