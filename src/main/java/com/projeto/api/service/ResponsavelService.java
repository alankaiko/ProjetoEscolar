package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Responsavel;
import com.projeto.api.repository.ResponsavelRepository;
import com.projeto.api.repository.filter.ResponsavelFilter;

@Service
public class ResponsavelService {
	@Autowired
	private ResponsavelRepository dao;

	
	public List<Responsavel> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Responsavel> Filtrando(ResponsavelFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Responsavel Criar(Responsavel responsavel) {
		try {
			return this.dao.save(responsavel);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de ResponsavelService");
			e.printStackTrace();
			return null;
		}		
	}

	public Responsavel BuscarPorId(Long id) {
		Optional<Responsavel> responsavel = this.dao.findById(id);

		if (responsavel.get() == null)
			throw new EmptyResultDataAccessException(1);

		return responsavel.get();
	}

	public void Deletar(Long codigo) {
		try {
			this.dao.deleteById(codigo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de ResponsavelService");
			e.printStackTrace();
		}
	}

	public void Deletar(Responsavel responsavel) {
		try {
			this.dao.delete(responsavel);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de ResponsavelService");
			e.printStackTrace();
		}
	}

	public Responsavel Atualizar(Long codigo, Responsavel responsavel) {
		try {
			Responsavel salvo = this.BuscarPorId(codigo);
			
			BeanUtils.copyProperties(responsavel, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de ResponsavelService");
			e.printStackTrace();
			return null;
		}		
	}
}






