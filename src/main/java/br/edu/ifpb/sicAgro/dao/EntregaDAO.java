package br.edu.ifpb.sicAgro.dao;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;

public interface EntregaDAO extends DAO<Entrega, Long>{
	
	Long getTotalEntregas();

	List<Entrega> filter(Date dateEntrega, Produtor produtor, Long id);
}
