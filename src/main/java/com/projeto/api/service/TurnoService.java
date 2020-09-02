package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Turno;
import com.projeto.api.repository.TurnoRepository;
import com.projeto.api.repository.filter.TurnoFilter;

@Service
public class TurnoService {
	@Autowired
	private TurnoRepository dao;

	
	public List<Turno> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Turno> Filtrando(TurnoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Turno Criar(Turno turno) {
		try {
			return this.dao.save(turno);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de TurnoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Turno BuscarPorId(Long id) {
		Optional<Turno> turno = this.dao.findById(id);

		if (turno.get() == null)
			throw new EmptyResultDataAccessException(1);

		return turno.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de TurnoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Turno turno) {
		try {
			this.dao.delete(turno);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de TurnoService");
			e.printStackTrace();
		}
	}

	public Turno Atualizar(Long id, Turno turno) {
		try {
			Turno salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(turno, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de TurnoService");
			e.printStackTrace();
			return null;
		}		
	}
}






