package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.edu.ifpb.sicAgro.services.ItemCargaService;
import br.edu.ifpb.sicAgro.services.ItemEntregaService;

/**
 * Bean responsável por criar e atribuir valores a gráficos de barras do
 * primefaces
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class BarChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemEntregaService itemEntregaService;

	@Inject
	private ItemCargaService itemCargaService;

	private BarChartModel barModelQuantEntregas;

	private BarChartModel barModelQuantCargas;

	private BarChartModel barChartModelTotalSolicitacoes;

	public void preRenderView() {
		createBarModelEntregas();
		createBarModelCargas();
	}

	private BarChartModel initBarModelEntrega() {
		BarChartModel model = new BarChartModel();
		ChartSeries produtos = new ChartSeries();
		produtos.setLabel("Produto");

		List<Object[]> listProdutos = itemEntregaService.getTotalPorProduto();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				produtos.set(name, quantidade);
			}
		}
		model.addSeries(produtos);

		return model;
	}
	
	public boolean isRenderedBarModelEntrega(){
		return itemEntregaService.getTotalPorProduto().size() > 0;
	}

	private BarChartModel initBarModelCarga() {
		BarChartModel model = new BarChartModel();
		ChartSeries produtos = new ChartSeries();
		produtos.setLabel("Produto");

		List<Object[]> listProdutos = itemCargaService.getTotalPorProduto();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				produtos.set(name, quantidade);
			}
		}
		model.addSeries(produtos);

		return model;
	}
	
	public boolean isRenderedBarModelCarga(){
		return itemCargaService.getTotalPorProduto().size() > 0;
	}

	private void createBarModelEntregas() {
		barModelQuantEntregas = initBarModelEntrega();

		barModelQuantEntregas
				.setTitle("Quantidade de Entregas Feitas por produto");
		barModelQuantEntregas.setLegendPosition("nw");
		barModelQuantEntregas.setAnimate(true);
		barModelQuantEntregas.setZoom(true);
		barModelQuantEntregas.setSeriesColors("dd4b39");

		Axis xAxis = barModelQuantEntregas.getAxis(AxisType.X);
		xAxis.setLabel("Produto");
		xAxis.setTickAngle(-50);

		Axis yAxis = barModelQuantEntregas.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);

	}

	private void createBarModelCargas() {
		barModelQuantCargas = initBarModelCarga();

		barModelQuantCargas.setTitle("Quantidade de Recebimentos por produto");
		barModelQuantCargas.setLegendPosition("nw");
		barModelQuantCargas.setAnimate(true);
		barModelQuantCargas.setZoom(true);
		barModelQuantCargas.setSeriesColors("00c0ef");

		Axis xAxis = barModelQuantCargas.getAxis(AxisType.X);
		xAxis.setLabel("Produto");
		xAxis.setTickAngle(-50);

		Axis yAxis = barModelQuantCargas.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);

	}

	public BarChartModel getBarModelQuantEntregas() {
		return barModelQuantEntregas;
	}

	public void setBarModelQuantEntregas(BarChartModel barModelQuantEntregas) {
		this.barModelQuantEntregas = barModelQuantEntregas;
	}

	public BarChartModel getBarChartModelTotalSolicitacoes() {
		return barChartModelTotalSolicitacoes;
	}

	public void setBarChartModelTotalSolicitacoes(
			BarChartModel barChartModelTotalSolicitacoes) {
		this.barChartModelTotalSolicitacoes = barChartModelTotalSolicitacoes;
	}

	public BarChartModel getBarModelQuantCargas() {
		return barModelQuantCargas;
	}

	public void setBarModelQuantCargas(BarChartModel barModelQuantCargas) {
		this.barModelQuantCargas = barModelQuantCargas;
	}

}
