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
public class Cartao {
	private Long codigo;
	private String titulo;
	private String descricao;
	private String codigobarras;
	private Aluno aluno;
	private Turma turma;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigobarras() {
		return codigobarras;
	}

	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	
	@OneToOne
	@JoinColumn(name = "tbl_aluno_id")
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@OneToOne
	@JoinColumn(name = "tbl_turma_id")
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
