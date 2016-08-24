package br.edu.ifpb.sicAgro.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.CargaDAO;
import br.edu.ifpb.sicAgro.model.Carga;
import br.edu.ifpb.sicAgro.services.CargaService;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class CargaServiceImpl extends GenericServiceImpl<Carga, Long> implements CargaService{
	
	public CargaServiceImpl() {
	}
	
	@Inject
	public CargaServiceImpl(CargaDAO cargaDAO) {
		this.dao = cargaDAO;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8745156525979136674L;

	@Override
	public Long getTotalCargas() {
		CargaDAO cargaDAO = (CargaDAO) this.dao;
		return cargaDAO.getTotalCargas();
	}

}
