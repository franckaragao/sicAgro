package br.edu.ifpb.sicAgro.services;

import br.edu.ifpb.sicAgro.model.Produtor;

public interface ProdutorService extends Service<Produtor, Long> {
	
	Long getTotalProdutores();
	
	Produtor findByCPF(String cpf);
	
}
