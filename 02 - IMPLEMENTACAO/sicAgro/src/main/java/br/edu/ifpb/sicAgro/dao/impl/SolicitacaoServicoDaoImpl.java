package br.edu.ifpb.sicAgro.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.time.DateUtils;

import br.edu.ifpb.sicAgro.dao.SolicitacaoServicoDAO;
import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;

/**
 * Classe que implementa todas as operações de CRUD 
 * definidas na interface @SolicitacaoServicoDAO.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class SolicitacaoServicoDaoImpl extends GenericDaoImpl<SolicitacaoServico, Long> implements SolicitacaoServicoDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * Método busca todas as solicitações por um filtro personalizado.
	 * 
	 * Método implementado utilizando a API Criteria do JPA, devido a haver 
	 * uns filtros na consulta.
	 * 
	 * @return lista de solicitações encontradas no BD
	 */
	public List<SolicitacaoServico> filter(SolicitacaoFilter filter) {

		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<SolicitacaoServico> query = criteria.createQuery(SolicitacaoServico.class);
		Root<SolicitacaoServico> solicitRoot = query.from(SolicitacaoServico.class);
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    query.select(solicitRoot);
	    
	    if(filter.getVeiculo() != null)
	    	predicates.add(criteria.equal(solicitRoot.get("veiculo"), filter.getVeiculo()));

	    if(filter.getResumoSolicitacao() != null)
	    	predicates.add(criteria.like(criteria.lower(solicitRoot.<String>get("resumoServico")), "%"+filter.getResumoSolicitacao()+"%"));
	    
	    if(filter.getFuncionario() != null)
	    	predicates.add(criteria.equal(solicitRoot.get("funcionario"), filter.getFuncionario()));

	    if(filter.getDateInit() != null && filter.getDateEnd() != null)
	    	predicates.add(criteria.between(solicitRoot.get("dateSolicitation"), filter.getDateInit(), filter.getDateEnd()));
	    
	    if(filter.getStatus() != null)
	    	predicates.add(criteria.equal(solicitRoot.get("state"), filter.getStatus()));
	    
	    if(predicates.size() > 0)
	    	query.where(criteria.and(predicates.toArray(new Predicate[]{})));

	    return entityManager.createQuery(query).getResultList();
	}
	
	/**
	 * 
	 */
	@Override
	public List<SolicitacaoServico> getSolicitacoesByFuncionario(Funcionario funcionario) {
		TypedQuery<SolicitacaoServico> query = entityManager.createNamedQuery("solicitacaoServico.findByFuncionario", SolicitacaoServico.class);
		query.setParameter("funcionario", funcionario);

		return query.getResultList();
		
	}

	/**
	 * 
	 */
	@Override
	public Long getTotalSolicitations() {
		Query query = entityManager.createNamedQuery("solicitacaoServico.getTotalSolicitations");
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 */
	@Override
	public List<SolicitacaoServico> getSolicitationByDate(Date date) {

		TypedQuery<SolicitacaoServico> query = entityManager.createNamedQuery(
				"solicitacaoServico.findByDate", SolicitacaoServico.class);
		query.setParameter("dateSolicitation", date);

		return query.getResultList();
	}

	/**
	 * 
	 */
	public List<SolicitacaoServico> getSolicitationByDateCompleted(Date date, Boolean completed) {

		TypedQuery<SolicitacaoServico> query = entityManager.createNamedQuery(
				"solicitacaoServico.findSolicitationByDateCompleted",
				SolicitacaoServico.class);
		query.setParameter("dateSolicitation", date);
		query.setParameter("completed", completed);

		return query.getResultList();
	}

	/**
	 * 
	 * @param date
	 * @param completed
	 * @param dateForRealization
	 * @return
	 */
	private Integer getCountSolicitationByDateNotCompleted(Date date, Boolean completed, Date dateForRealization) {

		Query query = entityManager.createNamedQuery("solicitacaoServico.getCountSolicitationByDateNotCompleted");
		query.setParameter("dateSolicitation", date);
		query.setParameter("completed", completed);
		query.setParameter("dateForRealization", dateForRealization);

		return ((Long) query.getSingleResult()).intValue();
	}

	/**
	 * 
	 * @param date
	 * @param completed
	 * @param dateForRealization
	 * @return
	 */
	private Integer getCountSolicitationByDateInCurrent(Date date, Boolean completed, Date dateForRealization) {

		Query query = entityManager
				.createNamedQuery("solicitacaoServico.getCountSolicitationByDateInCurrent");
		query.setParameter("dateSolicitation", date);
		query.setParameter("completed", completed);
		query.setParameter("dateForRealization", dateForRealization);

		return ((Long) query.getSingleResult()).intValue();
	}

	/**
	 * 
	 */
	public Map<Date, Integer> getSolicitacoesPorPeriodo(Integer nDays, SolicitationState state) {

		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, (nDays - 1) * -1);

		Map<Date, Integer> result = createMapEmpty((nDays - 1), dataInicial);

		for (Date date : result.keySet()) {

			if (state.equals(SolicitationState.FAIL)) {
				Integer count = getCountSolicitationByDateNotCompleted(date,
						false, new Date());
				result.put(date, count);

			} else if (state.equals(SolicitationState.COMPLETED)) {
				Integer count = getSolicitationByDateCompleted(date, true)
						.size();
				result.put(date, count);

			} else {
				Integer count = getCountSolicitationByDateInCurrent(date,
						false, new Date());
				result.put(date, count);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param nDays
	 * @param dateInitial
	 * @return
	 */
	private Map<Date, Integer> createMapEmpty(Integer nDays,
			Calendar dateInitial) {
		dateInitial = (Calendar) dateInitial.clone();
		Map<Date, Integer> mapInitial = new TreeMap<>();

		for (int i = 0; i <= nDays; i++) {
			mapInitial.put(dateInitial.getTime(), 0);
			dateInitial.add(Calendar.DAY_OF_MONTH, 1); // add + um dia
		}

		return mapInitial;
	}

	/**
	 * 
	 */
	@Override
	public Long getCountSolicitationsByFuncionarioAndStatus(Funcionario funcionario, SolicitationState status) {
		Query query = entityManager.createNamedQuery("solicitacaoServico.getCountSolicitationByFuncionarioAndStatus");
		query.setParameter("funcionario", funcionario);
		query.setParameter("state", status);
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalSolicitacoesByMaquina() throws SicAgroException{
		List<Object[]> result = null;

		try {
			Query query = entityManager.createNamedQuery("solicitacaoServico.getTotalPorMaquina");
			result =  query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro a tentar recuperar o total de solicitações por máquina "+e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Long getCountSolicitacoesByFuncionario(Funcionario funcionario) throws SicAgroException {
		Long result = 0l;
		try {
			Query query = entityManager.createNamedQuery("solicitacaoServico.getCountByFuncionario");
			query.setParameter("funcionario", funcionario);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar consultar a quantidade de solic. por funcionario."+e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Long getCountSolicitacoesByProdutor(Produtor produtor) throws SicAgroException {
		Long result = 0l;
		try {
			Query query = entityManager.createNamedQuery("solicitacao.getCountByProdutor");
			query.setParameter("produtor", produtor);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar consultar a quantidade de produtores." + e.getMessage());
		}
		return result;
	}
}
