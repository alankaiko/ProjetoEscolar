package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Aluno;
import com.projeto.api.repository.impl.AlunoRepositoryQuery;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, AlunoRepositoryQuery{

}
