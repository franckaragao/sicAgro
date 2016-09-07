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

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;

@Named
@ViewScoped
public class LineChartBean implements Serializable{

	private static final long serialVersionUID = 7086466135691243720L;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;
	
	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;
	
	private LineChartModel modelChartLineSolicitacao;
	
	private LineChartModel modelChartLinePedidoSolicitacao;
	
	public void preRenderView() {
		this.initChartLineSolicitacao();
		this.initChartLinePedidos();
	}
	
	/**
	 * 
	 */
	private void initChartLineSolicitacao(){
		this.modelChartLineSolicitacao = new LineChartModel();

		modelChartLineSolicitacao.setTitle("Solicitações dos últimos 10 dias");
		modelChartLineSolicitacao.setLegendPosition("nw");
		modelChartLineSolicitacao.setAnimate(true);
		modelChartLineSolicitacao.setZoom(true);

		modelChartLineSolicitacao.getAxes().put(AxisType.X, new CategoryAxis("Data do Registro"));
		Axis yAxis = modelChartLineSolicitacao.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");
        yAxis.setMin(0);
        
		this.generateChartSolicitacao("Solicitações concluídas",SolicitationState.COMPLETED);
		this.generateChartSolicitacao("Solicitações não concluídas",SolicitationState.FAIL);
		this.generateChartSolicitacao("Solicitações em adamento",SolicitationState.PROGRESS);
	}
	
	/**
	 * 
	 */
	private void initChartLinePedidos(){
		this.modelChartLinePedidoSolicitacao = new LineChartModel();

		modelChartLinePedidoSolicitacao.setTitle("Pedidos de solicitações dos últimos 10 dias");
		modelChartLinePedidoSolicitacao.setLegendPosition("nw");
		modelChartLinePedidoSolicitacao.setAnimate(true);
		modelChartLinePedidoSolicitacao.setZoom(true);

		modelChartLinePedidoSolicitacao.getAxes().put(AxisType.X, new CategoryAxis("Data do pedido"));
		Axis yAxis = modelChartLinePedidoSolicitacao.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade");
        yAxis.setMin(0);
        
		this.generateChartPedidos("Pedidos concluídos", PedidoStatus.COMPLETED);
		this.generateChartPedidos("Pedidos recusados", PedidoStatus.NOT_ACCEPTED);
		this.generateChartPedidos("Pedidos aceitos",PedidoStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param label
	 * @param state
	 */
	private void generateChartSolicitacao(String label, SolicitationState state) {

		Map<Date, Integer> solicidacoes = solicitacaoServicoService.getSolicitacoesPorPeriodo(10, state);

		LineChartSeries series = new LineChartSeries();
		series.setLabel(label);

		for (Date data : solicidacoes.keySet()) {
			series.set(DATE_FORMAT.format(data), solicidacoes.get(data));
		}

		modelChartLineSolicitacao.addSeries(series);
	}
	
	/**
	 * 
	 * @param label
	 * @param status
	 */
	private void generateChartPedidos(String label, PedidoStatus status) {

		Map<Date, Integer> pedidos = pedidoSolicitacaoService.getPedidosPorPeriodo(10, status);

		LineChartSeries series = new LineChartSeries();
		series.setLabel(label);

		for (Date data : pedidos.keySet()) {
			System.out.println(data +" "+ pedidos.get(data));
			series.set(DATE_FORMAT.format(data), pedidos.get(data));
		}

		modelChartLinePedidoSolicitacao.addSeries(series);
	}

	public LineChartModel getModelChartLineSolicitacao() {
		return modelChartLineSolicitacao;
	}

	public void setModelChartLineSolicitacao(
			LineChartModel modelChartLineSolicitacao) {
		this.modelChartLineSolicitacao = modelChartLineSolicitacao;
	}

	public LineChartModel getModelChartLinePedidoSolicitacao() {
		return modelChartLinePedidoSolicitacao;
	}

	public void setModelChartLinePedidoSolicitacao(
			LineChartModel modelChartLinePedidoSolicitacao) {
		this.modelChartLinePedidoSolicitacao = modelChartLinePedidoSolicitacao;
	}
	
	
}
