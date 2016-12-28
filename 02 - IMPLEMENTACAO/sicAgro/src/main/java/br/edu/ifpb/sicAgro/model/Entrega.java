package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classe representa a entidade de negócio Entrega, 
 * uma entrega representa a distribuição de um produto a um produtor.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "entregas")
@NamedQueries({
	@NamedQuery(name = "entrega.getTotalEntregas", query = "SELECT COUNT(e) FROM Entrega e"),
	@NamedQuery(name = "entrega.getCountByProdutor", query = "SELECT COUNT(e.id) FROM Entrega e WHERE e.produtor = :produtor")
})
public class Entrega implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	private Date dateEntrega;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro", nullable = false)
	private Date dateRegister = new Date();

	@Column(name = "observacao")
	private String observacao;

	@NotEmpty
	@NotNull
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy= "entrega")
	private List<ItemEntrega> itemEntregas;

	@NotNull
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "produtor_FK")
	private Produtor produtor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateEntrega() {
		return dateEntrega;
	}

	public void setDateEntrega(Date dateEntrega) {
		this.dateEntrega = dateEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ItemEntrega> getItemEntregas() {
		return itemEntregas;
	}

	public void setItemEntregas(List<ItemEntrega> itemEntregas) {
		this.itemEntregas = itemEntregas;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateEntrega == null) ? 0 : dateEntrega.hashCode());
		result = prime * result
				+ ((dateRegister == null) ? 0 : dateRegister.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((itemEntregas == null) ? 0 : itemEntregas.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((produtor == null) ? 0 : produtor.hashCode());
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
		Entrega other = (Entrega) obj;
		if (dateEntrega == null) {
			if (other.dateEntrega != null)
				return false;
		} else if (!dateEntrega.equals(other.dateEntrega))
			return false;
		if (dateRegister == null) {
			if (other.dateRegister != null)
				return false;
		} else if (!dateRegister.equals(other.dateRegister))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemEntregas == null) {
			if (other.itemEntregas != null)
				return false;
		} else if (!itemEntregas.equals(other.itemEntregas))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (produtor == null) {
			if (other.produtor != null)
				return false;
		} else if (!produtor.equals(other.produtor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", dateEntrega=" + dateEntrega
				+ ", dateRegister=" + dateRegister + ", observacao="
				+ observacao + ", itemEntregas=" + itemEntregas + ", produtor="
				+ produtor + "]";
	}
}
