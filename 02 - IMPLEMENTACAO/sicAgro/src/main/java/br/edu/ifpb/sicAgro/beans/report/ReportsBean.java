package br.edu.ifpb.sicAgro.beans.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.filter.SolicitacaoFilter;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.report.LoaderReport;

/**
 * 
 * <p>
 * Classe Bean responsável por gerenciar a geração de relatórios de solicitação
 * </p>
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class ReportsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	@Inject
	private VeiculoService veiculoService;

	private List<SolicitacaoServico> list;

	private LoaderReport<SolicitacaoServico> loaderReport;
	
	private List<SolicitationState> status = new ArrayList<SolicitationState>();

	private SolicitacaoFilter filter = SolicitacaoFilter.getInstance();

	@PostConstruct
	public void init() {
		this.getVeiculos();
		status.add(SolicitationState.COMPLETED);
		status.add(SolicitationState.FAIL);
		status.add(SolicitationState.PROGRESS);
	}

	public List<Veiculo> getVeiculos() {
		return veiculoService.findAll();
	}

	public void generateReport() {
		System.out.println("ESTA CHAMANDO BEAN 1 ?????????????");
		this.list = solicitacaoServicoService.filter(filter);
		if (list.size() > 0) {
			for (SolicitacaoServico solicitacaoServico : list) {
				if(solicitacaoServico.getTimeWorkeds() == null)
					solicitacaoServico.setTimeWorkeds(0);
			}
			System.out.println("ESTA CHAMANDO BEAN?????????????");
			this.loaderReport = new LoaderReport<SolicitacaoServico>(
					"/reports/solicitacoes.jasper", list, "solicitacoes.pdf");
			loaderReport.execute(filter.getDateInit(), filter.getDateEnd());
		} else {
			MessageUtils.messageError("Nenhuma solicitção completada foi encontrada neste período");
		}
	}

	public List<SolicitationState> getStatus() {
		return status;
	}

	public void setStatus(List<SolicitationState> status) {
		this.status = status;
	}

	public SolicitacaoFilter getFilter() {
		return filter;
	}

	public void setFilter(SolicitacaoFilter filter) {
		this.filter = filter;
	}
	
	

}
