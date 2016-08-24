package br.edu.ifpb.sicAgro.enumerations;

public enum CivelState {
	
	CASADO("Casado"),
	SOLTEIRO("Solteiro"),
	VIUVO("Viúvo"),
	NAO_INFORMADO("Não Informado"),
	OUTROS("Outros");
	
	private String state;
	
	private CivelState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
	
}
