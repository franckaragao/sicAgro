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

import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.validation.COD;

/**
 * Classe representa a entidade produto. Um produto é 
 * utilizado para representar o(s) produtor de uma carga.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */

@Entity
@Table(name = "produtos")
@NamedQueries({
		@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
		@NamedQuery(name = "Produto.findByName", query = "SELECT p FROM Produto p WHERE LOWER(p.name) LIKE LOWER(:name)") })
public class Produto implements Serializable {

	private static final long serialVersionUID = -4257670054521289515L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@COD
	@Column(name = "cod_produto")
	private String cod;

	@NotBlank
	@Column(name = "nome_produto")
	private String name;

	@Column(name = "descricao_produto")
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_produto", nullable = false)
	private ProdutoType produtoType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProdutoType getProdutoType() {
		return produtoType;
	}

	public void setProdutoType(ProdutoType produtoType) {
		this.produtoType = produtoType;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((produtoType == null) ? 0 : produtoType.hashCode());
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
		Produto other = (Produto) obj;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (produtoType != other.produtoType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", cod=" + cod + ", name=" + name
				+ ", description=" + description + ", produtoType="
				+ produtoType + "]";
	}

}
