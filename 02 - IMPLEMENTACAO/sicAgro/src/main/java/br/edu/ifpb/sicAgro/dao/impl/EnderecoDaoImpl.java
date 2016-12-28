package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.sicAgro.dao.EnderecoDAO;
import br.edu.ifpb.sicAgro.model.Endereco;

public class EnderecoDaoImpl extends GenericDaoImpl<Endereco, Long> implements EnderecoDAO{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCities(Integer codUF) {
		Query createQuery;
        createQuery = entityManager.createNativeQuery("SELECT c.nome FROM cidades c where c.estado = " + codUF);
        return createQuery.getResultList();
	}

	@Override
	public List<Endereco> findByAddress(String anddress) {
		TypedQuery<Endereco> query = entityManager.createNamedQuery("Endereco.findByAddress", Endereco.class);
		query.setParameter("address", "%" + anddress + "%");

		return query.getResultList();
	}

}
