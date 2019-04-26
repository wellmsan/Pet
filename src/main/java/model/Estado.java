package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import utils.ModelBase;

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
	
	
}
