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

import br.com.cs.dao.FuncionarioDao;
import br.com.cs.model.Funcionario;
import br.com.cs.utils.HibernateUtil;
import br.com.cs.utils.UtilMensagens;

@ManagedBean
@SessionScoped
public class ControllerFuncionarioCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private Funcionario funcionarioTemp;
	private DataModel<Funcionario> dataModelFuncionarios;

	private FuncionarioDao dao;

	@PostConstruct
	public void init() {
		dataModelFuncionarios = new ListDataModel<Funcionario>(
				dao.getEntities());
	}

	public ControllerFuncionarioCadastro() {
		dao = new FuncionarioDao(Funcionario.class, HibernateUtil
				.getSessionFactory().openSession());
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
			dao.save(funcionario);
			dataModelFuncionarios = new ListDataModel<Funcionario>(
					dao.getEntities());
			request.addCallbackParam("sucesso", true);
		}
		return "cadastroServico.xhtml";
	}

	private boolean validaDados(boolean erro) {
		if(funcionarioTemp==null){
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

}
