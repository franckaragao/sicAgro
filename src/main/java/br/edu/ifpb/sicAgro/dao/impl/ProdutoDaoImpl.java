package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.ProdutoDAO;
import br.edu.ifpb.sicAgro.model.Produto;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Long> implements  ProdutoDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public List<Produto> findByName(String name) {
		Query query = entityManager.createNamedQuery("Produto.findByName");
		query.setParameter("name", "%" + name + "%");

		@SuppressWarnings("unchecked")
		List<Produto> result = query.getResultList();
		return result;
	}
}
