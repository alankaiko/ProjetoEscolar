package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Imagem;
import com.projeto.api.repository.ImagemRepository;

@Service
public class ImagemService {
	@Autowired
	private ImagemRepository dao;

	
	public List<Imagem> Listar() {
		return this.dao.findAll();
	}

	public Imagem Criar(Imagem imagem) {
		try {
			return this.dao.save(imagem);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public Imagem BuscarPorId(Long id) {
		Optional<Imagem> imagem = this.dao.findById(id);

		if (imagem.get() == null)
			throw new EmptyResultDataAccessException(1);

		return imagem.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Deletar(Imagem imagem) {
		try {
			this.dao.delete(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Imagem Atualizar(Long id, Imagem imagem) {
		try {
			Imagem salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(imagem, salvo, "id");
			return this.Criar(salvo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

}
