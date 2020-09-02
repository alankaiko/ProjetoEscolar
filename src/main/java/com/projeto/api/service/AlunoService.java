package com.projeto.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Aluno;
import com.projeto.api.repository.AlunoRepository;
import com.projeto.api.repository.filter.AlunoFilter;


@Service
public class AlunoService {
	@Autowired
	private AlunoRepository dao;

	
	public List<Aluno> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Aluno> Filtrando(AlunoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Aluno Criar(Aluno aluno) {
		try {
			return this.dao.save(aluno);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de AlunoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Aluno BuscarPorId(Long id) {
		Optional<Aluno> aluno = this.dao.findById(id);

		if (aluno.get() == null)
			throw new EmptyResultDataAccessException(1);

		return aluno.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de AlunoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Aluno aluno) {
		try {
			this.dao.delete(aluno);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de AlunoService");
			e.printStackTrace();
		}
	}

	public Aluno Atualizar(Long id, Aluno aluno) {
		try {
			Aluno salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(aluno, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de AlunoService");
			e.printStackTrace();
			return null;
		}		
	}
}






