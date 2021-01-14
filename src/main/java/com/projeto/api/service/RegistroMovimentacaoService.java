package com.projeto.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.api.domain.RegistroMovimentacao;
import com.projeto.api.repository.RegistroMovimentacaoRepository;
import com.projeto.api.repository.filter.RegistroMovimentacaoFilter;

@Service
public class RegistroMovimentacaoService {
	@Autowired
	private RegistroMovimentacaoRepository dao;

	public List<RegistroMovimentacao> Listar() {
		return this.dao.findAll();
	}
	
	public Page<RegistroMovimentacao> Filtrando(RegistroMovimentacaoFilter filtro, Pageable page){
		try {
			return this.dao.FiltroPorData(filtro, page);
		} catch (Exception e) {
			return null;
		}	
	}

	public RegistroMovimentacao Criar(RegistroMovimentacao registro) {
		try {
			return this.dao.save(registro);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de RegistroMovimentacaoService");
			e.printStackTrace();
			return null;
		}
	}

	public RegistroMovimentacao BuscarPorId(Long id) {
		Optional<RegistroMovimentacao> registro = this.dao.findById(id);

		if (registro.get() == null)
			throw new EmptyResultDataAccessException(1);

		return registro.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de RegistroMovimentacaoService");
			e.printStackTrace();
		}
	}

	public void Deletar(RegistroMovimentacao registro) {
		try {
			this.dao.delete(registro);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de RegistroMovimentacaoService");
			e.printStackTrace();
		}
	}

	public RegistroMovimentacao Atualizar(Long id, RegistroMovimentacao registro) {
		try {
			RegistroMovimentacao salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(registro, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de RegistroMovimentacaoService");
			e.printStackTrace();
			return null;
		}
	}

}
