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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.enumerations.TypeMachine;

/**
 * Representa a entidade de negócio referente a pedidos de solicitações.  
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Entity
@Table(name = "pedido_solicitacao")
@NamedQueries({ 
		@NamedQuery(name = "pedidoSolicitacao.getTotalByStatus", query = "SELECT COUNT(p.id) FROM PedidoSolicitacao p WHERE p.status = :status"),
		@NamedQuery(name = "pedidoSolicitacao.getTotalByDateAndStatus", query = "SELECT COUNT(p.id) FROM PedidoSolicitacao p WHERE p.dataPedido = :dataPedido AND p.status = :status"),
		@NamedQuery(name = "pedidoSolicitacao.getTotalByMessages", query = "SELECT COUNT(p.id) FROM PedidoSolicitacao p WHERE p.motivoRejeicao IS NOT NULL"),
		@NamedQuery(name = "pedidoSolicitacao.findPedidosByProdutor", query = "SELECT p FROM PedidoSolicitacao p WHERE p.produtor = :produtor")
})
	
public class PedidoSolicitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date dataPedido = new Date();

	@Column(name = "horas_necessarias")
	private Integer horasNecessarias;

	@Column(name = "tipo_maquina")
	@Enumerated(EnumType.STRING)
	private TypeMachine tipoMaquina;

	@NotNull
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "produtor_FK")
	private Produtor produtor;

	@Column(name = "status_pedido")
	@Enumerated(EnumType.STRING)
	private PedidoStatus status;

	@OneToOne(mappedBy = "pedidoSolicitacao")
	private SolicitacaoServico solicitacaoServico;

	@Column(name = "motivo_rejeicao")
	private String motivoRejeicao;

	public PedidoSolicitacao() {

	}

	public Integer getHorasNecessarias() {
		return horasNecessarias;
	}

	public void setHorasNecessarias(Integer horasNecessarias) {
		this.horasNecessarias = horasNecessarias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public TypeMachine getTipoMaquina() {
		return tipoMaquina;
	}

	public void setTipoMaquina(TypeMachine tipoMaquina) {
		this.tipoMaquina = tipoMaquina;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((horasNecessarias == null) ? 0 : horasNecessarias.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((motivoRejeicao == null) ? 0 : motivoRejeicao.hashCode());
		result = prime * result
				+ ((produtor == null) ? 0 : produtor.hashCode());
		result = prime
				* result
				+ ((solicitacaoServico == null) ? 0 : solicitacaoServico
						.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((tipoMaquina == null) ? 0 : tipoMaquina.hashCode());
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
		PedidoSolicitacao other = (PedidoSolicitacao) obj;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horasNecessarias == null) {
			if (other.horasNecessarias != null)
				return false;
		} else if (!horasNecessarias.equals(other.horasNecessarias))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motivoRejeicao == null) {
			if (other.motivoRejeicao != null)
				return false;
		} else if (!motivoRejeicao.equals(other.motivoRejeicao))
			return false;
		if (produtor == null) {
			if (other.produtor != null)
				return false;
		} else if (!produtor.equals(other.produtor))
			return false;
		if (solicitacaoServico == null) {
			if (other.solicitacaoServico != null)
				return false;
		} else if (!solicitacaoServico.equals(other.solicitacaoServico))
			return false;
		if (status != other.status)
			return false;
		if (tipoMaquina != other.tipoMaquina)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoSolicitacao [id=" + id + ", descricao=" + descricao
				+ ", dataPedido=" + dataPedido + ", horasNecessarias="
				+ horasNecessarias + ", tipoMaquina=" + tipoMaquina
				+ ", produtor=" + produtor + ", status=" + status
				+ ", motivoRejeicao=" + motivoRejeicao + "]";
	}
}
