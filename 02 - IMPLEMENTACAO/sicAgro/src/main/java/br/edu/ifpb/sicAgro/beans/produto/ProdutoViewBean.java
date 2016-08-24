package br.edu.ifpb.sicAgro.beans.produto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a um produto
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class ProdutoViewBean implements Serializable {

	private static final long serialVersionUID = -5325152793743099030L;

	private Produto produto;

	/**
	 * É iniciando no inicio da renderização da pagina de produtoView,
	 * responsável por obter um produto pelo contexto de aplicação.
	 */
	public void preRenderView() {
		produto = (Produto) JSFUtils.getParam("produto");
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
