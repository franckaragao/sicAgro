package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.services.CargaService;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

@Named
@ViewScoped
public class DashBoardBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	@Inject
	private ProdutorService produtorService;
	
	@Inject
	private EntregaService entregaService;
	
	@Inject
	private CargaService cargaService;

	private LineChartModel modelChartLine;
	private Long numberProdutores;
	private Long numberSolicitations;
	private Long numberEntregas;
	private Long numberCargas;
	private ItemCarga itemCarga = new ItemCarga();

	public void preRenderView() {
		this.modelChartLine = new LineChartModel();

		modelChartLine.setTitle("Solicitações dos últimos 15 dias");
		modelChartLine.setLegendPosition("nw");
		modelChartLine.setAnimate(true);
		modelChartLine.setZoom(true);

		modelChartLine.getAxes().put(AxisType.X, new CategoryAxis("Data do Registro"));
		Axis yAxis = modelChartLine.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");
        yAxis.setMin(0);
        
		this.initLinearModel("Solicitações concluídas",SolicitationState.COMPLETED);
		this.initLinearModel("Solicitações não concluídas",SolicitationState.FAIL);
		this.initLinearModel("Solicitações em adamento",SolicitationState.PROGRESS);

		this.numberProdutores = produtorService.getTotalProdutores();
		this.numberSolicitations = solicitacaoServicoService.getTotalSolicitations();
		this.numberEntregas = entregaService.getTotalEntregas();
		this.numberCargas = cargaService.getTotalCargas();
	}

	private void initLinearModel(String label, SolicitationState state) {

		Map<Date, Integer> solicidacoes = solicitacaoServicoService.getSolicitacoesPorPeriodo(15, state);

		LineChartSeries series = new LineChartSeries();
		series.setLabel(label);

		for (Date data : solicidacoes.keySet()) {
			series.set(DATE_FORMAT.format(data), solicidacoes.get(data));
		}

		modelChartLine.addSeries(series);
	}

	public LineChartModel getModelChartLine() {
		return modelChartLine;
	}

	public void setModelChartLine(LineChartModel modelChartLine) {
		this.modelChartLine = modelChartLine;
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
