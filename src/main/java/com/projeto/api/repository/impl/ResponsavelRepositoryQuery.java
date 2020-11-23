package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Responsavel;
import com.projeto.api.repository.filter.ResponsavelFilter;

public interface ResponsavelRepositoryQuery {
	public Page<Responsavel> Filtrando(ResponsavelFilter filtro, Pageable pageable);
}
