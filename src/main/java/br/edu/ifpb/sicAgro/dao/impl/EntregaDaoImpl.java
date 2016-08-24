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
	
	public List<Entrega> filter(Date dateEntrega, Produtor produtor, Long id){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entrega> query = criteriaBuilder.createQuery(Entrega.class);
		Root<Entrega> entregaRoot = query.from(Entrega.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		query.select(entregaRoot);
		
		if(dateEntrega != null)
			predicates.add(criteriaBuilder.equal(entregaRoot.get("dateEntrega"), dateEntrega));
		
		if(produtor != null)
			predicates.add(criteriaBuilder.equal(entregaRoot.get("produtor"), produtor));
		
		if(id != null)
			predicates.add(criteriaBuilder.equal(entregaRoot.get("id"), id));
		
	    if(predicates.size() > 0)
	    	query.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{})));
		
		return entityManager.createQuery(query).getResultList();
		
	}

}
