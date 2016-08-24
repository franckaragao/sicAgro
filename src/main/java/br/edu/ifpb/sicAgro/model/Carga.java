package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe representa a entidade carga (Recebimento)
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "cargas")
@NamedQueries({ 
	@NamedQuery(name = "Carga.findAll", query = "SELECT c FROM Carga c"),
	@NamedQuery(name = "Carga.getTotalCargas", query = "SELECT COUNT(c) FROM Carga c")
})
public class Carga implements Serializable {

	private static final long serialVersionUID = 1814140288938423415L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "data_recebimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date receivingDate;

	@NotNull
	@NotBlank
	@Column(name = "responsavel_carga")
	private String responsibleCarga;

	@Column(name = "observacao")
	private String obs;

	@NotNull
	@Embedded
	private OrigemCarga originLoad;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCarga> itensCarga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}

	public String getResponsibleCarga() {
		return responsibleCarga;
	}

	public void setResponsibleCarga(String responsibleCarga) {
		this.responsibleCarga = responsibleCarga;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public OrigemCarga getOriginLoad() {
		return originLoad;
	}

	public void setOriginLoad(OrigemCarga originLoad) {
		this.originLoad = originLoad;
	}

	public List<ItemCarga> getItensCarga() {
		return itensCarga;
	}

	public void setItensCarga(List<ItemCarga> itensCarga) {
		this.itensCarga = itensCarga;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((itensCarga == null) ? 0 : itensCarga.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result
				+ ((originLoad == null) ? 0 : originLoad.hashCode());
		result = prime * result
				+ ((receivingDate == null) ? 0 : receivingDate.hashCode());
		result = prime
				* result
				+ ((responsibleCarga == null) ? 0 : responsibleCarga.hashCode());
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
		Carga other = (Carga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itensCarga == null) {
			if (other.itensCarga != null)
				return false;
		} else if (!itensCarga.equals(other.itensCarga))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (originLoad == null) {
			if (other.originLoad != null)
				return false;
		} else if (!originLoad.equals(other.originLoad))
			return false;
		if (receivingDate == null) {
			if (other.receivingDate != null)
				return false;
		} else if (!receivingDate.equals(other.receivingDate))
			return false;
		if (responsibleCarga == null) {
			if (other.responsibleCarga != null)
				return false;
		} else if (!responsibleCarga.equals(other.responsibleCarga))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carga [id=" + id + ", receivingDate=" + receivingDate
				+ ", responsibleCarga=" + responsibleCarga + ", obs=" + obs
				+ ", originLoad=" + originLoad + ", itensCarga=" + itensCarga
				+ "]";
	}

}
