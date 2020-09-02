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

import com.projeto.api.domain.Cartao;
import com.projeto.api.domain.Cartao_;
import com.projeto.api.repository.filter.CartaoFilter;

public class CartaoRepositoryImpl implements CartaoRepositoryQuery {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Cartao> Filtrando(CartaoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cartao> query = builder.createQuery(Cartao.class);
		Root<Cartao> root = query.from(Cartao.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);

		TypedQuery<Cartao> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, CartaoFilter filtro, Root<Cartao> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getTitulo()))
			lista.add(builder.like(builder.lower(root.get(Cartao_.titulo)),"%" + filtro.getTitulo().toLowerCase() + "%"));

		if (!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(Cartao_.descricao)),"%" + filtro.getDescricao().toLowerCase() + "%"));

		if (!StringUtils.isEmpty(filtro.getCodigobarras()))
			lista.add(builder.like(builder.lower(root.get(Cartao_.codigobarras)),"%" + filtro.getCodigobarras().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(CartaoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Cartao> root = query.from(Cartao.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
