package br.edu.ifpb.sicAgro.beans.solicitacaoServicoMaquinas;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager bean responsável por gerenciar a edição de uma solicitação de serviço.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class SolicitacaoServicoEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private ProdutorService produtorService;
	
	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;

	private SolicitacaoServico solicitacaoServico;
	private PedidoSolicitacao pedidoSolicitacao;
	
	/**
	 * É chamado na renderização da página, método auxilia
	 * no instaciamento de classes de acordo com o tipo de 
	 * operação (salvar/editar)
	 */
	public void preRenderView() {
		if (solicitacaoServico == null) {
			solicitacaoServico = new SolicitacaoServico();
			solicitacaoServico.setPedidoSolicitacao(pedidoSolicitacao);
			
			if(isPedidoExists()){
				solicitacaoServico.setProdutor(pedidoSolicitacao.getProdutor());
				solicitacaoServico.setDateSolicitation(pedidoSolicitacao.getDataPedido());
			}
		}
	}

	/**
	 * Salva uma solicitação de serviço.
	 * 
	 * @throws SicAgroException
	 */
	public void save() throws SicAgroException {
		if(solicitacaoServico.getTimeWorkeds() != null){
			this.veiculoService.setHorimetroVeiculo(solicitacaoServico.getVeiculo(), solicitacaoServico.getTimeWorkeds());
		}
		
		if (isSolicitacaoEdited()) {
			solicitacaoServicoService.update(solicitacaoServico);
			MessageUtils.messageSucess("Solicitação atualizada com sucesso.");
			
		} else {
			solicitacaoServicoService.add(solicitacaoServico);
			if(isPedidoExists()){
				pedidoSolicitacao.setSolicitacaoServico(solicitacaoServico);
				
				if(solicitacaoServico.getCompleted()){
					pedidoSolicitacaoService.completarPedidoSolicitacao(pedidoSolicitacao);
				}else{
					pedidoSolicitacao.setStatus(PedidoStatus.ACCEPTED);
					pedidoSolicitacaoService.update(pedidoSolicitacao);
				}
			}
			MessageUtils.messageSucess("Solicitação efetuada com com sucesso.");
		}
		JSFUtils.rederTo("solicitacaoView.xhtml");
		JSFUtils.setParam("solicitacao", solicitacaoServico);	
	}
	
	/**
	 * Verifica se uma solicitação de serviço já possui id.
	 * 
	 * @return true/false
	 */
	public boolean isSolicitacaoEdited() {
		return solicitacaoServico.getId() != null;
	}
	
	/**
	 * Verifica se um pedido de solicitação já possui ID.
	 * 
	 * @return true/false
	 */
	public boolean isPedidoExists(){
		return pedidoSolicitacao != null;
	}

	/**
	 * Consulta veiculos pelo identificador.
	 * Necessário na utilização do componente autocomplete no 
	 * formulário de solicitação de serviço.
	 * 
	 * @param identification
	 * 
	 * @return
	 */
	public List<Veiculo> listVeiculos(String identification) {
		return veiculoService.findByIdentification(identification);
	}

	/**
	 * Consulta lista de funcionario de acordo com o nome.
	 * Necessário na utilização do componente autocomplete no
	 * formulário de solicitação de serviço.
	 * 
	 * @param name
	 * @return
	 */
	public List<Funcionario> listFuncionarios(String name) {
		return funcionarioService.findDriversByName(name);
	}
	
	/**
	 * Consulta lista de produtores de acordo com o nome.
	 * necessário na utilização do componente autocomplete no formulário
	 * de solicitação de serviço;
	 * @param query
	 * @return
	 */
	public List<Produtor> listProdutores(String query){
		return produtorService.findByName(query);
	}
	
	/**
	 * Método para auxiliar no cancelamento de uma solicitação, i.e.,
	 * diante do cadastro de uma solicitação o usuário pode concluir e em seguida desistir
	 * de cde concluir uma solicitação, quando o mesmo desiste esse método é chamado.
	 */
	public void cancelConclusao(){
		solicitacaoServico.setCompleted(false);
		solicitacaoServico.setDateRealization(null);
		solicitacaoServico.setTimeWorkeds(null);
	}
	
	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}

	public PedidoSolicitacao getPedidoSolicitacao() {
		return pedidoSolicitacao;
	}

	public void setPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao) {
		this.pedidoSolicitacao = pedidoSolicitacao;
	}
	
}
