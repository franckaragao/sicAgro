package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemEntrega;
import br.edu.ifpb.sicAgro.model.Produto;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ItemEntregaService extends Service<ItemEntrega, Long>{

	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalPorProduto();
	
	/**
	 * 
	 * @param produto
	 * @return
	 */
	List<ItemEntrega> findByProduto(Produto produto);
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws SicAgroException
	 */
	Long findCountByTipoProduto(ProdutoType type);
}
