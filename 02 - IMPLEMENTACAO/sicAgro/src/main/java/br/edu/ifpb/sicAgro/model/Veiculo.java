package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.sicAgro.enumerations.States;
import br.edu.ifpb.sicAgro.enumerations.TypeMachine;

/**
 * Classe representa a entidade de négocio veiculo. 
 * Um @Veiculo representa uma máquina agricula, onde essa
 * máquina é solicitada por produtores para realização de serviços
 * diversos
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "veiculos")
@NamedQueries({ 
	@NamedQuery(name = "Veiculo.findByIdentification", query = "SELECT v FROM Veiculo v WHERE LOWER(v.identification) LIKE LOWER(:identification)")
})
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Column(name = "identificacao")
	private String identification;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_veiculo")
	@NotNull
	private TypeMachine type;

	@NotNull
	@Column(name = "chassi")
	private String chassi;

	@NotNull
	@Column(name = "cidade")
	private String city;

	@NotNull
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private States uf;

	@Column(name = "ano_aquisicao")
	private Integer aquisYear;

	@Column(name = "horimetro")
	private Integer horimetro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeMachine getType() {
		return type;
	}

	public void setType(TypeMachine type) {
		this.type = type;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public States getUf() {
		return uf;
	}

	public void setUf(States uf) {
		this.uf = uf;
	}

	public Integer getAquisYear() {
		return aquisYear;
	}

	public void setAquisYear(Integer aquisYear) {
		this.aquisYear = aquisYear;
	}

	public Integer getHorimetro() {
		return horimetro;
	}

	public void setHorimetro(Integer horimetro) {
		this.horimetro = horimetro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", identification=" + identification
				+ ", type=" + type + ", chassi=" + chassi + ", city=" + city
				+ ", uf=" + uf + ", aquisYear=" + aquisYear + ", horimetro="
				+ horimetro + "]";
	}

}
