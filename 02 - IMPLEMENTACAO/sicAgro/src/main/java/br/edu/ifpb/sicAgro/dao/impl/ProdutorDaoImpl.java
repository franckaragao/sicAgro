package br.edu.ifpb.sicAgro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;

public class ProdutorDaoImpl extends GenericDaoImpl<Produtor, Long> implements ProdutorDAO{

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Produtor> filter(ProdutorFilter filter){
		
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produtor> query = criteria.createQuery(Produtor.class);
		Root<Produtor> produtorRoot = query.from(Produtor.class);
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    query.select(produtorRoot);
	    
	    if(filter.getCod() != null && filter.getCod() > 0){
		    predicates.add(criteria.equal(produtorRoot.get("cod"), filter.getCod()));
	    }
	    if(filter.getName() != null){
	    	predicates.add(criteria.like(criteria.lower(produtorRoot.<String>get("name")),"%"+filter.getName()+"%"));
	    }
	    if(filter.getCpf() != null){
	    	predicates.add(criteria.like(produtorRoot.get("cpf"), filter.getCpf()));
	    }
	    if(predicates.size() > 0){
	    	query.where(criteria.and(predicates.toArray(new Predicate[]{})));
	    }
	    
	    return entityManager.createQuery(query).getResultList();
		
	}

	/**
	 * 
	 */
	@Override
	public List<Produtor> findByName(String name) {
		
		TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByName", Produtor.class);
		query.setParameter("name", "%" + name.toLowerCase() + "%");
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
