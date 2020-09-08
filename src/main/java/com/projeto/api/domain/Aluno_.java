package com.projeto.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aluno.class)
public abstract class Aluno_ {

	public static volatile SingularAttribute<Aluno, Long> codigo;
	public static volatile SingularAttribute<Aluno, Sala> sala;
	public static volatile SingularAttribute<Aluno, String> matricula;
	public static volatile SingularAttribute<Aluno, Serie> serie;
	public static volatile SingularAttribute<Aluno, String> nome;
	public static volatile SingularAttribute<Aluno, Resppedagogico> resppedagogico;
	public static volatile SingularAttribute<Aluno, Turno> turno;
	public static volatile SingularAttribute<Aluno, Turma> turma;
	public static volatile SingularAttribute<Aluno, EnumSexo> sexo;
	public static volatile SingularAttribute<Aluno, Responsavel> responsavel;

	public static final String CODIGO = "codigo";
	public static final String SALA = "sala";
	public static final String MATRICULA = "matricula";
	public static final String SERIE = "serie";
	public static final String NOME = "nome";
	public static final String RESPPEDAGOGICO = "resppedagogico";
	public static final String TURNO = "turno";
	public static final String TURMA = "turma";
	public static final String SEXO = "sexo";
	public static final String RESPONSAVEL = "responsavel";

}

