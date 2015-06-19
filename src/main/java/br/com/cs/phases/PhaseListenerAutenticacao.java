package br.com.cs.phases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.cs.model.Usuario;

public class PhaseListenerAutenticacao implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		ExternalContext ec = fc.getExternalContext();

		if (!fc.getViewRoot().getViewId().contains("login.xhtml")) {
			HttpSession session = (HttpSession) ec.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

			if (usuario == null) {
				try {
					ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
				} catch (IOException ex) {
					Logger.getLogger(PhaseListenerAutenticacao.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
