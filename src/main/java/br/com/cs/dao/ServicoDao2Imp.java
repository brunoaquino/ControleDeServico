package br.com.cs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cs.model.Servico;
import br.com.cs.utils.HibernateUtil;

public class ServicoDao2Imp implements ServicoDao2 {

	public void save(Servico servico) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction tc = sessao.beginTransaction();
		sessao.save(servico);
		tc.commit();
	}

	public Servico getServico(long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		return (Servico) sessao.load(Servico.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Servico> list() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria critera = sessao.createCriteria(Servico.class);
		List<Servico> lista = critera.list();
		return lista;
	}

	public void remove(Servico servico) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction tc = sessao.beginTransaction();
		sessao.delete(servico);
		tc.commit();
	}

	public void update(Servico servico) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction tc = sessao.beginTransaction();
		sessao.update(servico);
		tc.commit();
	}
}