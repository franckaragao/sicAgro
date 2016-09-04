package br.edu.ifpb.sicAgro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.sicAgro.dao.VeiculoDAO;
import br.edu.ifpb.sicAgro.filter.VeiculoFilter;
import br.edu.ifpb.sicAgro.model.Veiculo;

public class VeiculoDaoImpl extends GenericDaoImpl<Veiculo, Long> implements VeiculoDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Veiculo> findByIdentification(String identification) {

		Query query = entityManager.createNamedQuery("Veiculo.findByIdentification");
		query.setParameter("identification", "%" + identification + "%");

		@SuppressWarnings("unchecked")
		List<Veiculo> result = query.getResultList();
		return result;
	}

	@Override
	public List<Veiculo> filter(VeiculoFilter filter) {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Veiculo> query = criteria.createQuery(Veiculo.class);
		Root<Veiculo> veisuloRoot = query.from(Veiculo.class);
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    query.select(veisuloRoot);
	    
	    if(filter.getId() != null)
	    	predicates.add(criteria.equal(veisuloRoot.get("id"), filter.getId()));

	    if(filter.getIdentification() != null)
	    	predicates.add(criteria.like(veisuloRoot.get("identification"), "%"+filter.getIdentification().toLowerCase()+"%"));
	    
	    if(filter.getType() != null)
	    	predicates.add(criteria.equal(veisuloRoot.get("type"), filter.getType()));

	    if(predicates.size() > 0)
	    	query.where(criteria.and(predicates.toArray(new Predicate[]{})));

	    return entityManager.createQuery(query).getResultList();
	}
}
