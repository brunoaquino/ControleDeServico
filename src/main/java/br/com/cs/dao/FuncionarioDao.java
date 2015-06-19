package br.com.cs.dao;

import org.hibernate.Session;

import br.com.cs.model.Funcionario;

public class FuncionarioDao extends HibernateDAO<Funcionario> {

	private static final long serialVersionUID = 1L;

	public FuncionarioDao(Class<Funcionario> classe, Session session) {
		super(classe, session);
	}
}
