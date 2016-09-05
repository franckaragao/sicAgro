package br.edu.ifpb.sicAgro.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.sicAgro.dao.EntregaDAO;
import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class EntregaDaoImpl extends GenericDaoImpl<Entrega, Long> implements EntregaDAO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	@Override
	public Long getTotalEntregas() {
		Query query = entityManager.createNamedQuery("entrega.getTotalEntregas");
		return (Long) query.getSingleResult();
	}
	
	/**
	 * 
	 */
	public List<Entrega> filter(EntregaFilter filter){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entrega> query = criteriaBuilder.createQuery(Entrega.class);
		Root<Entrega> entregaRoot = query.from(Entrega.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		query.select(entregaRoot);
		
		
		if(filter.getId() != null)
			predicates.add(criteriaBuilder.equal(entregaRoot.<Long>get("id"), filter.getId()));
		
		if(filter.getProdutor() != null)
			predicates.add(criteriaBuilder.equal(entregaRoot.<Produtor>get("produtor"), filter.getProdutor()));
		
	    if(filter.getDateInit() != null && filter.getDateEnd() != null)
	    	predicates.add(criteriaBuilder.between(entregaRoot.<Date>get("dateEntrega"), filter.getDateInit(), filter.getDateEnd()));
		
	    if(predicates.size() > 0)
	    	query.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{})));
		
		return entityManager.createQuery(query).getResultList();
	}
}
