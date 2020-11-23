package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Responsavel;
import com.projeto.api.repository.impl.ResponsavelRepositoryQuery;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, ResponsavelRepositoryQuery{

}
