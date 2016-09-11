package br.edu.ifpb.sicAgro.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.SolicitacaoServicoDAO;
import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

public class SolicitacaoServicoServiceImpl extends GenericServiceImpl<SolicitacaoServico, Long> implements SolicitacaoServicoService {

	private static final long serialVersionUID = 1L;

	public SolicitacaoServicoServiceImpl() {
	}

	@Inject
	public SolicitacaoServicoServiceImpl(SolicitacaoServicoDAO dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void add(SolicitacaoServico entity) {
		entity.getCurrentStatus(entity);
		dao.add(entity);

	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public SolicitacaoServico update(SolicitacaoServico entity) {
		entity.getCurrentStatus(entity);
		return dao.update(entity);
	}

	/**
	 * 
	 */
	@Override
	public Map<Date, Integer> getSolicitacoesPorPeriodo(Integer nDays,
			SolicitationState state) {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;
		return solicitacaoServicoDAO.getSolicitacoesPorPeriodo(nDays, state);
	}

	/**
	 * 
	 */
	@Override
	public Long getTotalSolicitations() {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;

		return solicitacaoServicoDAO.getTotalSolicitations();
	}

	/**
	 * 
	 */
	@Override
	public List<SolicitacaoServico> filter(SolicitacaoFilter filter) {

		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;

		return solicitacaoServicoDAO.filter(filter);
	}

	/**
	 * 
	 */
	@Override
	public List<SolicitacaoServico> getSolicitacoesByFuncionario(
			Funcionario funcionario) {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;

		return solicitacaoServicoDAO.getSolicitacoesByFuncionario(funcionario);
	}

	/**
	 * 
	 */
	@Override
	public Long getCountSolicitationsByFuncionarioAndStatus(Funcionario funcionario,
			SolicitationState status) {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;

		return solicitacaoServicoDAO.getCountSolicitationsByFuncionarioAndStatus(
				funcionario, status);
	}

	/**
	 * 
	 */
	@Override
	public List<Object[]> getTotalSolicitacoesByMaquina() {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;
		List<Object[]> result = null;
		try {
			result = solicitacaoServicoDAO.getTotalSolicitacoesByMaquina();
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Long getCountSolicitacoesByFuncionario(Funcionario funcionario) {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;
		Long result = 0l;
		try {
			result = solicitacaoServicoDAO.getCountSolicitacoesByFuncionario(funcionario);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Long getCountSolicitacoesByProdutor(Produtor produtor) {
		SolicitacaoServicoDAO solicitacaoServicoDAO = (SolicitacaoServicoDAO) this.dao;
		Long result = 0l;
		try {
			result = solicitacaoServicoDAO.getCountSolicitacoesByProdutor(produtor);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}
}
