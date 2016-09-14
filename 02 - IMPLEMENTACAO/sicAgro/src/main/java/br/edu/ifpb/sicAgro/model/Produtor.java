package br.edu.ifpb.sicAgro.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade de negócio produtor. Um produtor é a 
 * principal entidade do sistema.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "produtores")
@DiscriminatorValue("Produtor")
@NamedQueries({
		@NamedQuery(name = "Produtor.findByName", query = "SELECT p FROM Produtor p WHERE LOWER(p.name) LIKE :name"),
		@NamedQuery(name = "Produtor.findByCPF", query = "SELECT p FROM Produtor p WHERE p.cpf = :cpf"),
		@NamedQuery(name = "Produtor.findByCod", query = "SELECT p FROM Produtor p WHERE p.cod = :cod"),
		@NamedQuery(name = "Produtor.getTotalProdutores", query = "SELECT COUNT(p) FROM Produtor p") })
public class Produtor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(name = "cod_produtor", nullable = false, unique = true)
	private Integer cod;

	@Column(name = "apelido")
	private String apelido;

	@Column(name = "numero_dap")
	private String nDap;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "conta_fk", nullable = false)
	private Conta acount;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getnDap() {
		return nDap;
	}

	public void setnDap(String nDap) {
		this.nDap = nDap;
	}

	public Conta getAcount() {
		return acount;
	}

	public void setAcount(Conta acount) {
		this.acount = acount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acount == null) ? 0 : acount.hashCode());
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + ((nDap == null) ? 0 : nDap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtor other = (Produtor) obj;
		if (acount == null) {
			if (other.acount != null)
				return false;
		} else if (!acount.equals(other.acount))
			return false;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		if (nDap == null) {
			if (other.nDap != null)
				return false;
		} else if (!nDap.equals(other.nDap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produtor [cod=" + cod + ", apelido=" + apelido + ", nDap="
				+ nDap + ", acount=" + acount + "]";
	}

}
