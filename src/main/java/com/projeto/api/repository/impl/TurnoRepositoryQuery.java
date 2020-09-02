package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Turno;
import com.projeto.api.repository.filter.TurnoFilter;

public interface TurnoRepositoryQuery {
	public Page<Turno> Filtrando(TurnoFilter filtro, Pageable pageable);
}
