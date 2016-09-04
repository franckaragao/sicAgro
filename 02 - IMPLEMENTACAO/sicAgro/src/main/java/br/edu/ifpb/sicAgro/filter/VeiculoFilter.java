package br.edu.ifpb.sicAgro.filter;

import br.edu.ifpb.sicAgro.enumerations.TypeMachine;

/**
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class VeiculoFilter {

	private static VeiculoFilter filter;

	private Long id;

	private String identification;

	private TypeMachine type;

	private String chassi;

	public static VeiculoFilter getInstance() {
		filter = new VeiculoFilter();
		return filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public TypeMachine getType() {
		return type;
	}

	public void setType(TypeMachine type) {
		this.type = type;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
}
