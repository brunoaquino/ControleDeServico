package br.com.cs.controller;

import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.cs.dao.UsuarioDao;
import br.com.cs.model.Usuario;
import br.com.cs.utils.FacesContextUtil;
import br.com.cs.utils.UtilMensagens;

@ManagedBean
@SessionScoped
public class ControllerAutenticacao {
	private Usuario usuario;
	private String login;
	private String senha;

	public String autenticaUsuario() {
		UsuarioDao dao = new UsuarioDao(Usuario.class, FacesContextUtil.getRequestSession());
		usuario = dao.getUsuarioPorLoginESenha(login, senha);

		if (usuario == null) {
			UtilMensagens.setMsgInfo("Login ou senha não correspondem");
			return "login.xhtml";
		} else {
			if (senha.equals(usuario.getSenha()) && login.equals(usuario.getEmail())) {

				HttpSession session;

				FacesContext ctx = FacesContext.getCurrentInstance();
				session = (HttpSession) ctx.getExternalContext().getSession(false);
				session.setAttribute("usuarioAutenticado", usuario);

				return "index.xhtml";
			} else {
				UtilMensagens.setMsgInfo("Login ou senha não correspondem");
				return "login.xhtml";
			}
		}
	}

	public String logout() {
		HttpSession session;

		FacesContext ctx = FacesContext.getCurrentInstance();
		session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("usuarioAutenticado", null);

		Enumeration<String> vals = session.getAttributeNames();

		while (vals.hasMoreElements()) {
			session.removeAttribute(vals.nextElement());
		}

		return "login.xhtml";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
