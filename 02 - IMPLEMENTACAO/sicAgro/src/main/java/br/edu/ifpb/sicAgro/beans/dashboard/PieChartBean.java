package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;

import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.ItemCargaService;

import com.ibm.icu.text.SimpleDateFormat;

@Named
@ViewScoped
public class PieChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GChartType chartType = GChartType.BAR;
	private GChartModel chartModel = null;

	@Inject
	private ItemCargaService itemCargaService;
	private ItemCarga itemCarga;
	
	public void preRenderView(){
		if(itemCargaService.findAll().size() > 0){
			if(itemCarga == null)
				itemCarga = itemCargaService.findAll().get(0);
			generatePieModel();
		}else{
			generatePieModelEmpty();
		}

	}
	
	public void generatePieModel(){
		chartModel = new GChartModelBuilder().setChartType(getChartType())  
                .addColumns("Topping", "Quantidade").addRow("Recebido", itemCarga.getQuantity())  
                .addRow("Disponível", itemCarga.getQuantidadeDisp()).build();  
	}
	
	private void generatePieModelEmpty(){
		chartModel = new GChartModelBuilder().setChartType(getChartType())  
                .addColumns("Topping", "Slices").addRow("Vazio", 0)  
                .addRow("Vazio", 0).build();
	}
	
	public String getDateItemcarga(){
		return new SimpleDateFormat("dd/MM/yyy").format(itemCarga.getDateRegister());
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
