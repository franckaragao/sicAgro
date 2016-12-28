package br.edu.ifpb.sicAgro.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;

/**
 * Interface que define operações específicas de uma solicitação de serviço.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface SolicitacaoServicoService extends Service<SolicitacaoServico, Long>{
	
	/**
	 * Obtém quantidade e data de solicitações em um intervalo de 
	 * tempo.
	 * 
	 * @param nDays
	 * @param state
	 * @return
	 */
	Map<Date, Integer> getSolicitacoesPorPeriodo(Integer nDays, SolicitationState state);
	
	/**
	 * Consulta a quantidade total de solicitações.
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
	 * Método busca todas as solicitações por filtro
	 * 
	 * @param filter
	 * @return
	 */
	List<SolicitacaoServico> filter(SolicitacaoFilter filter);
	
	/**
	 * Obtém as solicitações de um determinado funcionario e por um status.
	 * 
	 * @param funcionario
	 * @param status
	 * @return
	 */
	Long getCountSolicitationsByFuncionarioAndStatus(Funcionario funcionario, SolicitationState status);
	
	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalSolicitacoesByMaquina();
	
	/**
	 * 
	 * @param funcionario
	 * @return
	 */
	Long getCountSolicitacoesByFuncionario(Funcionario funcionario);
	
	/**
	 * 
	 * @param produtor
	 * @return
	 */
	Long getCountSolicitacoesByProdutor(Produtor produtor);
}
