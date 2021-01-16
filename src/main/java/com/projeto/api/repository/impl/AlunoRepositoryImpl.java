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

import com.projeto.api.domain.Aluno;
import com.projeto.api.domain.Aluno_;
import com.projeto.api.repository.filter.AlunoFilter;

public class AlunoRepositoryImpl implements AlunoRepositoryQuery {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Aluno> Filtrando(AlunoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Aluno> query = builder.createQuery(Aluno.class);
		Root<Aluno> root = query.from(Aluno.class);

		query.orderBy(builder.asc(root.get("codigo")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);

		TypedQuery<Aluno> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);

		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, AlunoFilter filtro, Root<Aluno> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getNome()))
			lista.add(builder.like(builder.lower(root.get(Aluno_.nome)), "%" + filtro.getNome().toLowerCase() + "%"));
		
		if (!StringUtils.isEmpty(filtro.getMatricula()))
			lista.add(builder.like(builder.lower(root.get(Aluno_.matricula)), "%" + filtro.getMatricula().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(AlunoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Aluno> root = query.from(Aluno.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
