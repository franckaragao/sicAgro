package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.model.Produto;

public interface ItemCargaDAO extends DAO<ItemCarga, Long>{
	
	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalPorProduto() throws SicAgroException;
	
	/**
	 * 
	 * @return
	 * @throws SicAgroException
	 */
	List<Object[]> getProdutosAndDates() throws SicAgroException;
	
	/**
	 * 
	 * @return
	 * @throws SicAgroException
	 */
	Long getQuantidadeByProduto(Produto produto) throws SicAgroException;

}
