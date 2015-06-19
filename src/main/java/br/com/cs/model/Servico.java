package br.com.cs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "servico")
public class Servico implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private int id;
	private double preco;
	private String nome;
	private String descricao;

	// private Set<Funcionario> servicos = new HashSet<Funcionario>(0);

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@Column(name = "servico_id", nullable = false)
	@GeneratedValue(generator = "generator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "doub_preco")
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Column(name = "text_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "text_descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// @ManyToMany(fetch = FetchType.LAZY, mappedBy = "servico")
	// public Set<Funcionario> getServicos() {
	// return servicos;
	// }
	//
	// public void setServicos(Set<Funcionario> servicos) {
	// this.servicos = servicos;
	// }

	@Transient
	public Servico getClone() {
		try {
			return (Servico) super.clone();
		} catch (CloneNotSupportedException e) {
			return this;
		}
	}
}
