package br.edu.ifpb.sicAgro.enumerations;

public enum SolicitationState {
	
	FAIL("Não concluído"),
	COMPLETED("Concluído"),
	PROGRESS("Em adamento"),
	
	/*representa nomes das classes css que exibirão o stattus*/
	LB_COMPLETED("label label-success"),
	LB_PROGRESS("label label-warning"),
	LB_FAIL("label label-danger");
	
	private String description;
	
	private SolicitationState(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
