package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Endereco;

public interface EnderecoDAO extends DAO<Endereco, Long>{
	
	List<String> getCities(Integer codUF);
	
	List<Endereco> findByAddress(String anddress);

}
