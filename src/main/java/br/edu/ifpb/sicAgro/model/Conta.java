package br.edu.ifpb.sicAgro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.sicAgro.enumerations.UserRole;

/**
 * Classe responsável por representar entidade de negócio Conta. Está conta diz
 * respeito ao acesso do usuário (funcionário ao sistema)
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "contas", uniqueConstraints = { @UniqueConstraint(name = "uc_contas", columnNames = "nome_usuario") })
@NamedQueries({
		@NamedQuery(name = "Conta.findByMail", query = "SELECT c FROM Conta c WHERE c.mail = :mail"),
		@NamedQuery(name = "conta.findByUserName", query = "SELECT c FROM Conta c WHERE c.userName = :userName") })
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Column(name = "nome_usuario")
	private String userName;

	@NotBlank
	@Column(name = "senha", nullable = false)
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "funcao_usuario", nullable = false)
	private UserRole userRole;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_ultimo_acesso")
	private Date lastAcess;

	@Email
	@Column(name = "email", nullable = false)
	private String mail;

	@OneToOne(mappedBy = "acount")
	private Funcionario funcionario;

	@OneToOne(mappedBy = "acount")
	private Produtor produtor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Date getLastAcess() {
		return lastAcess;
	}

	public void setLastAcess(Date lastAcess) {
		this.lastAcess = lastAcess;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastAcess == null) ? 0 : lastAcess.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userRole == null) ? 0 : userRole.hashCode());
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastAcess == null) {
			if (other.lastAcess != null)
				return false;
		} else if (!lastAcess.equals(other.lastAcess))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRole != other.userRole)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", userName=" + userName + ", password="
				+ password + ", userRole=" + userRole + ", lastAcess="
				+ lastAcess + ", mail=" + mail + "]";
	}

}
