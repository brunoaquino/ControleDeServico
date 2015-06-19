package br.com.cs.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.context.RequestContext;

import br.com.cs.dao.ServicoTesteDao;
import br.com.cs.model.Servico;
import br.com.cs.utils.HibernateUtil;
import br.com.cs.utils.UtilMensagens;

@ManagedBean
@SessionScoped
// @Named
// @SessionScoped
public class ControllerServicoCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Servico servico;
	private Servico servicoTemp;
	private DataModel<Servico> dataModelServicos;

	// @Inject
	// private SimpleClass simple;

	private ServicoTesteDao dao;

	@PostConstruct
	public void init() {
		dataModelServicos = new ListDataModel<Servico>(dao.getEntities());
		// simple.doSomething();
	}

	public ControllerServicoCadastro() {
		dao = new ServicoTesteDao(Servico.class, HibernateUtil
				.getSessionFactory().openSession());
	}

	public DataModel<Servico> getDataModelServicos() {
		return dataModelServicos;
	}

	public void setDataModelServicos(DataModel<Servico> dataModelServicos) {
		this.dataModelServicos = dataModelServicos;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String salvar(ActionEvent actionEvent) throws IOException {
		boolean erro = false;
		erro = validaDados(erro);
		RequestContext request = RequestContext.getCurrentInstance();
		if (erro) {
			request.addCallbackParam("sucesso", false);
		} else {
			dao.save(servico);
			dataModelServicos = new ListDataModel<Servico>(dao.getEntities());
			request.addCallbackParam("sucesso", true);
		}
		return "cadastroServico.xhtml";
	}

	private boolean validaDados(boolean erro) {
		if (servico.getNome().isEmpty()) {
			UtilMensagens.setMsgWarn("Campo Nome é obrigatório");
			servico.setNome(servicoTemp.getNome());
			erro = true;
		}
		if (servico.getDescricao().isEmpty()) {
			UtilMensagens.setMsgWarn("Campo Descrição é obrigatório");
			servico.setDescricao(servicoTemp.getDescricao());
			erro = true;
		}
		return erro;
	}

	public String cancelar() {
		servico.setNome(servicoTemp.getNome());
		servico.setDescricao(servicoTemp.getDescricao());
		servico.setPreco(servicoTemp.getPreco());
		return null;
	}

	public void preparaAdicionarServico(ActionEvent actionEvent) {
		servico = new Servico();
	}

	public void preparaAlterarServico(ActionEvent actionEvent) {
		servico = dataModelServicos.getRowData();
		servicoTemp = servico.getClone();
	}

	public void preparaExcluirServico(ActionEvent actionEvent) {
		servico = dataModelServicos.getRowData();
	}

	public void excluirServico(ActionEvent actionEvent) {
		dao.remove(servico);
		dataModelServicos = new ListDataModel<Servico>(dao.getEntities());
	}

}
