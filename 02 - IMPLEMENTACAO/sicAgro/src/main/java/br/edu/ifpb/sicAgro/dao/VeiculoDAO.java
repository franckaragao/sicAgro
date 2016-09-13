package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Veiculo;

public interface VeiculoDAO extends DAO<Veiculo, Long> {

	List<Veiculo> findByIdentification(String identification);

}
