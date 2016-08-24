package br.edu.ifpb.sicAgro.filter;

import java.io.Serializable;
import java.util.Date;

import br.edu.ifpb.sicAgro.enumerations.SolicitationState;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.Veiculo;

public class SolicitacaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SolicitacaoFilter filter;

	private String resumoSolicitacao;
	private SolicitationState status;
	private Veiculo veiculo;
	private Funcionario funcionario;
	private Date dateInit;
	private Date DateEnd;

	public SolicitacaoFilter() {

	}

	public static SolicitacaoFilter getInstance() {
		filter = new SolicitacaoFilter();
		return filter;
	}

	public String getResumoSolicitacao() {
		return resumoSolicitacao;
	}

	public void setResumoSolicitacao(String resumoSolicitacao) {
		this.resumoSolicitacao = resumoSolicitacao;
	}

	public SolicitationState getStatus() {
		return status;
	}

	public void setStatus(SolicitationState status) {
		this.status = status;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public Date getDateEnd() {
		return DateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		DateEnd = dateEnd;
	}
}
