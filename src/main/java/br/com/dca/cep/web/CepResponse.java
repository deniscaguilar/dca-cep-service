package br.com.dca.cep.web;

import java.io.Serializable;

public class CepResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cep;

	private String logradouro;

	private String bairro;

	private String localidade;

	private String uf;

	private Boolean erro;

	public CepResponse() {
	}

	public CepResponse(String cep, String logradouro, String bairro, String localidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public CepResponse(Boolean erro) {
		this.erro = erro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Boolean isErro() {
		if(erro == null){
			return false;
		}
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

}
