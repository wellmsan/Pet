package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import utils.ModelBase;

@MappedSuperclass
public abstract class Pet extends ModelBase {
	@Column(length=50, nullable=false)
	private String nome;
	@Column(length=20, nullable=true)
	private Date nascimento;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario tutor;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	public Usuario getTutor() {
		return tutor;
	}
	
	public void setTutor(Usuario tutor) {
		this.tutor = tutor;
	}
	
	
}
