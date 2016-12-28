package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.sicAgro.enumerations.MeasurementType;

/**
 * Classe responsável por representar a entidade de négocio Item de carga.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "itens_carga")
@NamedQueries({
		@NamedQuery(name = "itemCarga.getTotalPorProduto", query = "SELECT i.produto.name, COUNT(i.produto) FROM ItemCarga i GROUP BY i.produto.name, i.produto"),
		@NamedQuery(name = "itemcarga.getQuantidadeByProduto", query = "SELECT COUNT(i.id) FROM ItemCarga i WHERE i.produto = :produto"), 
		@NamedQuery(name = "itemCarga.getProdutosAndDatesItens", query = "SELECT i.produto, i.dateRegister FROM ItemCarga i")
})
public class ItemCarga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantidade_recebida")
	private BigDecimal quantity;

	@Column(name = "quantidade_disponivel", nullable = false, precision = 10, scale = 1)
	private BigDecimal quantidadeDisp = BigDecimal.ZERO;

	@Column(name = "tipo_medida")
	@Enumerated(EnumType.STRING)
	private MeasurementType measurementType;

	@NotNull
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produto_FK")
	private Produto produto;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRegister = new Date();
	
	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public MeasurementType getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidadeDisp() {
		return quantidadeDisp;
	}

	public void setQuantidadeDisp(BigDecimal quantidadeDisp) {
		this.quantidadeDisp = quantidadeDisp;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((measurementType == null) ? 0 : measurementType.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result
				+ ((quantidadeDisp == null) ? 0 : quantidadeDisp.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
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
		ItemCarga other = (ItemCarga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (measurementType != other.measurementType)
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidadeDisp == null) {
			if (other.quantidadeDisp != null)
				return false;
		} else if (!quantidadeDisp.equals(other.quantidadeDisp))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemCarga [id=" + id + ", quantity=" + quantity
				+ ", quantidadeDisp=" + quantidadeDisp + ", measurementType="
				+ measurementType + ", produto=" + produto + "]";
	}

}
