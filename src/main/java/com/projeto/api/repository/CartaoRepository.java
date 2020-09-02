package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Cartao;
import com.projeto.api.repository.impl.CartaoRepositoryQuery;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>, CartaoRepositoryQuery{
	public Cartao findByCodigobarras(String codigobarras);
}
