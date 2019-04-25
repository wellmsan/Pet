package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import utils.ModelBase;
import utils.ValidacaoException;

@Entity(name="pessoa")
public abstract class Pessoa extends ModelBase {
	@Column(length=14, nullable=false)
	private String telefone;
	@Column(length=255, nullable=false)
	private String email;
	@Column(length=60, nullable=false)
	private String endereco;
	@Column(length=60, nullable=true)
	private String complementoEndereco;
	@Column(length=10, nullable=false)
	private String numeroEndereco;
	@Column(length=25, nullable=false)
	private String cidade;
	@Column(length=2, nullable=false)
	private String estado;
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void valida() throws ValidacaoException {
		if(email == null || "".equals(email.trim())) {
			throw new ValidacaoException("Preencha o campo E-mail!");
		}
		if(telefone == null || "".equals(telefone.trim())) {
			throw new ValidacaoException("Preencha o campo Telefone!");
		}
		if(cidade == null || "".equals(cidade.trim())) {
			throw new ValidacaoException("Preencha o campo Cidade!");
		}
		if(estado == null || "".equals(estado.trim())) {
			throw new ValidacaoException("Preencha o campo Estado!");
		}
	}
	
	
}
