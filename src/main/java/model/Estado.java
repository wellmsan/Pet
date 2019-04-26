package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import utils.ModelBase;
import utils.ValidacaoException;

@Entity(name="estado")
public class Estado extends ModelBase{
	@Column(length=80, nullable=false)
	private String nome;
	@Column(length=2, nullable=false)
	private String uf;
	@Column(length=2, nullable=false)
	private String pais;
	
	public Estado() {}

	public Estado(String nome, String uf, String pais) {
		super();
		this.nome = nome;
		this.uf = uf;
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void valida() throws ValidacaoException {
		if(nome == null || "".equals(nome.trim())) {
			throw new ValidacaoException("Preencha o campo Nome!");
		}
		if(uf == null || "".equals(uf.trim())) {
			throw new ValidacaoException("Preencha o campo UF");
		}
		if(pais == null || "".equals(pais.trim())) {
			throw new ValidacaoException("Preencha o campo País");
		}
	}
	
	
}
