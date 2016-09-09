package br.edu.ifpb.sicAgro.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.DateUtils;

import br.edu.ifpb.sicAgro.dao.PedidoSolicitacaoDAO;
import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class PedidoSolicitacaoDaoImpl extends GenericDaoImpl<PedidoSolicitacao, Long> implements PedidoSolicitacaoDAO{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public Long getTotalPedidosByStatutus(PedidoStatus status) {
		Long result = null;
		try {
			Query query = entityManager.createNamedQuery("pedidoSolicitacao.getTotalByStatus");
			query.setParameter("status", status);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Long getTotalPedidosByMessage() {
		Long result = null;
		try {
			Query query = entityManager.createNamedQuery("pedidoSolicitacao.getTotalByMessages");
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<PedidoSolicitacao> findPedidosByProdutor(Produtor produtor) {
		List<PedidoSolicitacao> result = null;
		try {
			TypedQuery<PedidoSolicitacao> query = entityManager.createNamedQuery("pedidoSolicitacao.findPedidosByProdutor", PedidoSolicitacao.class);
			query.setParameter("produtor", produtor);
			result = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Long getTotalPedidosByDateAndEstatus(Date date, PedidoStatus status){
		Long result = 0l;
		try {
			Query query = entityManager.createNamedQuery("pedidoSolicitacao.getTotalByDateAndStatus");
			query.setParameter("dataPedido", date);
			query.setParameter("status", status);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Map<Date, Integer> getPedidosPorPeriodo(Integer nDays, PedidoStatus status) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, (nDays - 1) * -1);

		Map<Date, Integer> result = createMapEmpty((nDays - 1), dataInicial);

		for (Date date : result.keySet()) {

			if (status.equals(PedidoStatus.NOT_ACCEPTED)) {
				Integer count = getTotalPedidosByDateAndEstatus(date, status).intValue();
				result.put(date, count);

			} else if (status.equals(PedidoStatus.ACCEPTED)) {
				Integer count = getTotalPedidosByDateAndEstatus(date, status).intValue();
				result.put(date, count);
				
			} else if (status.equals(PedidoStatus.PROGRESS)) {
				Integer count = getTotalPedidosByDateAndEstatus(date, status).intValue();
				result.put(date, count);

			} else {
				Integer count = getTotalPedidosByDateAndEstatus(date, status).intValue();
				result.put(date, count);
			}
		}
		return result;
	}
	
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
}
