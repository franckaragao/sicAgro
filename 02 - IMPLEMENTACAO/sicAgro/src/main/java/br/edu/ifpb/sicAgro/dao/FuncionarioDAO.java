package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.model.Funcionario;

/**
 * Interaface responsável por definir operações especificas de acesso a banco de
 * dados de um funcionário
 * 
 */
public interface FuncionarioDAO extends DAO<Funcionario, Long> {
	
	List<Funcionario> findByName(String name);

}
