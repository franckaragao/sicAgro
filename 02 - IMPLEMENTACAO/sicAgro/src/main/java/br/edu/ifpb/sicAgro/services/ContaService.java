package br.edu.ifpb.sicAgro.services;

import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ContaService extends Service<Conta, Long>{
	
	/**
	 * 
	 * @param mail
	 * @return
	 */
	Conta findByMail(String mail);
	
	/**
	 * 
	 * @param conta
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	void criptografarSenha(Conta conta) throws SicAgroExceptionHandler;
	
	Conta findByUserName(String userName);

}
