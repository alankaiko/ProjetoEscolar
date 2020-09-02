package com.projeto.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cartao.class)
public abstract class Cartao_ {

	public static volatile SingularAttribute<Cartao, Long> codigo;
	public static volatile SingularAttribute<Cartao, Aluno> aluno;
	public static volatile SingularAttribute<Cartao, String> codigobarras;
	public static volatile SingularAttribute<Cartao, String> titulo;
	public static volatile SingularAttribute<Cartao, Turma> turma;
	public static volatile SingularAttribute<Cartao, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String ALUNO = "aluno";
	public static final String CODIGOBARRAS = "codigobarras";
	public static final String TITULO = "titulo";
	public static final String TURMA = "turma";
	public static final String DESCRICAO = "descricao";

}

