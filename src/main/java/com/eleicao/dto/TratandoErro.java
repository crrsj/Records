package com.eleicao.dto;

import org.springframework.validation.FieldError;

public record TratandoErro(String campo,String mensagem) {
	public TratandoErro(FieldError erro) {
		this(erro.getField(),erro.getDefaultMessage());
	}

}