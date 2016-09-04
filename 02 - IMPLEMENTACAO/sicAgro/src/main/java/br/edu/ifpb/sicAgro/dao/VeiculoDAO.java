package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.VeiculoFilter;
import br.edu.ifpb.sicAgro.model.Veiculo;

public interface VeiculoDAO extends DAO<Veiculo, Long> {

	List<Veiculo> findByIdentification(String identification);

	/**
	 * Filtro por utilizando atributos basicos de um veículo.
	 * 
	 * Filtro utilizando API Criteria.
	 * 
	 * @param filter - classe de filtro com atributos básicos para filtro
	 * @return - lista contento resultados do filtro
	 */
	List<Veiculo> filter(VeiculoFilter filter);
}
