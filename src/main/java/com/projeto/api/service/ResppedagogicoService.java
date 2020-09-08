package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Resppedagogico;
import com.projeto.api.repository.ResppedagogicoRepository;
import com.projeto.api.repository.filter.ResppedagogicoFilter;

@Service
public class ResppedagogicoService {
	@Autowired
	private ResppedagogicoRepository dao;

	
	public List<Resppedagogico> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Resppedagogico> Filtrando(ResppedagogicoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public Resppedagogico Criar(Resppedagogico resp) {
		try {
			return this.dao.save(resp);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de ResppedagogicoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Resppedagogico BuscarPorId(Long id) {
		Optional<Resppedagogico> resp = this.dao.findById(id);

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

	public void Deletar(Resppedagogico resp) {
		try {
			this.dao.delete(resp);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de ResppedagogicoService");
			e.printStackTrace();
		}
	}

	public Resppedagogico Atualizar(Long id, Resppedagogico resp) {
		try {
			Resppedagogico salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(resp, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de ResppedagogicoService");
			e.printStackTrace();
			return null;
		}		
	}
}






