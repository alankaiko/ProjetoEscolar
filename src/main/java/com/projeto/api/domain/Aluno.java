package com.projeto.api.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class Aluno {
	private Long codigo;
	private String nome;
	private String matricula;
	private Responsavel responsavel;
	private Serie serie;
	private Turma turma;
	private Professor professor;
	private Turno turno;
	private Sala sala;
	private EnumSexo sexo;
	private Imagem imagem;
	
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
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavel_codigo")
	public Responsavel getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_serie_id", referencedColumnName = "codigo")
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_turma_id", referencedColumnName = "codigo")
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_resppedagogico_id", referencedColumnName = "codigo")
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_turno_id", referencedColumnName = "codigo")
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_sala_id", referencedColumnName = "codigo")
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Enumerated(EnumType.STRING)
	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imagem_codigo")
	public Imagem getImagem() {
		return imagem;
	}
	
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

}
