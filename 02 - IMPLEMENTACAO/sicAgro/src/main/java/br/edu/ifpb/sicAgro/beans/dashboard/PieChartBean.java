package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;

import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.ItemCargaService;

@Named
@ViewScoped
public class PieChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GChartType chartType = GChartType.COLUMN;
	private GChartModel chartModel = null;

	@Inject
	private ItemCargaService itemCargaService;
	private ItemCarga itemCarga;
	
	@PostConstruct
	public void preRenderView(){
		if(itemCargaService.findAll().size() > 0){
			itemCarga = itemCargaService.findAll().get(0);
		
			chartModel = new GChartModelBuilder().setChartType(getChartType())  
	                .addColumns("Topping", "Slices").addRow("Mushrooms", 6)  
	                .addRow("Onions", 3).build();  
		}else{
			chartModel = new GChartModelBuilder().setChartType(getChartType())  
	                .addColumns("Topping", "Slices").addRow("Mushrooms", 1)  
	                .addRow("Onions", 6).build();  
		}
	}

	public List<ItemCarga> listItensCargas(){
		return itemCargaService.findAll();
	}

	public GChartType getChartType() {
		return chartType;
	}

	public void setChartType(GChartType chartType) {
		this.chartType = chartType;
	}

	public GChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(GChartModel chartModel) {
		this.chartModel = chartModel;
	}

	public ItemCarga getItemCarga() {
		return itemCarga;
	}

	public void setItemCarga(ItemCarga itemCarga) {
		this.itemCarga = itemCarga;
	}
}
