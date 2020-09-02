package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Cartao;
import com.projeto.api.repository.filter.CartaoFilter;

public interface CartaoRepositoryQuery {
	public Page<Cartao> Filtrando(CartaoFilter filtro, Pageable pageable);
}
