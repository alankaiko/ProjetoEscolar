package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.RegistroMovimentacao;
import com.projeto.api.repository.filter.RegistroMovimentacaoFilter;

public interface RegistroMovimentacaoRepositoryQuery {
	public Page<RegistroMovimentacao> Filtrando(RegistroMovimentacaoFilter filtro, Pageable pageable);
}
