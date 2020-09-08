package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Serie;
import com.projeto.api.repository.impl.SerieRepositoryQuery;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>, SerieRepositoryQuery {

}
