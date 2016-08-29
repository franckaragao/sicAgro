package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface PedidoSolicitacaoService extends Service<PedidoSolicitacao, Long>{
	
	 Long getTotalPedidosByStatutus(PedidoStatus status);
	 
	 void completarPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao);
	 
	 Long getTotalPedidosByMessages();
	 
	 List<PedidoSolicitacao> findPedidosByProdutor(Produtor produtor);

}
