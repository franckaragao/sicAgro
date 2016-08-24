package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.VeiculoDAO;
import br.edu.ifpb.sicAgro.model.Veiculo;

public class VeiculoDaoImpl extends GenericDaoImpl<Veiculo, Long> implements
		VeiculoDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Veiculo> findByIdentification(String identification) {

		Query query = entityManager
				.createNamedQuery("Veiculo.findByIdentification");
		query.setParameter("identification", "%" + identification + "%");

		@SuppressWarnings("unchecked")
		List<Veiculo> result = query.getResultList();
		return result;
	}

}
