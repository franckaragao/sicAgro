package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;

public interface EntregaService extends Service<Entrega, Long>{
	
	Long getTotalEntregas();
	
	List<Entrega> filter(EntregaFilter filter);
	
}
