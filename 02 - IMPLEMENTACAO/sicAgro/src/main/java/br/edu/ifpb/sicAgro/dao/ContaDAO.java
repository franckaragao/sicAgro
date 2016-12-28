package br.edu.ifpb.sicAgro.dao;

import br.edu.ifpb.sicAgro.model.Conta;

/**
 * Interface define operações especificas de uma conta de usuário
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ContaDAO extends DAO<Conta, Long>{
	
	/**
	 * Busca no BD uma conta pelo email
	 * 
	 * @param mail - email
	 * 
	 * @return conta encontrada
	 */
	Conta findByMail(String mail);
	
	/**
	 * Busca uma conta pelo nome do usuário
	 * 
	 * @param userName - nome do usuário
	 * 
	 * @return - conta encontrada
	 */
	Conta findByUserName(String userName);

}
