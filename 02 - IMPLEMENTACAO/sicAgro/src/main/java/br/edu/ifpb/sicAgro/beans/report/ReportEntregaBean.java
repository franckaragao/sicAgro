package br.edu.ifpb.sicAgro.beans.report;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.filter.EntregaFilter;
import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.report.LoaderReport;

/**
 * 
 * <p>
 * Classe Bean responsável por gerenciar a geração de relatórios de entregas.
 * </p>
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class ReportEntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaService entregaService;

	private List<Entrega> list;

	private LoaderReport<Entrega> loaderReport;
	
	private EntregaFilter filter = EntregaFilter.getInstance();

	public void generateReport() {
		this.list = entregaService.filter(filter);
		if (list.size() > 0) {

			this.loaderReport = new LoaderReport<Entrega>(
					"/reports/entregas.jasper", list, "entregas.pdf");
			loaderReport.execute(filter.getDateInit(), filter.getDateEnd());
		} else {
			MessageUtils.messageError("Nenhuma entrega encontrada.");
		}
	}

	public EntregaFilter getFilter() {
		return filter;
	}

	public void setFilter(EntregaFilter filter) {
		this.filter = filter;
	}
}
