package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.ItemEntregaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemEntrega;

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
}
