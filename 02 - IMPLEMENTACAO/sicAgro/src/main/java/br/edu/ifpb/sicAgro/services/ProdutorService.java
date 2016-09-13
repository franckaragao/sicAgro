package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * Iterface padrão que define operações especificas de um produtor.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ProdutorService extends Service<Produtor, Long> {
	
	/**
	 * Consulta um produtor pelo nome.
	 * 
	 * @param name
	 * @return
	 */
	List<Produtor> findByName(String name);
	
	/**
	 * Consulta a quantidade de produtores.
	 * 
	 * @return
	 */
	Long getTotalProdutores();
	
	/**
	 * Consulta um produtor pelo nome.
	 * 
	 * @param cpf
	 * @return
	 */
	Produtor findByCPF(String cpf);
	
	/**
	 * Consulta um proutor de pelo código.
	 * 
	 * @param cod
	 * @return
	 */
	Produtor finByCod(Integer cod);
	
	/**
	 * Filtro de um produtor.
	 * 
	 * @param filter
	 * @return
	 */
	List<Produtor> filter(ProdutorFilter filter);
	
}
