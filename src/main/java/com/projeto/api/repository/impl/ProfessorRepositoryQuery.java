package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Professor;
import com.projeto.api.repository.filter.ProfessorFilter;

public interface ProfessorRepositoryQuery {
	public Page<Professor> Filtrando(ProfessorFilter filtro, Pageable pageable);
}
