package br.com.dca.cep.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dca.cep.exception.ErrorResource;

@ControllerAdvice
public class CepExceptionHandlerController extends ResponseEntityExceptionHandler {

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private ErrorResource errorResource(String code, String message) {
		return new ErrorResource(code, message);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// @ResponseBody
	public ResponseEntity<Object> handleBadRequestException(final HttpClientErrorException e, WebRequest request) {
		ErrorResource error = errorResource("BadRequest", e.getMessage());
		return handleExceptionInternal(e, error, getHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
