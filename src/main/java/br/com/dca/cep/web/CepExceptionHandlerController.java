package br.com.dca.cep.web;

import br.com.dca.cep.exception.ErrorResource;
import br.com.dca.cep.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CepExceptionHandlerController extends ResponseEntityExceptionHandler {

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private ErrorResource errorResource(Integer code, String message) {
		return new ErrorResource(code, message);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleBadRequestException(final HttpClientErrorException e, WebRequest request) {
		ErrorResource error = errorResource(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return handleExceptionInternal(e, error, getHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException e, WebRequest request) {
		ErrorResource error = errorResource(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return handleExceptionInternal(e, error, getHeaders(), HttpStatus.NOT_FOUND, request);
	}


}
