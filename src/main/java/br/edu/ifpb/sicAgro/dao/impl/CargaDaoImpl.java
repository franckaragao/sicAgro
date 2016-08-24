package br.edu.ifpb.sicAgro.dao.impl;

import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.CargaDAO;
import br.edu.ifpb.sicAgro.model.Carga;


/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class CargaDaoImpl extends GenericDaoImpl<Carga, Long> implements CargaDAO{

	private static final long serialVersionUID = -1933623088336048459L;

	@Override
	public Long getTotalCargas() {
		Query query = entityManager.createNamedQuery("Carga.getTotalCargas");
		return (Long) query.getSingleResult();
	}

}
