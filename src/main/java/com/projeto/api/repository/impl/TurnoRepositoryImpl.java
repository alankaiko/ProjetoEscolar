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

import com.projeto.api.domain.Turno;
import com.projeto.api.domain.Turno_;
import com.projeto.api.repository.filter.TurnoFilter;

public class TurnoRepositoryImpl implements TurnoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Turno> Filtrando(TurnoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Turno> query = builder.createQuery(Turno.class);
		Root<Turno> root = query.from(Turno.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);

		TypedQuery<Turno> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, TurnoFilter filtro, Root<Turno> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getTurno()))
			lista.add(builder.like(builder.lower(root.get(Turno_.turno)), "%" + filtro.getTurno().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(TurnoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Turno> root = query.from(Turno.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
