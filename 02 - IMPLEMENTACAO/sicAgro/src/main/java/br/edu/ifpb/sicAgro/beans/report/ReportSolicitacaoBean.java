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
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.report.LoaderReport;

/**
 * <p>
 * Classe Bean responsável por gerenciar a geração de relatórios de solicitação
 * </p>
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class ReportSolicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	private List<SolicitacaoServico> list;

	private LoaderReport<SolicitacaoServico> loaderReport;
	
	private List<SolicitationState> status = new ArrayList<SolicitationState>();

	private SolicitacaoFilter filter = SolicitacaoFilter.getInstance();

	/**
	 * Inicializa os valores do filtro por status.
	 */
	@PostConstruct
	public void init() {
		status.add(SolicitationState.COMPLETED);
		status.add(SolicitationState.FAIL);
		status.add(SolicitationState.PROGRESS);
	}

	/**
	 * Gera relatório considerando o filtro.
	 */
	public void generateReport() {
		this.list = solicitacaoServicoService.filter(filter);
		if (list.size() > 0) {
			for (SolicitacaoServico solicitacaoServico : list) {
				if(solicitacaoServico.getTimeWorkeds() == null)
					solicitacaoServico.setTimeWorkeds(0);
			}
			this.loaderReport = new LoaderReport<SolicitacaoServico>("/reports/solicitacoes.jasper", list, "solicitacoes.pdf");
			loaderReport.execute(filter.getDateInit(), filter.getDateEnd());
		} else {
			MessageUtils.messageError("Nenhuma solicitção foi encontrada neste período");
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
