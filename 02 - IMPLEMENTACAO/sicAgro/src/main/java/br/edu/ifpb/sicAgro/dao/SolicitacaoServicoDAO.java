package br.edu.ifpb.sicAgro.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;

/**
 * Define operações específicas do dao de solicitação de serviço.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
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
	 * Método busca todas as solicitações por filtro.
	 * 
	 * Método implementado utilizando a API Criteria do JPA.
	 * 
	 * @param filter
	 * @return
	 */
	List<SolicitacaoServico> filter(SolicitacaoFilter filter);
	
	/**
	 * 
	 * @param funcionario
	 * @param status
	 * @return
	 */
	Long getCountSolicitationsByFuncionarioAndStatus(Funcionario funcionario, SolicitationState status);
	
	/**
	 * 
	 * @return
	 * @throws SicAgroException
	 */
	List<Object[]> getTotalSolicitacoesByMaquina() throws SicAgroException;
	
	/**
	 * 
	 * @param funcionario
	 * @return
	 * @throws SicAgroException
	 */
	Long getCountSolicitacoesByFuncionario(Funcionario funcionario) throws SicAgroException;
	
	/**
	 * 
	 * @param produtor
	 * @return
	 * @throws SicAgroException
	 */
	Long getCountSolicitacoesByProdutor(Produtor produtor) throws SicAgroException;
}
