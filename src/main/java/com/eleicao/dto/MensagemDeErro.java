package com.eleicao.dto;

import org.springframework.http.HttpStatus;

public record MensagemDeErro(HttpStatus ststus,String mensagem) {

}
