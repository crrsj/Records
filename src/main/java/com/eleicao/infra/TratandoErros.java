package com.eleicao.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eleicao.dto.MensagemDeErro;

@ControllerAdvice
public class TratandoErros {
    @ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>tratarErrosDeId(){
		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST,"ID não encontrado.");
		return  ResponseEntity.badRequest().body(erros);
	}
}
