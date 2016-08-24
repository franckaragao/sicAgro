package br.edu.ifpb.sicAgro.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manage bean responsável por gerenciar a lista de produtos
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -6153253920691800226L;

	@Inject
	private ProdutoService produtoService;

	private List<Produto> produtos;

	private Produto selectedProduto;

	/**
	 * 
	 */
	public void preRenderView() {
		if (selectedProduto == null) {
			selectedProduto = new Produto();
		}
	}

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.listOfProdutos();
	}

	/**
	 * 
	 */
	public void remove() {
		produtoService.remove(selectedProduto);
		MessageUtils.messageSucess("Produto removido com sucesso.");
		JSFUtils.rederTo("produtos.xhtml");
	}

	/**
	 * 
	 */
	public void renderTo() {
		JSFUtils.rederTo("produtoView.xhtml");
		JSFUtils.setParam("produto", selectedProduto);
	}

	public void listOfProdutos() {
		this.produtos = produtoService.findAll();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(Produto selectedProduto) {
		this.selectedProduto = selectedProduto;
	}
}
