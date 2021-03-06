package br.com.dca.cep.web;

import br.com.dca.cep.exception.ResourceNotFoundException;
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

@RestController
@RequestMapping("/cep")
public class CepController {

	private static final Logger logger = LoggerFactory.getLogger(CepController.class);

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/{numero}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CepResponse> buscarPorCep(@PathVariable String numero) throws Exception {
		String url = "http://viacep.com.br/ws/" + numero + "/json/";
		CepResponse cepResponse = restTemplate.getForObject(url, CepResponse.class);

		if (cepResponse.isErro()) {
			logger.debug("Cep {} não encontrado.", numero);
			throw new ResourceNotFoundException("Cep não encontrado.");
		}

		return ResponseEntity.ok(cepResponse);
	}

}
