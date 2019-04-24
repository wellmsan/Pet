package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import utils.ModelBase;
import utils.ValidacaoException;

@Entity(name="usuario")
public class Usuario extends ModelBase {
	@Column(length=50, nullable=false)
	private String nome;
	@Column(length=255, nullable=false)
	private String email;
	@Column(length=50, nullable=false)
	private String senha;
	
	public Usuario() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void valida() throws ValidacaoException {
		if(nome == null || "".equals(nome.trim())) {
			throw new ValidacaoException("Preencha o campo Nome!");
		}
		if(email == null || "".equals(email.trim())) {
			throw new ValidacaoException("Preencha o campo E-mail!");
		}
		if(senha == null || "".equals(senha.trim())) {
			throw new ValidacaoException("Preencha o campo Senha!");
		}
	}
	
}
