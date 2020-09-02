package com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.api.domain.Resppedagogico;

@Repository
public interface ResppedagogicoRepository extends JpaRepository<Resppedagogico, Long>{

}
