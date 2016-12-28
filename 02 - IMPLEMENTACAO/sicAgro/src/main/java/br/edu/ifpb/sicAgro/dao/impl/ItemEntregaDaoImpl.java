package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.sicAgro.dao.ItemEntregaDAO;
import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemEntrega;
import br.edu.ifpb.sicAgro.model.Produto;

public class ItemEntregaDaoImpl extends GenericDaoImpl<ItemEntrega, Long> implements ItemEntregaDAO{

	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalPorProduto() throws SicAgroException{
		List<Object[]> result = null;

		try {
			Query query = entityManager.createNamedQuery("itemEntrega.getTotalPorProduto");
			result =  query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro a tentar recuperar o total de produtos"+e.getMessage());
		}
		return result;
	}


	@Override
	public List<ItemEntrega> findByProduto(Produto produto) {
		TypedQuery<ItemEntrega> query = entityManager.createNamedQuery("itemEntrega.findByProduto", ItemEntrega.class);
		query.setParameter("produto", produto);
		return query.getResultList();
	}


	@Override
	public Long findCountByTipoProduto(ProdutoType type) throws SicAgroException {
		Long result = 0l;;
		try {
			Query query = entityManager.createNamedQuery("itemEntrega.findByTipoProduto");
			query.setParameter("type", type);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro a tentar recuperar os itens de entrega pelo tipo "+e.getMessage());
		}
		return result;
	}
}
