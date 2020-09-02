package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Turma;
import com.projeto.api.repository.TurmaRepository;
import com.projeto.api.repository.filter.TurmaFilter;

@Service
public class TurmaService {
	@Autowired
	private TurmaRepository dao;

	
	public List<Turma> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Turma> Filtrando(TurmaFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Turma Criar(Turma turma) {
		try {
			return this.dao.save(turma);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de TurmaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Turma BuscarPorId(Long id) {
		Optional<Turma> turma = this.dao.findById(id);

		if (turma.get() == null)
			throw new EmptyResultDataAccessException(1);

		return turma.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de TurmaService");
			e.printStackTrace();
		}
	}

	public void Deletar(Turma turma) {
		try {
			this.dao.delete(turma);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de TurmaService");
			e.printStackTrace();
		}
	}

	public Turma Atualizar(Long id, Turma turma) {
		try {
			Turma salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(turma, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de TurmaService");
			e.printStackTrace();
			return null;
		}		
	}
}






