package br.edu.ifpb.sicAgro.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;

/**
 * 
 * @author franck
 *
 */
public interface SolicitacaoServicoDAO extends DAO<SolicitacaoServico, Long>{
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	List<SolicitacaoServico> getSolicitationByDate(Date date);
	
	/**
	 * 
	 * @param date
	 * @param completed
	 * @return
	 */
	List<SolicitacaoServico> getSolicitationByDateCompleted(Date date, Boolean completed);
	
	
	/**
	 * 
	 * @param nDays
	 * @return
	 */
	Map<Date, Integer> getSolicitacoesPorPeriodo(Integer nDays, SolicitationState state);
	
	/**
	 * 
	 * @return
	 */
	Long getTotalSolicitations();
	
	List<SolicitacaoServico> getSolicitacoesByFuncionario(Funcionario funcionario);
	
	/**
	 * Método busca todas as solicitações de um veículo especifico em um
	 * intervalo de datas passados por parametro.
	 * 
	 * Método implementado utilizando a API Criteria do JPA
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
	
	List<Object[]> getTotalSolicitacoesByMaquina() throws SicAgroException;
}
