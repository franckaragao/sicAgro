package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.sicAgro.enumerations.Agencys;
import br.edu.ifpb.sicAgro.enumerations.States;

/**
 * Classe que representa a entidade Origem de carga,
 * onde, uma carga tem uma origem. Está classe é embutida na entidade @Carga.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Embeddable
public class OrigemCarga implements Serializable {

	private static final long serialVersionUID = 710762956848924938L;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "orgao")
	private Agencys agency;

	@NotNull
	@NotBlank
	@Column(name = "cidade")
	private String city;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private States state;

	public Agencys getAgency() {
		return agency;
	}

	public void setAgency(Agencys agency) {
		this.agency = agency;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agency == null) ? 0 : agency.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrigemCarga other = (OrigemCarga) obj;
		if (agency != other.agency)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrigemCarga [agency=" + agency + ", city=" + city + ", state="
				+ state + "]";
	}
}
