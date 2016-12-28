package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.FuncionarioDAO;
import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * 
 * @author franck
 *
 */
public class FuncionarioSeriviceImpl extends GenericServiceImpl<Funcionario, Long> implements FuncionarioService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	@Inject
	private ContaService contaService;
	
	public FuncionarioSeriviceImpl() {
	}
	
	@Inject
	public FuncionarioSeriviceImpl(FuncionarioDAO funcionarioDAO){
		this.dao = funcionarioDAO;
		
	}
	
	@Override
	@Transactional
	public void add(Funcionario entity) throws SicAgroException {
		FuncionarioDAO funcionarioDAO = (FuncionarioDAO) this.dao;
		Funcionario f = funcionarioDAO.findByCPF(entity.getCpf());
		if(f != null){
			throw new SicAgroExceptionHandler("Já existe um funcinário com este CPF cadastrado");
		}
		Conta conta = contaService.findByUserName(entity.getCpf());
		if(conta != null){
			throw new SicAgroExceptionHandler("Já existe um usuário cadastrado com este CPF: "+ entity.getCpf());
		}
		dao.add(entity);
	}
	
	@Override
	@Transactional
	public void remove(Funcionario entity) throws SicAgroException {
		Long count = solicitacaoServicoService.getCountSolicitacoesByFuncionario(entity);
		if(count > 0){
			throw new SicAgroExceptionHandler(
					"Funcionário não pode ser removido, está relacionado com solicitação de serviço de máquinas");
		}
		dao.remove(entity);
	}

	@Override
	public List<Funcionario> findByName(String name) {
		FuncionarioDAO funcionarioDAO = (FuncionarioDAO) this.dao;
		List<Funcionario> list = funcionarioDAO.findByName(name);
		return list;
	}

	@Override
	public List<Funcionario> findDriversByName(String name) {
		FuncionarioDAO funcionarioDAO = (FuncionarioDAO) this.dao;
		List<Funcionario> result = null;
		try {
			result = funcionarioDAO.findDriversByName(name, UserRole.DRIVER);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

}
