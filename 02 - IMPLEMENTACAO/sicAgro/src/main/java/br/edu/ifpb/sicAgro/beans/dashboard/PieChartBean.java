package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;

import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.ItemCargaService;
import br.edu.ifpb.sicAgro.services.ItemEntregaService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

import com.ibm.icu.text.SimpleDateFormat;

@Named
@ViewScoped
public class PieChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GChartModel chartModel;
	private GChartModel columnModel;
	private GChartModel pieModel;

	@Inject
	private ItemCargaService itemCargaService;

	@Inject
	private SolicitacaoServicoService solicitacaoService;

	@Inject
	private ItemEntregaService itemEntregaService;

	private ItemCarga itemCarga;

	public void preRenderView() {
		if (itemCargaService.findAll().size() > 0) {
			if (itemCarga == null)
				itemCarga = itemCargaService.findAll().get(0);
			generatePieModel();
		} else {
			generatePieModelEmpty();
		}

		generatePieModelSolicitacaoes();
		generateChartModelColumn();
	}

	private void generatePieModelSolicitacaoes() {

		List<Object[]> listProdutos = solicitacaoService.getTotalSolicitacoesByMaquina();
		
		GChartModelBuilder c = new GChartModelBuilder();
		c.addColumns("Topping", "Máquinas");
		c.setChartType(GChartType.PIE);

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				c.addRow(name,quantidade);			
			}
		}
		pieModel = c.build();
	}

	public void generatePieModel() {
		chartModel = new GChartModelBuilder().setChartType(GChartType.BAR)
				.addColumns("Topping", "Quantidade")
				.addRow("Recebido", itemCarga.getQuantity())
				.addRow("Disponível", itemCarga.getQuantidadeDisp()).build();
	}

	private void generatePieModelEmpty() {
		chartModel = new GChartModelBuilder().setChartType(GChartType.BAR)
				.addColumns("Topping", "Slices").addRow("Vazio", 0)
				.addRow("Vazio", 0).build();
	}

	public void generateChartModelColumn() {
		List<ProdutoType> l = Arrays.asList(ProdutoType.values());
		GChartModelBuilder c = new GChartModelBuilder();
		c.addColumns("Topping", "Categorias");
		c.setChartType(GChartType.COLUMN);
		for (ProdutoType produtoType : l) {
			c.addRow(produtoType.getType(),
					itemEntregaService.findCountByTipoProduto(produtoType));
		}
		columnModel = c.build();
	}

	public String getDateItemcarga() {
		return new SimpleDateFormat("dd/MM/yyy").format(itemCarga.getDateRegister());
	}

	public List<ItemCarga> listItensCargas() {
		return itemCargaService.findAll();
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

	public GChartModel getColumnModel() {
		return columnModel;
	}

	public void setColumnModel(GChartModel columnModel) {
		this.columnModel = columnModel;
	}

	public GChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(GChartModel pieModel) {
		this.pieModel = pieModel;
	}
}
