package br.edu.ifpb.sicAgro.beans.entrega;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

@Named
@RequestScoped
public class EntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaService entregaService;

	private List<Entrega> entregas = new ArrayList<Entrega>();

	private Entrega selectedEntrega;
	
	private Date dataEntrega;
	private Produtor produtor;
	private Long id;

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
	
	public void filter(){
		this.entregas = entregaService.filter(dataEntrega, produtor, id);
		System.out.println(entregas+"------------------------------------------------------------->");
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

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
