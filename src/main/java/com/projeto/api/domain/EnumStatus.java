package com.projeto.api.domain;

public enum EnumStatus {
	ENTRADA("ENTRADA"), SAIDA("SAIDA");
	
	private String valor;
	
	private EnumStatus(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
