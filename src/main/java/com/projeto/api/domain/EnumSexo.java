package com.projeto.api.domain;

public enum EnumSexo {
	MASCULINO("Masculino"),FEMININO("Feminino");
	
	private String valor;
	
	private EnumSexo(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}
