package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.ItemCarga;

/**
 * Interface responsável por definir todas as operações responsáveis por
 * gerenciar um item de carga
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ItemCargaService extends Service<ItemCarga, Long> {

	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalPorProduto();

}
