package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import utils.ValidacaoException;

@Entity(name="pessoa_juridica")
public class Fornecedor extends Pessoa {
	@Column(length=200, nullable=false)
	private String razaoSocial;
	@Column(length=100, nullable=true)
	private String nomeFantasia;
	@Column(length=19, nullable=false) 
	private String cnpj;
	@Column(length=30, nullable=true)
	private String inscricaoEstadual;
	@Column(length=30, nullable=true)
	private String inscricaoMunicipal;
	
	public Fornecedor() {}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	
	public void valida() throws ValidacaoException {
		
	}

}
