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
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

@Named
@ViewScoped
public class LineChartBean implements Serializable{

	private static final long serialVersionUID = 7086466135691243720L;
	
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;
	
	private LineChartModel modelChartLine;

	
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
	
}
