package br.edu.ifpb.sicAgro.beans.solicitacaoServicoMaquinas;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.filter.VeiculoFilter;
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
	
	private VeiculoFilter veiculoFilter = VeiculoFilter.getInstance();
	private ProdutorFilter produtorFilter = ProdutorFilter.getInstance();

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
		JSFUtils.rederTo("solicitacoes.xhtml");
	}
	
	public boolean isSolicitacaoEdited() {
		return solicitacaoServico.getId() != null;
	}
	
	public boolean isPedidoExists(){
		return pedidoSolicitacao != null;
	}

	public List<Veiculo> listVeiculos(String identification) {
		return veiculoService.findByIdentification(identification);
	}

	public List<Funcionario> listFuncionarios(String name) {
		return funcionarioService.findDriversByName(name);
	}
	
	public List<Produtor> listProdutores(String query){
		produtorFilter.setName(query);
		return produtorService.filter(produtorFilter);
	}
	
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
