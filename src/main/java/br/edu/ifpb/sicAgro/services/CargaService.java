package br.edu.ifpb.sicAgro.services;

import br.edu.ifpb.sicAgro.model.Carga;

public interface CargaService extends Service<Carga, Long>{
	
	Long getTotalCargas();

}
