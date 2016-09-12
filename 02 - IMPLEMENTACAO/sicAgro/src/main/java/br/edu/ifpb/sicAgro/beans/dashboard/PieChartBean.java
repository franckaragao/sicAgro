package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.PieChartModel;

import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.ItemCargaService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

import com.ibm.icu.text.SimpleDateFormat;

@Named
@ViewScoped
public class PieChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GChartType chartType = GChartType.BAR;
	private GChartModel chartModel = null;
	private PieChartModel pieChartModel;

	@Inject
	private ItemCargaService itemCargaService;
	
	@Inject
	private SolicitacaoServicoService solicitacaoService;
	
	private ItemCarga itemCarga;
	
	public void preRenderView(){
		if(itemCargaService.findAll().size() > 0){
			if(itemCarga == null)
				itemCarga = itemCargaService.findAll().get(0);
			generatePieModel();
		}else{
			generatePieModelEmpty();
		}
		
		createPieModelSolicitacoes();
	}
	
	private PieChartModel generatePieModelSolicitacaoes() {
		PieChartModel model = new PieChartModel();

		List<Object[]> listProdutos = solicitacaoService.getTotalSolicitacoesByMaquina();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				model.set(name, quantidade);
			}
		}

		return model;
	}
	
	private void createPieModelSolicitacoes() {
		pieChartModel = generatePieModelSolicitacaoes();

		pieChartModel.setTitle("Quantidade de Solicitações Feitas por Máquina");
		pieChartModel.setLegendPosition("nw");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setSeriesColors("dd4b39,098554,f39c12,00c0ef,6A1CFF,007F10,FFF29F,BBA8FF");
	}
	
	public boolean isRenderedPieSolicitacaoes(){
		return solicitacaoService.getTotalSolicitacoesByMaquina().size() > 0;	}

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

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}
	
}
