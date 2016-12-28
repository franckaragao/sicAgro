package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ItemCargaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ItemCargaService;

/**
 * Classe de serviço responsável por implementar todos os métodos definidos pela
 * interface @ItemCargaService
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ItemCargaServiceImpl extends GenericServiceImpl<ItemCarga, Long>
		implements ItemCargaService {

	private static final long serialVersionUID = 1L;

	public ItemCargaServiceImpl() {
	}

	@Inject
	public ItemCargaServiceImpl(ItemCargaDAO itemCargaDAO) {
		this.dao = itemCargaDAO;
	}

	/**
	 * 
	 */
	@Override
	public List<Object[]> getTotalPorProduto() {
		ItemCargaDAO itemDAO = (ItemCargaDAO) this.dao;
		List<Object[]> result = null;
		try {
			result = itemDAO.getTotalPorProduto();
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public List<Object[]> getProdutosAndDates() {
		ItemCargaDAO itemDAO = (ItemCargaDAO) this.dao;
		List<Object[]> result = null;
		try {
			result = itemDAO.getProdutosAndDates();
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public Long getQuantidadeByProduto(Produto produto) {
		ItemCargaDAO itemDAO = (ItemCargaDAO) this.dao;
		Long result = 0l;
		try {
			result = itemDAO.getQuantidadeByProduto(produto);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}
}
