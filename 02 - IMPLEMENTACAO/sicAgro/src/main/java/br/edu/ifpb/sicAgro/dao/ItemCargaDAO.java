package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemCarga;

public interface ItemCargaDAO extends DAO<ItemCarga, Long>{
	
	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalPorProduto() throws SicAgroException;

}
