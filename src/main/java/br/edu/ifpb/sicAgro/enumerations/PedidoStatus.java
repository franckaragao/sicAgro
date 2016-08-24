package br.edu.ifpb.sicAgro.enumerations;

public enum PedidoStatus {
	
	NOT_ACCEPTED("Recusado"),
	COMPLETED("Concluído"),
	ACCEPTED("Aceito"),
	PROGRESS("Aguardando"),
	
	/*representa nomes das classes css que exibirão o stattus*/
	LB_COMPLETED("label label-success"),
	LB_ACCEPTED("label label-info"),
	LB_PROGRESS("label label-warning"),
	LB_NOT_ACCEPTED("label label-danger");
	
	private String description;
	
	private PedidoStatus(String descrption) {
		this.description = descrption;
	}

	public String getDescription() {
		return description;
	}

}
