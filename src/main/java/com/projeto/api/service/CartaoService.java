package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.Cartao;
import com.projeto.api.repository.CartaoRepository;
import com.projeto.api.repository.filter.CartaoFilter;

@Service
public class CartaoService {
	@Autowired
	private CartaoRepository dao;

	
	public List<Cartao> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Cartao> Filtrando(CartaoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Cartao Criar(Cartao cartao) {
		try {
			return this.dao.save(cartao);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de CartaoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Cartao BuscarPorId(Long id) {
		Optional<Cartao> cartao = this.dao.findById(id);

		if (cartao.get() == null)
			throw new EmptyResultDataAccessException(1);

		return cartao.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de CartaoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Cartao cartao) {
		try {
			this.dao.delete(cartao);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de CartaoService");
			e.printStackTrace();
		}
	}

	public Cartao Atualizar(Long id, Cartao cartao) {
		try {
			Cartao salvo = this.BuscarPorId(id);
			
			BeanUtils.copyProperties(cartao, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de CartaoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	public Cartao BuscarPorCodigoBarras(String codigobarras) {
		try {
			return this.dao.findByCodigobarras(codigobarras);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo BuscarPorCodigoBarras------------------ de CartaoService");
			e.printStackTrace();
			return null;
		}
	}
}






