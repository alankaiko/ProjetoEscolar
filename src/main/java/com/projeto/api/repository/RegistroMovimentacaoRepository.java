package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.RegistroMovimentacao;
import com.projeto.api.repository.impl.RegistroMovimentacaoRepositoryQuery;

@Repository
public interface RegistroMovimentacaoRepository extends JpaRepository<RegistroMovimentacao, Long>, RegistroMovimentacaoRepositoryQuery{

}
