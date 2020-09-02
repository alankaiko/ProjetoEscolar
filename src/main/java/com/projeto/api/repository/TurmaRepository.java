package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Turma;
import com.projeto.api.repository.impl.TurmaRepositoryQuery;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>, TurmaRepositoryQuery{

}
