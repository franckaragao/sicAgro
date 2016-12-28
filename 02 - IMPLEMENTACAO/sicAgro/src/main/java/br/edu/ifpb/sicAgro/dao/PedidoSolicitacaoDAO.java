package br.edu.ifpb.sicAgro.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.model.Produtor;


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
	
	/**
	 * 
	 * @param produtor
	 * @return
	 */
	List<PedidoSolicitacao> findPedidosByProdutor(Produtor produtor);
	
	/**
	 * 
	 * @param nDays
	 * @param state
	 * @return
	 */
	Map<Date, Integer> getPedidosPorPeriodo(Integer nDays, PedidoStatus status);

}
