package br.edu.ifpb.sicAgro.dao.impl;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.PedidoSolicitacaoDAO;
import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;

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
}
