package br.edu.ifpb.sicAgro.filter;

import java.util.Date;

import br.edu.ifpb.sicAgro.model.Produtor;

public class EntregaFilter {

	private Long id;
	private Date dateInit;
	private Date DateEnd;
	private Produtor produtor;

	public static EntregaFilter getInstance() {
		return new EntregaFilter();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public Date getDateEnd() {
		return DateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		DateEnd = dateEnd;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
}
