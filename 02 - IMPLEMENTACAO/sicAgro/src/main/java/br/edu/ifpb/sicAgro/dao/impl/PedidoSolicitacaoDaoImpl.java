package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
}
