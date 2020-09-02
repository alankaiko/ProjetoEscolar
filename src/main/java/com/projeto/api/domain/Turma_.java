package com.projeto.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Turma.class)
public abstract class Turma_ {

	public static volatile SingularAttribute<Turma, Long> codigo;
	public static volatile SingularAttribute<Turma, String> nome;
	public static volatile SingularAttribute<Turma, Turno> turno;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String TURNO = "turno";

}

