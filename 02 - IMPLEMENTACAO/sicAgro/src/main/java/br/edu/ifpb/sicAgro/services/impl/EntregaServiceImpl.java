package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.EntregaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.EntregaService;

public class EntregaServiceImpl extends GenericServiceImpl<Entrega, Long> implements EntregaService{

	private static final long serialVersionUID = 1L;
	
	public EntregaServiceImpl() {
	}
	
	/**
	 * 
	 * @param entregaDAO
	 */
	@Inject
	public EntregaServiceImpl(EntregaDAO entregaDAO) {
		this.dao = entregaDAO;
	}

	/**
	 * 
	 */
	@Override
	public Long getTotalEntregas() {
		EntregaDAO entregaDAO = (EntregaDAO) this.dao;
		return entregaDAO.getTotalEntregas();
	}

	/**
	 * 
	 */
	@Override
	public List<Entrega> filter(EntregaFilter filter) {
		EntregaDAO entregaDAO = (EntregaDAO) this.dao;
		return entregaDAO.filter(filter);
	}
	
	/**
	 * 
	 */
	@Override
	public Long getCountEntregasByProdutor(Produtor produtor) {
		EntregaDAO entregaDAO = (EntregaDAO) this.dao;
		Long result = 0l;
		try {
			result = entregaDAO.getCountEntregasByProdutor(produtor);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}
}
