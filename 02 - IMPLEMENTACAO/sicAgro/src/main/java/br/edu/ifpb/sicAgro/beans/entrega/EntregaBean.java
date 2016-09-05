package br.edu.ifpb.sicAgro.beans.entrega;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

@Named
@RequestScoped
public class EntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaService entregaService;
	
	@Inject	
	private ProdutoService produtoService;
	
	private List<Entrega> entregas = new ArrayList<Entrega>();

	private Entrega selectedEntrega;

	private EntregaFilter filter = EntregaFilter.getInstance();

	@PostConstruct
	public void init() {
		this.listEntregas();
	}

	public void renderTo() {
		JSFUtils.rederTo("entregaView.xhtml");
		JSFUtils.setParam("entrega", selectedEntrega);
	}

	public void listEntregas() {
		this.entregas = entregaService.findAll();
	}

	public void filter() {
		this.entregas = entregaService.filter(filter);
	}
	
	public List<Produto> listProdutos() {
		return produtoService.findAll();
		
	}
	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public Entrega getSelectedEntrega() {
		return selectedEntrega;
	}

	public void setSelectedEntrega(Entrega selectedEntrega) {
		this.selectedEntrega = selectedEntrega;
	}

	public EntregaFilter getFilter() {
		return filter;
	}

	public void setFilter(EntregaFilter filter) {
		this.filter = filter;
	}

}
