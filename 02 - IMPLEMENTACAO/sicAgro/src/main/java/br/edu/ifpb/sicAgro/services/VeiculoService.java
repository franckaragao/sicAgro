package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.filter.VeiculoFilter;
import br.edu.ifpb.sicAgro.model.Veiculo;

public interface VeiculoService extends Service<Veiculo, Long>{
	
	List<Veiculo> filter(VeiculoFilter filter);
	
	List<Veiculo> findByIdentification(String identification);
	
	void setHorimetroVeiculo(Veiculo veiculo, Integer timesWorked);

}
