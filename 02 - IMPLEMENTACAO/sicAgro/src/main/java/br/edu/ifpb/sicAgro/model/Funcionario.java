package br.edu.ifpb.sicAgro.model;

import javax.persistence.CascadeType;
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
 * Classe responsável por representar a entidade de négocio Funcionário.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "funcionarios")
@DiscriminatorValue("Funcionario")
@NamedQueries({ @NamedQuery(name = "Funcionario.findByName", query = "SELECT f FROM Funcionario f WHERE LOWER(f.name) LIKE LOWER(:name)") })
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "conta_fk", nullable = false)
	private Conta acount;

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
		Funcionario other = (Funcionario) obj;
		if (acount == null) {
			if (other.acount != null)
				return false;
		} else if (!acount.equals(other.acount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [acount=" + acount + "]";
	}

}
