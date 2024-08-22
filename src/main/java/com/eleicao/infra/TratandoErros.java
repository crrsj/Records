package com.eleicao.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eleicao.dto.MensagemDeErro;
import com.eleicao.dto.TratandoErro;


@ControllerAdvice
public class TratandoErros {
    @ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>tratarErrosDeId(){
		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST,"ID não encontrado.");
		return  ResponseEntity.badRequest().body(erros);
		
	}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>tratador400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(TratandoErro::new).toList());		
	}
    @ExceptionHandler(HttpMessageNotReadableException.class)
  	public ResponseEntity<MensagemDeErro>tratarErrosDeEnum(){
  		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST,"campo: Partido vazio ou incorreto.");
  		return  ResponseEntity.badRequest().body(erros);
  		
  	}
    
}
