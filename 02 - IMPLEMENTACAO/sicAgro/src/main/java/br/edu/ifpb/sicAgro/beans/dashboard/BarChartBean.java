package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;

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

	private GChartModel barModelQuantEntregas;

	private GChartModel barModelQuantCargas;

	public void preRenderView() {
		initBarModelEntrega();
		initBarModelCarga();
	}

	private void initBarModelEntrega() {
		GChartModelBuilder gChartColumn = new GChartModelBuilder();
		gChartColumn.addColumns("Topping", "Produto");
		gChartColumn.setChartType(GChartType.COLUMN);

		List<Object[]> listProdutos = itemEntregaService.getTotalPorProduto();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				gChartColumn.addRow(name, quantidade);
			}
		}
		barModelQuantEntregas = gChartColumn.build();
	}
	
	public boolean isRenderedBarModelEntrega(){
		return itemEntregaService.getTotalPorProduto().size() > 0;
	}

	private void initBarModelCarga() {
		GChartModelBuilder gChartColumn = new GChartModelBuilder();
		gChartColumn.addColumns("Topping", "Produto");
		gChartColumn.setChartType(GChartType.COLUMN);
		
		List<Object[]> listProdutos = itemCargaService.getTotalPorProduto();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				String name = (String) objects[0];
				Long quantidade = (Long) objects[1];
				gChartColumn.addRow(name,quantidade);
			}
		}
		barModelQuantCargas = gChartColumn.build();
	}
	
	public boolean isRenderedBarModelCarga(){
		return itemCargaService.getTotalPorProduto().size() > 0;
	}

	public GChartModel getBarModelQuantEntregas() {
		return barModelQuantEntregas;
	}

	public void setBarModelQuantEntregas(GChartModel barModelQuantEntregas) {
		this.barModelQuantEntregas = barModelQuantEntregas;
	}

	public GChartModel getBarModelQuantCargas() {
		return barModelQuantCargas;
	}

	public void setBarModelQuantCargas(GChartModel barModelQuantCargas) {
		this.barModelQuantCargas = barModelQuantCargas;
	}

}
