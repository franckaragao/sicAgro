package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.CargaService;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

@Named
@ViewScoped
public class DashBoardBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	@Inject
	private ProdutorService produtorService;
	
	@Inject
	private EntregaService entregaService;
	
	@Inject
	private CargaService cargaService;
	
	private Long numberProdutores;
	private Long numberSolicitations;
	private Long numberEntregas;
	private Long numberCargas;
	private ItemCarga itemCarga = new ItemCarga();

	public void preRenderView() {
		this.numberProdutores = produtorService.getTotalProdutores();
		this.numberSolicitations = solicitacaoServicoService.getTotalSolicitations();
		this.numberEntregas = entregaService.getTotalEntregas();
		this.numberCargas = cargaService.getTotalCargas();
	}
	
	public Long getNumberProdutores() {
		return numberProdutores;
	}

	public void setNumberProdutores(Long numberProdutores) {
		this.numberProdutores = numberProdutores;
	}

	public Long getNumberSolicitations() {
		return numberSolicitations;
	}

	public void setNumberSolicitations(Long numberSolicitations) {
		this.numberSolicitations = numberSolicitations;
	}

	public Long getNumberEntregas() {
		return numberEntregas;
	}

	public void setNumberEntregas(Long numberEntregas) {
		this.numberEntregas = numberEntregas;
	}

	public Long getNumberCargas() {
		return numberCargas;
	}

	public void setNumberCargas(Long numberCargas) {
		this.numberCargas = numberCargas;
	}

	public ItemCarga getItemCarga() {
		return itemCarga;
	}

	public void setItemCarga(ItemCarga itemCarga) {
		this.itemCarga = itemCarga;
	}
}
