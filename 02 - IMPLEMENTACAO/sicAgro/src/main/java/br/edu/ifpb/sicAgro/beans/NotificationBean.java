package br.edu.ifpb.sicAgro.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;

/**
 * Manager bean responsável por gerenciar dados referente ao
 * gerenciamento de notificações de pedidos de solicitações.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */

@Named
@RequestScoped
public class NotificationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;

	/**
	 * Obtém todos os pedidos de solicitações por status.
	 * 
	 * @return
	 */
	public Long getTotalPedidos() {
		return pedidoSolicitacaoService.getTotalPedidosByStatutus(PedidoStatus.PROGRESS);
	}
	
	public Long getTotalByMessages(){
		return pedidoSolicitacaoService.getTotalPedidosByMessages();
	}
}
