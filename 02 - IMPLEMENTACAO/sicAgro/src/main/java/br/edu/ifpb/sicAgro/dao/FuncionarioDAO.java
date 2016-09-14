package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Funcionario;

/**
 * Interaface responsável por definir operações especificas de acesso a banco de
 * dados de um funcionário
 * 
 */
public interface FuncionarioDAO extends DAO<Funcionario, Long> {
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Funcionario> findByName(String name);

	/**
	 * 
	 * @param name
	 * @param user
	 * @return
	 * @throws SicAgroException
	 */
	List<Funcionario> findDriversByName(String name, UserRole user) throws SicAgroException;
	
	/**
	 * 
	 * @param cpf
	 * @return
	 * @throws SicAgroException
	 */
	Funcionario findByCPF(String cpf) throws SicAgroException;
}
