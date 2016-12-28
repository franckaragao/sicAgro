package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Produto;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ProdutoService extends Service<Produto, Long>{
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Produto> findByName(String name);

}
