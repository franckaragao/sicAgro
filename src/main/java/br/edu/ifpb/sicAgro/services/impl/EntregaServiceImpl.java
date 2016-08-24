package br.edu.ifpb.sicAgro.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.EntregaDAO;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.EntregaService;

public class EntregaServiceImpl extends GenericServiceImpl<Entrega, Long> implements EntregaService{

	private static final long serialVersionUID = 1L;
	
	public EntregaServiceImpl() {
	}
	
	@Inject
	public EntregaServiceImpl(EntregaDAO entregaDAO) {
		this.dao = entregaDAO;
	}

	@Override
	public Long getTotalEntregas() {
		EntregaDAO entregaDAO = (EntregaDAO) this.dao;
		return entregaDAO.getTotalEntregas();
	}

	@Override
	public List<Entrega> filter(Date dateEntrega, Produtor produtor, Long id) {
		EntregaDAO entregaDAO = (EntregaDAO) this.dao;
		return entregaDAO.filter(dateEntrega, produtor, id);
	}

}
