package com.eleicao.entidade;

import com.eleicao.enums.Partido;
import com.eleicao.servico.dto.CandidatoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "candidatos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidato{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;
	private	String nome;
	private	Partido partido;
	private	Integer numero;
	private	Integer zona;
	private	Integer sessao ;
	
	
	

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
