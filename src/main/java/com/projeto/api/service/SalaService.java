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
import com.projeto.api.repository.SalaRepository;
import com.projeto.api.repository.filter.SalaFilter;

@Service
public class SalaService {
	@Autowired
	private SalaRepository dao;

	
	public List<Sala> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Sala> Filtrando(SalaFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Sala Criar(Sala sala) {
		try {
			return this.dao.save(sala);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de SalaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Sala BuscarPorId(Long id) {
		Optional<Sala> sala = this.dao.findById(id);

		if (sala.get() == null)
			throw new EmptyResultDataAccessException(1);

		return sala.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de SalaService");
			e.printStackTrace();
		}
	}

	public void Deletar(Sala sala) {
		try {
			this.dao.delete(sala);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de SalaService");
			e.printStackTrace();
		}
	}

	public Sala Atualizar(Long id, Sala sala) {
		try {
			Sala salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(sala, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de SalaService");
			e.printStackTrace();
			return null;
		}		
	}
}






