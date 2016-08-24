package br.edu.ifpb.sicAgro.beans.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class ProdutoEditBean implements Serializable {

	private static final long serialVersionUID = -3476340788406544684L;

	@Inject
	private ProdutoService produtoService;

	private Produto produto;
	
	private List<ProdutoType> produtoTypes = new ArrayList<ProdutoType>();

	public ProdutoEditBean() {
		produtoTypes = Arrays.asList(ProdutoType.values());
	}
	
	/**
	 * 
	 */
    public void preRenderView() {
        if (produto == null) {
    		produto = new Produto();
        }
    }
	
	/**
	 * 
	 */
	public void save() {
		if(isProdutoEdited()){
			produtoService.update(produto);
			MessageUtils.messageSucess("Produto atualizado com sucesso.");
		}else{
			produtoService.add(produto);
			MessageUtils.messageSucess("Produto adicionado com sucesso.");
		}
		JSFUtils.rederTo("produtos.xhtml");
	}
	
	public boolean isProdutoEdited(){
		return produto.getId() != null;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ProdutoType> getProdutoTypes() {
		return produtoTypes;
	}

	public void setProdutoTypes(List<ProdutoType> produtoTypes) {
		this.produtoTypes = produtoTypes;
	}
	
}
