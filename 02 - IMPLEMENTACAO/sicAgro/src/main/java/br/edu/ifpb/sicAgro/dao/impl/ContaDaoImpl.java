package br.edu.ifpb.sicAgro.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.sicAgro.dao.ContaDAO;
import br.edu.ifpb.sicAgro.model.Conta;

/**
 * Classe responsável por implementar todas as operações definidas na interface @ContaDAO
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ContaDaoImpl extends GenericDaoImpl<Conta, Long> implements ContaDAO{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public Conta findByMail(String mail) {
		
		TypedQuery<Conta> query =  entityManager.createNamedQuery("Conta.findByMail", Conta.class);
		query.setParameter("mail", mail);
		
		return query.getSingleResult();
	}

	/**
	 * 
	 */
	@Override
	public Conta findByUserName(String userName) {
		try {
			TypedQuery<Conta> query = entityManager.createNamedQuery("conta.findByUserName", Conta.class);
			query.setParameter("userName", userName);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
}
