package com.projeto.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class RegistroMovimentacaoFilter {
	private String descricao;
	private String sala;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataregistro;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getDataregistro() {
		return dataregistro;
	}
	
	public void setDataregistro(LocalDate dataregistro) {
		this.dataregistro = dataregistro;
	}

	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
}
