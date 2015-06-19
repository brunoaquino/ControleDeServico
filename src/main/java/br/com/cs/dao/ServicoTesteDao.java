package br.com.cs.dao;

import org.hibernate.Session;

import br.com.cs.model.Servico;

public class ServicoTesteDao extends HibernateDAO<Servico> {

	private static final long serialVersionUID = 1L;

	public ServicoTesteDao(Class<Servico> classe, Session session) {
		super(classe, session);
	}
}
