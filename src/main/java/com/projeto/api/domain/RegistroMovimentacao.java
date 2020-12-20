package com.projeto.api.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="registromovimentacao")
@Entity
public class RegistroMovimentacao {
	private Long codigo;
	private LocalDate dataregistro;
	private String horaregistro;
	private Cartao cartao;
	private String descricao;
	private String dadosadicionais;
	private EnumStatus status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataregistro() {
		return dataregistro;
	}

	public void setDataregistro(LocalDate dataregistro) {
		this.dataregistro = dataregistro;
	}

	public String getHoraregistro() {
		return horaregistro;
	}
	
	public void setHoraregistro(String horaregistro) {
		this.horaregistro = horaregistro;
	}
	
	@OneToOne
	@JoinColumn(name = "tbl_cartao_id")
	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDadosadicionais() {
		return dadosadicionais;
	}

	public void setDadosadicionais(String dadosadicionais) {
		this.dadosadicionais = dadosadicionais;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	public EnumStatus getStatus() {
		return status;
	}
	
	public void setStatus(EnumStatus status) {
		this.status = status;
	}
}
