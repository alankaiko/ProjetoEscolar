package com.projeto.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class Turma {
	private Long codigo;
	private String nome;
	private Turno turno;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@OneToOne
	@JoinColumn(name = "tbl_turno_id", referencedColumnName = "codigo", nullable = true)
	public Turno getTurno() {
		return turno;
	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
}
