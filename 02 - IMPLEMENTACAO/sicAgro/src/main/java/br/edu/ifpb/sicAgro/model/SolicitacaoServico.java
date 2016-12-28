package br.edu.ifpb.sicAgro.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;

/**
 * Classe representa a entidade de negócio Solicitação. Este tipo de solicitação
 * é usada para representação de solicitações de serviços de veículos (Máquinas
 * agrículas) que são solicitadas por produtors
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "solicitacoes_servicos_prestados")
@NamedQueries({
		@NamedQuery(name = "solicitacaoServico.findByDate", query = "SELECT s FROM SolicitacaoServico s WHERE s.dateSolicitation = :dateSolicitation"),
		@NamedQuery(name = "solicitacaoServico.findByFuncionario", query = "SELECT s FROM SolicitacaoServico s WHERE s.funcionario = :funcionario"),
		@NamedQuery(name = "solicitacaoServico.findSolicitationByDateCompleted", query = "SELECT s FROM SolicitacaoServico s WHERE s.dateSolicitation = :dateSolicitation and s.completed = :completed"),
		@NamedQuery(name = "solicitacaoServico.getTotalSolicitations", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s"),
		@NamedQuery(name = "solicitacao.getCountByProdutor", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s WHERE s.produtor = :produtor"),
		@NamedQuery(name = "solicitacaoServico.getCountSolicitationByDateNotCompleted", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s WHERE s.dateSolicitation = :dateSolicitation and s.completed = :completed and s.dateForRealization < :dateForRealization"),
		@NamedQuery(name = "solicitacaoServico.getCountSolicitationByDateInCurrent", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s WHERE s.dateSolicitation = :dateSolicitation and s.completed = :completed and s.dateForRealization > :dateForRealization"),
		@NamedQuery(name = "solicitacaoServico.getCountByFuncionario", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s WHERE s.funcionario = :funcionario"),
		@NamedQuery(name = "solicitacaoServico.getCountSolicitationByFuncionarioAndStatus", query = "SELECT COUNT(s.id) FROM SolicitacaoServico s WHERE s.funcionario = :funcionario AND s.state = :state"),
		@NamedQuery(name = "solicitacaoServico.getTotalPorMaquina", query = "SELECT s.veiculo.identification, COUNT(s.veiculo) FROM SolicitacaoServico s GROUP BY s.veiculo.identification, s.veiculo"),
	})
public class SolicitacaoServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro")
	private Date dateRegister = new Date();

	@Column(name = "data_solicitacao")
	@Temporal(TemporalType.DATE)
	private Date dateSolicitation;

	@Column(name = "data_marcada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateForRealization;

	@Column(name = "data_realizacao")
	@Temporal(TemporalType.DATE)
	private Date dateRealization;

	@Column(name = "horas_trabalhadas")
	private Integer timeWorkeds;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "veiculo_FK")
	private Veiculo veiculo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "produtor_FK")
	private Produtor produtor;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "funcionario_FK")
	private Funcionario funcionario;

	@Column(name = "completo")
	private Boolean completed;

	@Column(name = "resumo_servico")
	private String resumoServico;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_solicitacao")
	private SolicitationState state;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "pedido_solicitacao_FK")
	private PedidoSolicitacao pedidoSolicitacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Date getDateSolicitation() {
		return dateSolicitation;
	}

	public void setDateSolicitation(Date dateSolicitation) {
		this.dateSolicitation = dateSolicitation;
	}

	public Date getDateForRealization() {
		return dateForRealization;
	}

	public void setDateForRealization(Date dateForRealization) {
		this.dateForRealization = dateForRealization;
	}

	public Date getDateRealization() {
		return dateRealization;
	}

	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

	public Integer getTimeWorkeds() {
		return timeWorkeds;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setTimeWorkeds(Integer timeWorkeds) {
		this.timeWorkeds = timeWorkeds;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getResumoServico() {
		return resumoServico;
	}

	public void setResumoServico(String resumoServico) {
		this.resumoServico = resumoServico;
	}

	public SolicitationState getState() {
		return state;
	}

	public void setState(SolicitationState state) {
		this.state = state;
	}

	public PedidoSolicitacao getPedidoSolicitacao() {
		return pedidoSolicitacao;
	}

	public void setPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao) {
		this.pedidoSolicitacao = pedidoSolicitacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completed == null) ? 0 : completed.hashCode());
		result = prime
				* result
				+ ((dateForRealization == null) ? 0 : dateForRealization
						.hashCode());
		result = prime * result
				+ ((dateRealization == null) ? 0 : dateRealization.hashCode());
		result = prime * result
				+ ((dateRegister == null) ? 0 : dateRegister.hashCode());
		result = prime
				* result
				+ ((dateSolicitation == null) ? 0 : dateSolicitation.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((produtor == null) ? 0 : produtor.hashCode());
		result = prime * result
				+ ((resumoServico == null) ? 0 : resumoServico.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((timeWorkeds == null) ? 0 : timeWorkeds.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		SolicitacaoServico other = (SolicitacaoServico) obj;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (dateForRealization == null) {
			if (other.dateForRealization != null)
				return false;
		} else if (!dateForRealization.equals(other.dateForRealization))
			return false;
		if (dateRealization == null) {
			if (other.dateRealization != null)
				return false;
		} else if (!dateRealization.equals(other.dateRealization))
			return false;
		if (dateRegister == null) {
			if (other.dateRegister != null)
				return false;
		} else if (!dateRegister.equals(other.dateRegister))
			return false;
		if (dateSolicitation == null) {
			if (other.dateSolicitation != null)
				return false;
		} else if (!dateSolicitation.equals(other.dateSolicitation))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produtor == null) {
			if (other.produtor != null)
				return false;
		} else if (!produtor.equals(other.produtor))
			return false;
		if (resumoServico == null) {
			if (other.resumoServico != null)
				return false;
		} else if (!resumoServico.equals(other.resumoServico))
			return false;
		if (state != other.state)
			return false;
		if (timeWorkeds == null) {
			if (other.timeWorkeds != null)
				return false;
		} else if (!timeWorkeds.equals(other.timeWorkeds))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SolicitacaoServico [id=" + id + ", dateRegister="
				+ dateRegister + ", dateSolicitation=" + dateSolicitation
				+ ", dateForRealization=" + dateForRealization
				+ ", dateRealization=" + dateRealization + ", timeWorkeds="
				+ timeWorkeds + ", veiculo=" + veiculo + ", produtor="
				+ produtor + ", funcionario=" + funcionario + ", completed="
				+ completed + ", resumoServico=" + resumoServico + ", state="
				+ state + ", pedidoSolicitacao=" + pedidoSolicitacao + "]";
	}
	
	public void getCurrentStatus(SolicitacaoServico solicitacao) {
		SolicitationState currentStatus = SolicitationState.PROGRESS;
		if (solicitacao == null) {
			currentStatus = SolicitationState.PROGRESS;
		}
		if (solicitacao.getCompleted()) {
			currentStatus = SolicitationState.COMPLETED;

		} else if (solicitacao.getDateForRealization().after(new Date())) {
			currentStatus = SolicitationState.PROGRESS;

		} else {
			currentStatus = SolicitationState.FAIL;
		}
		
		solicitacao.setState(currentStatus);
	}

}
