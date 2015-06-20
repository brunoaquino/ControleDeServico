package br.com.cs.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.cs.dao.FuncionarioDao;
import br.com.cs.dao.ServicoDao;
import br.com.cs.model.Funcionario;
import br.com.cs.model.Servico;

public class HibernateTest {
	public static void main(String[] args) {

		FuncionarioDao dao1 = new FuncionarioDao(Funcionario.class, HibernateUtil.getSessionFactory().openSession());
		ServicoDao dao2 = new ServicoDao(Servico.class, HibernateUtil.getSessionFactory().openSession());

		Servico servico1 = dao2.getEntity(1);
		Servico servico2 = dao2.getEntity(2);
		Servico servico3 = dao2.getEntity(3);

		Funcionario func1 = new Funcionario();
		func1.setNome("bruno");
		func1.setDataDeCadastro(new Date());
		func1.setDataDeAlteracao(new Date());

		Set<Servico> servicoList = new HashSet<Servico>();

		servicoList.add(servico1);
		servicoList.add(servico2);
		servicoList.add(servico3);

		func1.setServicos(servicoList);
		// dao1.save(func1);

		Funcionario func2 = dao1.getEntity(1);
		for (Servico servico : func2.getServicos()) {
			System.out.println(servico.getNome());
		}
	}

}
