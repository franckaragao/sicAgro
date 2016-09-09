package br.edu.ifpb.sicAgro.beans.entrega;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.MeasurementType;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.model.ItemEntrega;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.services.ItemCargaService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.util.convertUnits.ConversionUtils;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

@Named
@ViewScoped
public class EntregaEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaService entregaService;

	@Inject
	private ItemCargaService itemCargaService;

	@Inject
	private ProdutorService produtorService;

	private Entrega entrega;
	
	private ItemEntrega itemEntrega = new ItemEntrega();
	
	private ItemCarga itemCarga;
	
	private List<ItemCarga> itemCargas = new ArrayList<ItemCarga>();
	
	private List<Produtor> produtores = new ArrayList<Produtor>();

	private List<MeasurementType> measurements = new ArrayList<>();

	public void preRenderView() {
		if (entrega == null) {
			entrega = new Entrega();
			entrega.setProdutor(new Produtor());
			entrega.setItemEntregas(new ArrayList<ItemEntrega>());
		}
	}

	@PostConstruct
	public void init() {
		this.listItensCargas();
		this.listProdutores();
		measurements = Arrays.asList(MeasurementType.values());
	}

	public void save() throws SicAgroException {
		if(entrega.getItemEntregas().size() < 1)
			throw new SicAgroExceptionHandler("Um item de entrega deve ser adicionado");
		
		if (isEdited()) {
			entregaService.update(entrega);
			MessageUtils.messageSucess("Entrega atualizada com sucesso.");
		} else {
			entregaService.add(entrega);
			MessageUtils.messageSucess("Entrega realizada com sucesso.");
		}
		JSFUtils.rederTo("entregas.xhtml");
	}

	public void listItensCargas() {
		itemCargas = itemCargaService.findAll();
	}

	public void listProdutores() {
		produtores = produtorService.findAll();
	}
	
	public void updateItemEntrega(){
		this.itemEntrega.setMeasurementType(itemCarga.getMeasurementType());
		this.itemEntrega.setProduto(itemCarga.getProduto());
		
	}
	
	public void reDeleteItemEntrega(ItemEntrega itemEntrega) throws SicAgroException{
		
		ItemCarga itemCarga = itemEntrega.getItemCarga();
		
		BigDecimal value = itemEntrega.getQuantity();
		
		if(isEqualsMeasurement(itemCarga, itemEntrega)){
			BigDecimal valueB = new BigDecimal(
					ConversionUtils.convert(itemEntrega.getMeasurementType(),itemCarga.getMeasurementType() , itemEntrega.getQuantity()));
			value =  valueB;
		}

		BigDecimal valueItemCarga = itemCarga.getQuantidadeDisp().add(value);
	
		itemCarga.setQuantidadeDisp(valueItemCarga);
		
		this.itemCargaService.update(itemCarga);
		
		this.itemCarga = itemCarga;
	}
	
	public ItemEntrega reSetItemEntrega() throws SicAgroException {
		
		if(isEqualsMeasurement(itemCarga, itemEntrega)){
			BigDecimal value = new BigDecimal(ConversionUtils.convert(itemEntrega.getMeasurementType(),itemCarga.getMeasurementType() , itemEntrega.getQuantity()));
			this.itemCarga.setQuantidadeDisp(this.updateQuantityItemCarga(value, itemCarga));
			
		}else{
			this.itemCarga.setQuantidadeDisp(this.updateQuantityItemCarga(itemEntrega.getQuantity(), itemCarga));
		}
		
		this.itemEntrega.setItemCarga(itemCarga);
		
		if(itemCarga.getId() != null){
			this.itemCargaService.update(itemCarga);
			this.listItensCargas();
		}
		
		ItemEntrega item = this.itemEntrega;
		this.itemEntrega = new ItemEntrega();
		item.setEntrega(entrega);
		return item;
	}
	
	private boolean isEqualsMeasurement(ItemCarga itemCarga, ItemEntrega itemEntrega){
		return !itemCarga.getMeasurementType().equals(itemEntrega.getMeasurementType());
	}
	
	public BigDecimal updateQuantityItemCarga(BigDecimal value, ItemCarga itemCarga) throws SicAgroExceptionHandler {
		
		BigDecimal valueCal = itemCarga.getQuantidadeDisp().subtract(value);
		
		if(valueCal.doubleValue() < 0)
			throw new SicAgroExceptionHandler("Quantidade informada não deve ser maior que o total de itens disponíveis");
		
		return valueCal;
	}

	public boolean isEdited() {
		return entrega.getId() != null;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<ItemCarga> getItemCargas() {
		return itemCargas;
	}

	public void setItemCargas(List<ItemCarga> itemCargas) {
		this.itemCargas = itemCargas;
	}

	public ItemCarga getItemCarga() {
		return itemCarga;
	}

	public void setItemCarga(ItemCarga itemCarga) {
		this.itemCarga = itemCarga;
	}

	public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public ItemEntrega getItemEntrega() {
		return itemEntrega;
	}

	public void setItemEntrega(ItemEntrega itemEntrega) {
		this.itemEntrega = itemEntrega;
	}

	public List<MeasurementType> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<MeasurementType> measurements) {
		this.measurements = measurements;
	}
}
