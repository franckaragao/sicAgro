package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.NotNull;

import br.edu.ifpb.sicAgro.enumerations.MeasurementType;

/**
 * Classe representa entidade de negócio Item de enntrega, um @ItemEntrega 
 * corresponde a uma parte de uma carga que é entregue ao beneficiário @Produtor.
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "itens_entrega")
@NamedQueries({
		@NamedQuery(name = "itemEntrega.getTotalPorProduto", query = "SELECT i.produto.name, COUNT(i.produto) FROM ItemEntrega i GROUP BY i.produto.name, i.produto"),
		@NamedQuery(name = "itemEntrega.findByProduto", query = "SELECT i FROM ItemEntrega i WHERE i.produto = :produto")
})
public class ItemEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "un_medida")
	private MeasurementType measurementType;

	@Column(name = "quantidade", nullable = false)
	private BigDecimal quantity;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "produto_FK")
	private Produto produto;

	@NotNull
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "item_carga_FK")
	private ItemCarga itemCarga;
	
	@ManyToOne
	private Entrega entrega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeasurementType getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemCarga getItemCarga() {
		return itemCarga;
	}
	
	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public void setItemCarga(ItemCarga itemCarga) {
		this.itemCarga = itemCarga;
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
		ItemEntrega other = (ItemEntrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemEntrega [id=");
		builder.append(id);
		builder.append(", measurementType=");
		builder.append(measurementType);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", produto=");
		builder.append(produto);
		builder.append("]");
		return builder.toString();
	}

}
