package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Serie;
import com.projeto.api.repository.filter.SerieFilter;

public interface SerieRepositoryQuery {
	public Page<Serie> Filtrando(SerieFilter filtro, Pageable pageable);
}
