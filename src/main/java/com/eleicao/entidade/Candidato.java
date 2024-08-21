package com.eleicao.entidade;

import com.eleicao.dto.CandidatoDto;
import com.eleicao.enums.Partido;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
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
