package br.edu.ifpb.sicAgro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;

public class ProdutorDaoImpl extends GenericDaoImpl<Produtor, Long> implements ProdutorDAO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
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
	 * @throws SicAgroException 
	 * 
	 */
	@Override
	public List<Produtor> findByName(String name) throws SicAgroException {
		List<Produtor> result = null;
		try {
			TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByName", Produtor.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar cosultar produtor pelo nome "+ e.getMessage());
		}
		return result;
	}

	/**
	 * @throws SicAgroException 
	 * 
	 */
	@Override
	public Long getTotalProdutores() throws SicAgroException {
		Long result = 0l;
		try {
			Query query = entityManager.createNamedQuery("Produtor.getTotalProdutores");
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar cosultar o total de produtores "+ e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Produtor findByCPF(String cpf) throws SicAgroException {
		Produtor produtor = null;
		try{
			TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByCPF", Produtor.class);
			query.setParameter("cpf", cpf);
			produtor = query.getSingleResult();
		}catch(NoResultException nr){
			return null;
		}
		catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar consultar o produtor pelo CPF "+ e.getMessage());
		}
		return produtor;
	}

	/**
	 * 
	 */
	@Override
	public boolean isCPFExists(String cpf) throws SicAgroException {
		Produtor produtor = null;
		try{
			produtor = findByCPF(cpf);
			
		}catch(NoResultException nr){
			return false;
		}
		catch (PersistenceException e) {
			throw new SicAgroException("Erro verificar se CPF existe "+ e.getMessage());
		}
		return produtor != null;
	}

	/**
	 * Consulta produtor pelo código usando namedQuery.
	 */
	@Override
	public Produtor findByCod(Integer cod) throws SicAgroException {
		Produtor result = null;
		try {
			TypedQuery<Produtor> query = entityManager.createNamedQuery("Produtor.findByCod", Produtor.class);
			query.setParameter("cod", cod);
			result = query.getSingleResult();
		} catch (PersistenceException e) {
			throw new SicAgroException("Erro ao tentar cosultar o produtor pelo cod "+ e.getMessage());
		}
		return result;
	}
}
