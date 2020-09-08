package com.projeto.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.api.domain.Resppedagogico;
import com.projeto.api.repository.filter.ResppedagogicoFilter;

public interface ResppedagogicoRepositoryQuery {
	public Page<Resppedagogico> Filtrando(ResppedagogicoFilter filtro, Pageable pageable);
}
