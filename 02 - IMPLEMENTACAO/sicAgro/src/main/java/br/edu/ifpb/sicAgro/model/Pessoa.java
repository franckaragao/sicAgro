package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.edu.ifpb.sicAgro.enumerations.CivelState;
import br.edu.ifpb.sicAgro.validators.RG;

/**
 * Classe Generica que representa a entidade Pessoa, está classe
 * é responsável por definir atributos básicos presentes em qualquer
 * tipo de entidade que seja uma @Pessoa, como @Funcioario, @Produtor.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "pessoas", uniqueConstraints = { @UniqueConstraint(name = "uc_pessoa", columnNames = "cpf") })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false)
	protected String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_civil")
	protected CivelState civilState;

	@CPF(message = "deve ser informado no formato correto")
	@Column(name = "cpf", nullable = false)
	protected String cpf;

	@RG
	@Column(name = "rg")
	protected String rg;

	@Temporal(TemporalType.DATE)
	@Column(name = "dta_nascimento")
	protected Date dateNasc;

	@NotNull
	@Column(name = "sexo", nullable = false)
	protected String sexo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro", nullable = false)
	protected Date dateRegister = new Date();

	@Column(name = "telefone")
	protected String phone;

	@NotNull
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "endereco_FK", nullable = false)
	protected Endereco endereco;

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

	public CivelState getCivilState() {
		return civilState;
	}

	public void setCivilState(CivelState civilState) {
		this.civilState = civilState;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDateNasc() {
		return dateNasc;
	}

	public void setDateNasc(Date dateNasc) {
		this.dateNasc = dateNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", name=" + name + ", civilState="
				+ civilState + ", cpf=" + cpf + ", rg=" + rg + ", dateNasc="
				+ dateNasc + ", sexo=" + sexo + ", dateRegister="
				+ dateRegister + ", phone=" + phone + ", endereco=" + endereco
				+ "]";
	}
}
