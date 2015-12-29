package br.com.dca.cep.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.dca.cep.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/cep")
public class CepController {

	private static final Logger logger = LoggerFactory.getLogger(CepController.class);

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/{numero}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CepResponse> buscarPorCep(@PathVariable String numero) throws Exception {
		logger.info("Buscando o endereço pelo cep {}", numero);
		String url = "http://viacep.com.br/ws/" + numero + "/json/";
		CepResponse cepResponse = restTemplate.getForObject(url, CepResponse.class);
		if (cepResponse.isErro()) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(cepResponse);
	}

}
