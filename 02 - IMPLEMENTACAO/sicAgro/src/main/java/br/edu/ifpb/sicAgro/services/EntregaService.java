package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * 
 * @author franck
 *
 */
public interface EntregaService extends Service<Entrega, Long>{
	
	/**
	 * 
	 * @return
	 */
	Long getTotalEntregas();
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	List<Entrega> filter(EntregaFilter filter);
	
	/**
	 * 
	 * @param produtor
	 * @return
	 */
	Long getCountEntregasByProdutor(Produtor produtor);
}
