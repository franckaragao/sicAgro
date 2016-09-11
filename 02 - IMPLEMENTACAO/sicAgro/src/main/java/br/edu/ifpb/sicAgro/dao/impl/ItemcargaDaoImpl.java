package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.ItemCargaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.model.Produto;

/**
 * Classe Dao responsável por implementar todas as funcionalidades definidas na
 * intarface @ItemCargaDAO
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ItemcargaDaoImpl extends GenericDaoImpl<ItemCarga, Long> implements
		ItemCargaDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalPorProduto() throws SicAgroException {
		List<Object[]> result = null;

		try {
			Query query = entityManager.createNamedQuery("itemCarga.getTotalPorProduto");
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro a tentar recuperar o total de produtos "+ e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getProdutosAndDates() throws SicAgroException {
		List<Object[]> result = null;
		try {
			Query query = entityManager.createNamedQuery("itemCarga.getProdutosAndDates");
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro a tentar recuperar o total de produtos por data "+ e.getMessage());
		}
		return result;
	}

	@Override
	public Long getQuantidadeByProduto(Produto produto) throws SicAgroException {
		Long result = 0l;
		try {
			Query query = entityManager.createNamedQuery("itemcarga.getQuantidadeByProduto");
			query.setParameter("produto", produto);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar obter a quantidade de itens de cargas por produto"+e.getMessage());
		}
		return result;
	}

}
