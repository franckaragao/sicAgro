package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;

public interface ProdutorService extends Service<Produtor, Long> {
	
	List<Produtor> findByName(String name);
	
	Long getTotalProdutores();
	
	Produtor findByCPF(String cpf);
	
	List<Produtor> filter(ProdutorFilter filter);
	
}
