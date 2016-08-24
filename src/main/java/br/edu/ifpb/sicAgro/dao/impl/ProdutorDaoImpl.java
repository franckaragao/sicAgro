package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.model.Produtor;

public class ProdutorDaoImpl extends GenericDaoImpl<Produtor, Long> implements ProdutorDAO{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public List<Produtor> findByName(String name) {
		
		TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByName", Produtor.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	/**
	 * 
	 */
	@Override
	public Long getTotalProdutores() {
		
		Query query = entityManager.createNamedQuery("Produtor.getTotalProdutores");
		
		return ((Long) query.getSingleResult());
	}

	@Override
	public Produtor findByCPF(String cpf) {
		try{
			
			TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByCPF", Produtor.class);
			query.setParameter("cpf", cpf);
			
			return query.getSingleResult();
			
		}catch(NoResultException nr){
			return null;
		}
	}

	@Override
	public boolean isCPFExists(String cpf) {
		try{

			Produtor produtor = findByCPF(cpf);

			return produtor != null;
			
		}catch(NoResultException nr){
			return false;
		}
	}
}
