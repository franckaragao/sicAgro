package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ProdutoDAO;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ProdutoService;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ProdutoServiceImpl extends GenericServiceImpl<Produto, Long> implements ProdutoService {

	private static final long serialVersionUID = 1L;

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
