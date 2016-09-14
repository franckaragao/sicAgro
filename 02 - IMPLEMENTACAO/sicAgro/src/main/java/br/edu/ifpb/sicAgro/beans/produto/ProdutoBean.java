package br.edu.ifpb.sicAgro.beans.produto;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager bean responsável por gerenciar a lista de produtos
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
	 * @throws SicAgroException 
	 * 
	 */
	public void remove() throws SicAgroException {
		produtoService.remove(selectedProduto);
		MessageUtils.messageSucess("Produto removido com sucesso.");
		JSFUtils.rederTo("produtos.xhtml");
	}

	/**
	 * <pre>
	 * Utilizado como solução pra conseguir passar um paramtro apartir do 
	 * manager bean, devido a forma que a linha da datatable é selecionada,
	 * desta forma sem usar um componente que tenha um outcome, tem-se a necessidade
	 * de fazer o manager bean redireiconar para outra página, diante isso o parametro
	 * deve ser passado do manager bean.
	 * </pre>
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
