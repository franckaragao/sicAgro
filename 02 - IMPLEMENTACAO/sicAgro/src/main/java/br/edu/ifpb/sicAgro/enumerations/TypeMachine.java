package br.edu.ifpb.sicAgro.enumerations;

/**
 * 
 * @author franck
 *
 */
/**
 *Enumeração que representa os tipos de veiculos (maquinário) 
 *
 */
public enum TypeMachine {

	RETRO_ESCAVADEIRA("Retro Escavadeira"), 
	CACAMBA("Caçamba"),
	PATROL("Patrol"),
	PIPA("Pipa"),
	ENCHEDEIRA("Enchedeira"),
	TRATOR("Trator");

	private String type;

	private TypeMachine(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
}
