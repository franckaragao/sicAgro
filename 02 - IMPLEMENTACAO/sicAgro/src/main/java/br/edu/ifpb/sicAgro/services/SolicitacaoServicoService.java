package br.edu.ifpb.sicAgro.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface SolicitacaoServicoService extends Service<SolicitacaoServico, Long>{
	
	/**
	 * 
	 * @param nDays
	 * @param state
	 * @return
	 */
	Map<Date, Integer> getSolicitacoesPorPeriodo(Integer nDays, SolicitationState state);
	
	/**
	 * 
	 * @return
	 */
	Long getTotalSolicitations();
	
	/**
	 * 
	 * @param funcionario
	 * @return
	 */
	List<SolicitacaoServico> getSolicitacoesByFuncionario(Funcionario funcionario);

	
	/**
	 * Método busca todas as solicitações de um veículo especifico em um
	 * intervalo de datas passados por parametro.
	 * 
	 * @param veiculo
	 * @param dateInit
	 * @param dateEnd
	 * @param state
	 * @return
	 */
	List<SolicitacaoServico> filter(SolicitacaoFilter filter);
	
	/**
	 * 
	 * @param funcionario
	 * @param status
	 * @return
	 */
	Long getCountSolicitationsByFuncionario(Funcionario funcionario, SolicitationState status);
	
	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalSolicitacoesByMaquina();
	
}
