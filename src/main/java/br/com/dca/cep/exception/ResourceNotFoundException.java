package br.com.dca.cep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cep não encontrado!")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
