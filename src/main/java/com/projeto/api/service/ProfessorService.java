package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Professor;
import com.projeto.api.repository.ProfessorRepository;
import com.projeto.api.repository.filter.ProfessorFilter;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository dao;

	
	public List<Professor> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Professor> Filtrando(ProfessorFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public Professor Criar(Professor resp) {
		try {
			return this.dao.save(resp);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de ResppedagogicoService");
			e.printStackTrace();
			return null;
		}		
		
	}

	public Professor BuscarPorId(Long id) {
		Optional<Professor> resp = this.dao.findById(id);

		if (resp.get() == null)
			throw new EmptyResultDataAccessException(1);

		return resp.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de ResppedagogicoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Professor resp) {
		try {
			this.dao.delete(resp);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de ResppedagogicoService");
			e.printStackTrace();
		}
	}

	public Professor Atualizar(Long id, Professor resp) {
		try {
			Professor salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(resp, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de ResppedagogicoService");
			e.printStackTrace();
			return null;
		}		
	}
}






