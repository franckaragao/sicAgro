package br.edu.ifpb.sicAgro.beans.solicitacaoServicoMaquinas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.userSession.UserLogged;

@Named
@RequestScoped
public class SolicitacaoServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoServicoService service;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	@UserLogged
	private Conta conta;

	private SolicitacaoServico selectedSolicitacao;

	private List<SolicitacaoServico> solicitacoes;
	private List<SolicitationState> statusList = new ArrayList<SolicitationState>();

	private SolicitacaoFilter filter = SolicitacaoFilter.getInstance();

	@PostConstruct
	public void init() {
		this.listSolicitacoes();
		this.updateStates();
		statusList.add(SolicitationState.COMPLETED);
		statusList.add(SolicitationState.FAIL);
		statusList.add(SolicitationState.PROGRESS);
	}

	public void renderTo() {
		JSFUtils.rederTo("solicitacaoView.xhtml");
		JSFUtils.setParam("solicitacao", selectedSolicitacao);
	}

	public void listSolicitacoes() {
		if (isUserLoggedIsDriver()) {
			this.solicitacoes = service.getSolicitacoesByFuncionario(conta.getFuncionario());
		} else {
			this.solicitacoes = service.findAll();
		}
	}

	public void filter() {
		if (isUserLoggedIsDriver()) {
			solicitacoes = service.filter(filter);
		} else {
			solicitacoes = service.filter(filter);
		}
	}

	public void updateStates() {
		for (SolicitacaoServico solicitacaoServico : solicitacoes) {
			solicitacaoServico.setState(service.getCurrentStatus(solicitacaoServico));
		}
	}

	public boolean isUserLoggedIsDriver() {
		if (conta.getUserRole().equals(UserRole.DRIVER))
			return true;
		return false;
	}

	public List<Veiculo> getVeiculos() {
		return veiculoService.findAll();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarioService.findAll();
	}
	
	public Long getCountSolicitationsByFuncionario(){
		Long n = service.getCountSolicitationsByFuncionario(conta.getFuncionario(), SolicitationState.PROGRESS);
		return n;
	}
	
	public String updateLabelStatus(SolicitationState state) {
		if (state.equals(SolicitationState.COMPLETED)) {
			return SolicitationState.LB_COMPLETED.getDescription();

		} else if (state.equals(SolicitationState.PROGRESS)) {
			return SolicitationState.LB_PROGRESS.getDescription();

		} else {
			return SolicitationState.LB_FAIL.getDescription();
		}
	}
	
	public SolicitacaoServico getSelectedSolicitacao() {
		return selectedSolicitacao;
	}

	public void setSelectedSolicitacao(SolicitacaoServico selectedSolicitacao) {
		this.selectedSolicitacao = selectedSolicitacao;
	}

	public List<SolicitacaoServico> getSolicitacoes() {
		return solicitacoes;
	}

	public List<SolicitationState> getStatusList() {
		return statusList;
	}

	public SolicitacaoFilter getFilter() {
		return filter;
	}

	public void setFilter(SolicitacaoFilter filter) {
		this.filter = filter;
	}
}
