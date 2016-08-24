package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Endereco;

public interface EnderecoService extends Service<Endereco, Long> {

	void getCities(Object state, Integer codState);

	List<String> listCities(Integer codCity);
	
	List<Endereco> findByAddress(String anddress);

}
