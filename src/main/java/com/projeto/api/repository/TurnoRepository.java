package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Turno;
import com.projeto.api.repository.impl.TurnoRepositoryQuery;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>, TurnoRepositoryQuery{
}
