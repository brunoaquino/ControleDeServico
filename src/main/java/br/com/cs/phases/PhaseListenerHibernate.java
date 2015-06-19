package br.com.cs.phases;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.Session;

import br.com.cs.utils.FacesContextUtil;
import br.com.cs.utils.HibernateUtil;

public class PhaseListenerHibernate implements PhaseListener {

	private static final long serialVersionUID = 1L;

	// Antes da Fase
	@Override
	public void beforePhase(PhaseEvent fase) {
		abreSessaoHibernate(fase);
	}

	private void abreSessaoHibernate(PhaseEvent fase) {
		if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			FacesContextUtil.setRequestSession(session);
		}
	}

	// Depois da Fase
	@Override
	public void afterPhase(PhaseEvent fase) {
		validaSessaoHibernate(fase);
	}

	private void validaSessaoHibernate(PhaseEvent fase) {
		if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			Session session = FacesContextUtil.getRequestSession();
			try {
				session.getTransaction().commit();
			} catch (Exception e) {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} finally {
				session.close();
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
