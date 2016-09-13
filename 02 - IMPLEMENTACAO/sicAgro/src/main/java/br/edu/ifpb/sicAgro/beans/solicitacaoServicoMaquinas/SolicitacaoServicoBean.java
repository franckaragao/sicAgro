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

/**
 * manager bean responsável por gerenciar listagem e filtro 
 * de uma solicitação.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
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

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.listSolicitacoes();
		this.updateStates();
		statusList.add(SolicitationState.COMPLETED);
		statusList.add(SolicitationState.FAIL);
		statusList.add(SolicitationState.PROGRESS);
	}

	/**
	 * <pre>
	 * Utilizado como solução pra conseguir passar um paramtro apartir do 
	 * manager bean, devido a forma que a linha da datatable é selecionada,
	 * desta forma sem usar um componente que tenha um outcome, tem-se a necessidade
	 * de fazer o manager bean redireiconar para outra página, diante isso o parametro
	 * deve ser passado do manager bean.
	 * </pre>
	 */
	public void renderTo() {
		JSFUtils.rederTo("solicitacaoView.xhtml");
		JSFUtils.setParam("solicitacao", selectedSolicitacao);
	}

	/**
	 * Metódo  necessário devido que, mais de um papel acessa a lista de 
	 * solicitações, com acessos de dados diferentes.
	 */
	public void listSolicitacoes() {
		if (isUserLoggedIsDriver()) {
			this.solicitacoes = service.getSolicitacoesByFuncionario(conta.getFuncionario());
		} else {
			this.solicitacoes = service.findAll();
		}
	}

	/**
	 * Filtro de busca.
	 */
	public void filter() {
		if (isUserLoggedIsDriver()) {
			solicitacoes = service.filter(filter);
		} else {
			solicitacoes = service.filter(filter);
		}
	}

	/**
	 * Atualiza os estatus das solicitações antes de exibir para 
	 * o usuário, já que uma solicitações pode mudar de status diante da data.
	 * 
	 */
	public void updateStates() {
		for (SolicitacaoServico solicitacaoServico : solicitacoes) {
			service.update(solicitacaoServico);
		}
	}

	/**
	 * Verifica se o usuário online é um motorista.
	 * 
	 * @return
	 */
	public boolean isUserLoggedIsDriver() {
		if (conta.getUserRole().equals(UserRole.DRIVER))
			return true;
		return false;
	}

	/**
	 * recupera os veiculos, utilizado no filtro de solicitação.
	 * 
	 * @return
	 */
	public List<Veiculo> getVeiculos() {
		return veiculoService.findAll();
	}

	/**
	 * recupera os fucionários, é utilizado no filtro de solicitações.
	 * 
	 * @return
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarioService.findAll();
	}
	
	/**
	 * Retorna a quantidade de solicitações abertas para um funcionário.
	 * 
	 * @return
	 */
	public Long getCountSolicitationsByFuncionario(){
		Long n = service.getCountSolicitationsByFuncionarioAndStatus(conta.getFuncionario(), SolicitationState.PROGRESS);
		return n;
	}
	
	/**
	 * Verifica qual label deve ser utilizada na lista de solicitações.
	 * è exibido de acordo com o status da solicitação.
	 *  
	 * @param state
	 * @return
	 */
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
