package br.com.cs.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ControllerIndex {

	public String abreCadastroDeServicos() {
		return "cadastroServico.xhtml";
	}

	public String abreCadastroDeFuncionario() {
		return "cadastroFuncionario.xhtml";
	}

	public String abreCadastroDeCliente() {
		return "cadastroCliente.xhtml";
	}

	public String abreGerenciadorDeServicos() {
		return "gerenciadorDeServicos.xhtml";
	}
}
