package br.edu.ifpb.sicAgro.services;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;

public interface EntregaService extends Service<Entrega, Long>{
	
	Long getTotalEntregas();
	
	List<Entrega> filter(Date dateEntrega, Produtor produtor, Long id);
	
}
