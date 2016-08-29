package br.edu.ifpb.sicAgro.beans.pedidoSolicitacao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.userSession.UserLogged;

/**
 * Manage bean responsável por gerenciar a pedidos de solicitações
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class PedidoSolicitacaoBean implements Serializable {

	private static final long serialVersionUID = -6153253920691800226L;

	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;
	
	@Inject
	@UserLogged
	private Conta conta;

	private List<PedidoSolicitacao> pedidosSolicitacao;

	private PedidoSolicitacao selectedPedidoSolicitacao;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.listPedidos();
	}

	/**
	 * 
	 */
	public void remove() {
		pedidoSolicitacaoService.remove(selectedPedidoSolicitacao);
		MessageUtils.messageSucess("Pedido removido com sucesso.");
		JSFUtils.rederTo("pedidosSolicitacao.xhtml");
	}
	
	/**
	 * 
	 */
	public void renderTo() {
		JSFUtils.rederTo("pedidoSolicitacaoView.xhtml");
		JSFUtils.setParam("pedidoSolicitacao", selectedPedidoSolicitacao);
	}

	public void listPedidos() {
		if(isUserLoggedIsProdutor()){
			this.pedidosSolicitacao = pedidoSolicitacaoService.findPedidosByProdutor(conta.getProdutor());
		}else{
			this.pedidosSolicitacao = pedidoSolicitacaoService.findAll();
		}
	}
	
	public boolean isUserLoggedIsProdutor() {
		if (conta.getUserRole().equals(UserRole.PRODUTOR))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	public String updateLabelStatus(PedidoStatus status) {
		if (status.equals(PedidoStatus.COMPLETED)) {
			return PedidoStatus.LB_COMPLETED.getDescription();

		} else if (status.equals(PedidoStatus.ACCEPTED)) {
			return PedidoStatus.LB_ACCEPTED.getDescription();

		} else if (status.equals(PedidoStatus.PROGRESS)) {
			return PedidoStatus.LB_PROGRESS.getDescription();

		} else {
			return PedidoStatus.LB_NOT_ACCEPTED.getDescription();
		}
	}

	public List<PedidoSolicitacao> getPedidosSolicitacao() {
		return pedidosSolicitacao;
	}

	public void setPedidosSolicitacao(List<PedidoSolicitacao> pedidosSolicitacao) {
		this.pedidosSolicitacao = pedidosSolicitacao;
	}

	public PedidoSolicitacao getSelectedPedidoSolicitacao() {
		return selectedPedidoSolicitacao;
	}

	public void setSelectedPedidoSolicitacao(
			PedidoSolicitacao selectedPedidoSolicitacao) {
		this.selectedPedidoSolicitacao = selectedPedidoSolicitacao;
	}

}
