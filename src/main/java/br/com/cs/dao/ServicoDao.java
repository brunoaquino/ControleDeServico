package br.com.cs.dao;

import org.hibernate.Session;

import br.com.cs.model.Servico;

public class ServicoDao extends HibernateDAO<Servico> {

	private static final long serialVersionUID = 1L;

	public ServicoDao(Class<Servico> classe, Session session) {
		super(classe, session);
	}
}
