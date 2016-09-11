package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ProdutoDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ItemCargaService;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ProdutoServiceImpl extends GenericServiceImpl<Produto, Long> implements ProdutoService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemCargaService itemCargaService;

	/**
	 * Construtor default, necessário devido ao CDI requerer um construtor
	 * default
	 */
	public ProdutoServiceImpl() {
	}
	
	/**
	 * Construtor responsável por injetar o dao de produto no dao generico
	 * 
	 * @param produtoDAO
	 */
	@Inject
	public ProdutoServiceImpl(ProdutoDAO produtoDAO) {
		this.dao = produtoDAO;
	}
	
	@Override
	@Transactional
	public void remove(Produto entity) throws SicAgroException {
		Long flag = itemCargaService.getQuantidadeByProduto(entity);
		if(flag > 0)
			throw new SicAgroExceptionHandler("Produto não pode ser removido, está relacionado com alguma carga.");
		dao.remove(entity);
	}

	/**
	 * 
	 */
	@Override
	public List<Produto> findByName(String name) {
		ProdutoDAO produtoDAO = (ProdutoDAO) this.dao;
		List<Produto> list = produtoDAO.findByName(name);
		return list;
	}
}
