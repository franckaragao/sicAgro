package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;

public interface EntregaDAO extends DAO<Entrega, Long>{
	
	Long getTotalEntregas();

	List<Entrega> filter(EntregaFilter filter);
}
