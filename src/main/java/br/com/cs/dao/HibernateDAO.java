package br.com.cs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cs.utils.UtilMensagens;

public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {

	private static final long serialVersionUID = 1L;

	private Class<T> classe;
	private Session session;

	public HibernateDAO(Class<T> classe, Session session) {
		super();
		this.classe = classe;
		this.session = session;
	}

	@Override
	public void save(T entity) {
		Transaction transacao = session.getTransaction();
		transacao.begin();
		try {
			session.save(entity);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			UtilMensagens.setMsgFatal("O sistema se recuperou de um erro");
		}
	}

	@Override
	public void update(T entity) {
		Transaction transacao = session.getTransaction();
		transacao.begin();
		try {
			session.update(entity);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			UtilMensagens.setMsgFatal("O sistema se recuperou de um erro");
		}
	}

	@Override
	public void remove(T entity) {
		Transaction transacao = session.getTransaction();
		transacao.begin();
		try {
			session.delete(entity);
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			UtilMensagens.setMsgFatal("O sistema se recuperou de um erro");
		}

	}

	@Override
	public void merge(T entity) {
		session.merge(entity);
	}

	@Override
	public T getEntity(Serializable id) {
		T entity = (T) session.get(classe, id);
		return entity;
	}

	@Override
	public List<T> getEntities() {
		List<T> enties = (List<T>) session.createCriteria(classe).list();
		return enties;
	}

	public Session getSession() {
		return session;
	}
}
