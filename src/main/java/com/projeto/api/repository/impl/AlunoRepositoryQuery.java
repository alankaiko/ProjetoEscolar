package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Aluno;
import com.projeto.api.repository.filter.AlunoFilter;

public interface AlunoRepositoryQuery {
	public Page<Aluno> Filtrando(AlunoFilter filtro, Pageable pageable);
}
