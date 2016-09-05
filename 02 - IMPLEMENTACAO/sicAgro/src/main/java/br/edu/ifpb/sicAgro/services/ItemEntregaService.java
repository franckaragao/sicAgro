package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.ItemEntrega;
import br.edu.ifpb.sicAgro.model.Produto;

public interface ItemEntregaService extends Service<ItemEntrega, Long>{

	List<Object[]> getTotalPorProduto();
	
	List<ItemEntrega> findByProduto(Produto produto);
}
