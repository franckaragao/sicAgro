package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Veiculo;

/**
 * Interface define operações específicas de um veiculo no sistema.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface VeiculoService extends Service<Veiculo, Long>{
	
	
	List<Veiculo> findByIdentification(String identification);
	
	void setHorimetroVeiculo(Veiculo veiculo, Integer timesWorked);

}
