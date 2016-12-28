package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Funcionario;

/**
 * 
 * @author franck
 *
 */
public interface FuncionarioService extends Service<Funcionario, Long>{
	
	List<Funcionario> findByName(String name);
	
	List<Funcionario> findDriversByName(String name);

}
