package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.FuncionarioDAO;
import br.edu.ifpb.sicAgro.model.Funcionario;

/**
 * 
 * @author franck
 *
 */
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario, Long> implements FuncionarioDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Funcionario> findByName(String name) {
		
		Query query = entityManager.createNamedQuery("Funcionario.findByName");
		query.setParameter("name", "%" + name + "%");

		@SuppressWarnings("unchecked")
		List<Funcionario> result = query.getResultList();
		return result;
	}

}
