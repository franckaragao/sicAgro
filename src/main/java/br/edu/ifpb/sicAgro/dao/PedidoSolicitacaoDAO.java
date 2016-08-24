package br.edu.ifpb.sicAgro.dao;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;


/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface PedidoSolicitacaoDAO extends DAO<PedidoSolicitacao, Long>{
	
	/**
	 * 
	 * @param status status do pedido.
	 * @return lista contendo todos os pedidos com base no statatus
	 * @throws SicAgroException
	 */
	Long getTotalPedidosByStatutus(PedidoStatus status);
	
	/**
	 * 
	 * @return
	 */
	Long getTotalPedidosByMessage();

}
