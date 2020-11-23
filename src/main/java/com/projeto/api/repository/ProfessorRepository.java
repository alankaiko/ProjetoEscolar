package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Professor;
import com.projeto.api.repository.impl.ProfessorRepositoryQuery;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>, ProfessorRepositoryQuery{

}
