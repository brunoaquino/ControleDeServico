package br.com.cs.dao;

import java.util.List;

import br.com.cs.model.Servico;

public interface ServicoDao2 {
	public void save(Servico Servico);

	public Servico getServico(long id);

	public List<Servico> list();

	public void remove(Servico Servico);

	public void update(Servico Servico);

}
