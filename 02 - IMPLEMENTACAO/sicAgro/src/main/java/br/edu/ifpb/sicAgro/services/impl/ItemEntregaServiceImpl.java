package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ItemEntregaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemEntrega;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ItemEntregaService;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ItemEntregaServiceImpl extends GenericServiceImpl<ItemEntrega, Long> implements ItemEntregaService{

	private static final long serialVersionUID = 1L;
	
	public ItemEntregaServiceImpl() {

	}
	
	@Inject
	public ItemEntregaServiceImpl(ItemEntregaDAO itemEntregaDAO) {
		this.dao = itemEntregaDAO;
	}
	
	/**
	 * 
	 */
	@Override
	public List<Object[]> getTotalPorProduto(){
		ItemEntregaDAO itemDao = (ItemEntregaDAO) this.dao;
		List<Object[]> result = null;
		try {
			result = itemDao.getTotalPorProduto();
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ItemEntrega> findByProduto(Produto produto) {
		ItemEntregaDAO itemDao = (ItemEntregaDAO) this.dao;
		return itemDao.findByProduto(produto);
	}
}
