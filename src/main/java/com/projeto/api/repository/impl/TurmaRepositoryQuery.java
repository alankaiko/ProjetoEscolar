package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Turma;
import com.projeto.api.repository.filter.TurmaFilter;

public interface TurmaRepositoryQuery {
	public Page<Turma> Filtrando(TurmaFilter filtro, Pageable pageable);
}
