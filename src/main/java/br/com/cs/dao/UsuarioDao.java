package br.com.cs.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.cs.model.Usuario;

public class UsuarioDao extends HibernateDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioDao(Class<Usuario> classe, Session session) {
		super(classe, session);
	}

	public Usuario getUsuarioPorLoginESenha(String login, String senha) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", login));
		criteria.add(Restrictions.eq("senha", senha));
		return (Usuario) criteria.uniqueResult();
	}
}
