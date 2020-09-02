package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Sala;
import com.projeto.api.repository.impl.SalaRepositoryQuery;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>, SalaRepositoryQuery{

}
