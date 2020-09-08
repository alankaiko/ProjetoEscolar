package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Sala;
import com.projeto.api.domain.Serie;
import com.projeto.api.repository.SerieRepository;
import com.projeto.api.repository.filter.SalaFilter;
import com.projeto.api.repository.filter.SerieFilter;

@Service
public class SerieService {
	@Autowired
	private SerieRepository dao;

	
	public List<Serie> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Serie> Filtrando(SerieFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Serie Criar(Serie serie) {
		try {
			return this.dao.save(serie);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de SerieService");
			e.printStackTrace();
			return null;
		}		
	}

	public Serie BuscarPorId(Long id) {
		Optional<Serie> serie = this.dao.findById(id);

		if (serie.get() == null)
			throw new EmptyResultDataAccessException(1);

		return serie.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de SerieService");
			e.printStackTrace();
		}
	}

	public void Deletar(Serie serie) {
		try {
			this.dao.delete(serie);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de SerieService");
			e.printStackTrace();
		}
	}

	public Serie Atualizar(Long id, Serie serie) {
		try {
			Serie salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(serie, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de SerieService");
			e.printStackTrace();
			return null;
		}		
	}
}






