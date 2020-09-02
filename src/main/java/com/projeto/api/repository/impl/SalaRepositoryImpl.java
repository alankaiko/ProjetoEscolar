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

import com.projeto.api.domain.Sala;
import com.projeto.api.domain.Sala_;
import com.projeto.api.repository.filter.SalaFilter;

public class SalaRepositoryImpl implements SalaRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Sala> Filtrando(SalaFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Sala> query = builder.createQuery(Sala.class);
		Root<Sala> root = query.from(Sala.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);

		TypedQuery<Sala> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, SalaFilter filtro, Root<Sala> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getSala()))
			lista.add(builder.like(builder.lower(root.get(Sala_.sala)), "%" + filtro.getSala().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(SalaFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Sala> root = query.from(Sala.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
