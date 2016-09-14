package br.edu.ifpb.sicAgro.services;

import br.edu.ifpb.sicAgro.model.Carga;

/**
 * Interface específica para definição de operações específcicas para uma carga.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface CargaService extends Service<Carga, Long>{
	
	/**
	 * Obtémm a quantidade de cargas.
	 * 
	 * @return numero de cargas.
	 */
	Long getTotalCargas();

}
