package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Sala;
import com.projeto.api.repository.filter.SalaFilter;

public interface SalaRepositoryQuery {
	public Page<Sala> Filtrando(SalaFilter filtro, Pageable pageable);
}
