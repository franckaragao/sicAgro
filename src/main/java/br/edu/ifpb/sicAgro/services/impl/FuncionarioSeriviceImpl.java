package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.FuncionarioDAO;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.services.FuncionarioService;

/**
 * 
 * @author franck
 *
 */
public class FuncionarioSeriviceImpl extends GenericServiceImpl<Funcionario, Long> implements FuncionarioService{

	private static final long serialVersionUID = 1L;
	
	public FuncionarioSeriviceImpl() {
	}
	
	@Inject
	public FuncionarioSeriviceImpl(FuncionarioDAO funcionarioDAO){
		this.dao = funcionarioDAO;
		
	}

	@Override
	public List<Funcionario> findByName(String name) {
		FuncionarioDAO funcionarioDAO = (FuncionarioDAO) this.dao;
		List<Funcionario> list = funcionarioDAO.findByName(name);
		return list;
	}

}
