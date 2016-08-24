package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Produto;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ProdutoDAO extends DAO<Produto, Long>{
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Produto> findByName(String name);

}
