package br.com.cs.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.context.RequestContext;

import br.com.cs.dao.FuncionarioDao;
import br.com.cs.dao.ServicoDao;
import br.com.cs.model.Funcionario;
import br.com.cs.model.Servico;
import br.com.cs.tipo.Estado;
import br.com.cs.utils.HibernateUtil;
import br.com.cs.utils.UtilMensagens;

@ManagedBean
@SessionScoped
public class ControllerFuncionarioCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private Funcionario funcionarioTemp;
	private Map<String, Estado> estados;
	private Map<String, Servico> servicos;
	private Servico servicoSelecionado;
	private Estado estadoSelecionado;
	private DataModel<Funcionario> dataModelFuncionarios;

	private FuncionarioDao dao;

	@PostConstruct
	public void init() {
		dataModelFuncionarios = new ListDataModel<Funcionario>(
				dao.getEntities());

		ServicoDao daoServico = new ServicoDao(Servico.class, HibernateUtil
				.getSessionFactory().openSession());

		servicos = new HashMap<String, Servico>();
		for (Servico servico : daoServico.getEntities()) {
			servicos.put(servico.getNome(), servico);
		}
	}

	public ControllerFuncionarioCadastro() {
		dao = new FuncionarioDao(Funcionario.class, HibernateUtil
				.getSessionFactory().openSession());

		estados = new HashMap<String, Estado>();
		for (Estado estado : Estado.values()) {
			estados.put(estado.getDescricao(), estado);
		}
	}

	public DataModel<Funcionario> getDataModelFuncionarios() {
		return dataModelFuncionarios;
	}

	public void setDataModelFuncionarios(
			DataModel<Funcionario> dataModelServicos) {
		this.dataModelFuncionarios = dataModelServicos;
	}

	public String salvar(ActionEvent actionEvent) throws IOException {
		boolean erro = false;
		erro = validaDados(erro);
		RequestContext request = RequestContext.getCurrentInstance();
		if (erro) {
			request.addCallbackParam("sucesso", false);
		} else {
			funcionario.setEstado(getEstadoSelecionado());
			if (funcionario.getDataDeCadastro() == null) {
				funcionario.setDataDeCadastro(new Date());
			}
			funcionario.setDataDeAlteracao(new Date());
			dao.save(funcionario);
			dataModelFuncionarios = new ListDataModel<Funcionario>(
					dao.getEntities());
			request.addCallbackParam("sucesso", true);
		}
		return "cadastroServico.xhtml";
	}

	private boolean validaDados(boolean erro) {
		if (funcionarioTemp == null) {
			funcionarioTemp = new Funcionario();
		}
		if (funcionario.getNome().isEmpty()) {
			UtilMensagens.setMsgWarn("Campo Nome é obrigatório");
			funcionario.setNome(funcionarioTemp.getNome());
			erro = true;
		}
		if (funcionario.getCpfCnpj().isEmpty()) {
			UtilMensagens.setMsgWarn("Campo CPF/CNPJ é obrigatório");
			funcionario.setCpfCnpj(funcionarioTemp.getCpfCnpj());
			erro = true;
		}
		return erro;
	}

	public String cancelar() {
		if (funcionarioTemp != null) {
			funcionario.setNome(funcionarioTemp.getNome());
			funcionario.setCpfCnpj(funcionarioTemp.getCpfCnpj());
			funcionario.setRg(funcionarioTemp.getRg());
			funcionario.setEndereco(funcionarioTemp.getEndereco());
			funcionario.setCep(funcionarioTemp.getCep());
			funcionario.setEstado(funcionarioTemp.getEstado());
			funcionario.setBairro(funcionarioTemp.getBairro());
			funcionario.setEmail(funcionarioTemp.getEmail());
			funcionario.setObservacoes(funcionarioTemp.getObservacoes());
			funcionario.setTelefone(funcionarioTemp.getTelefone());
			funcionario.setDataDeNascimento(funcionarioTemp
					.getDataDeNascimento());
			funcionario.setServicos(funcionarioTemp.getServicos());
		}
		return null;
	}

	public void preparaAdicionarFuncionario(ActionEvent actionEvent) {
		funcionario = new Funcionario();
	}

	public void preparaAlterarFuncionario(ActionEvent actionEvent) {
		funcionario = dataModelFuncionarios.getRowData();
		funcionarioTemp = funcionario.getClone();
		estadoSelecionado = funcionario.getEstado();
	}

	public void preparaExcluirfuncionario(ActionEvent actionEvent) {
		funcionario = dataModelFuncionarios.getRowData();
	}

	public void excluirFuncionario(ActionEvent actionEvent) {
		dao.remove(funcionario);
		dataModelFuncionarios = new ListDataModel<Funcionario>(
				dao.getEntities());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Map<String, Estado> getEstados() {
		return estados;
	}

	public void setEstados(Map<String, Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public Map<String, Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Map<String, Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

}
