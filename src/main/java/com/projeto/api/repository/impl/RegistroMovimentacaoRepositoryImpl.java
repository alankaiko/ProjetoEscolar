package com.projeto.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.projeto.api.domain.RegistroMovimentacao;
import com.projeto.api.domain.RegistroMovimentacao_;
import com.projeto.api.repository.filter.RegistroMovimentacaoFilter;

public class RegistroMovimentacaoRepositoryImpl implements RegistroMovimentacaoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<RegistroMovimentacao> Filtrando(RegistroMovimentacaoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<RegistroMovimentacao> query = builder.createQuery(RegistroMovimentacao.class);
		Root<RegistroMovimentacao> root = query.from(RegistroMovimentacao.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);

		TypedQuery<RegistroMovimentacao> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, RegistroMovimentacaoFilter filtro, Root<RegistroMovimentacao> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(RegistroMovimentacao_.descricao)), "%" + filtro.getDescricao().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(RegistroMovimentacaoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<RegistroMovimentacao> root = query.from(RegistroMovimentacao.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
