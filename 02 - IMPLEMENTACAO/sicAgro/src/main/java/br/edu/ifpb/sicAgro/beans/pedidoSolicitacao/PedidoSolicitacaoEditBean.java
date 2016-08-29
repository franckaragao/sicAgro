package br.edu.ifpb.sicAgro.beans.pedidoSolicitacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.TypeMachine;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.userSession.UserLogged;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class PedidoSolicitacaoEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PedidoSolicitacao pedidoSolicitacao;

	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;
	
	@Inject
	@UserLogged
	private Conta conta;

	private List<TypeMachine> maquinas = new ArrayList<TypeMachine>();

	@PostConstruct
	public void init() {
		maquinas = Arrays.asList(TypeMachine.values());
	}

	/**
	 * 
	 */
	public void preRenderView() {
		if (pedidoSolicitacao == null) {
			pedidoSolicitacao = new PedidoSolicitacao();
			pedidoSolicitacao.setProdutor(conta.getProdutor());
		}
	}

	public void save() throws SicAgroException {
		if (isPedidoEdited()) {
			pedidoSolicitacaoService.update(pedidoSolicitacao);
			MessageUtils
					.messageSucess("Pedido de solicitação atualizado com sucesso.");
		} else {
			pedidoSolicitacaoService.add(pedidoSolicitacao);
			MessageUtils
					.messageSucess("Pedido de solicitação efetuado com sucesso.");
		}
		JSFUtils.rederTo("pedidosSolicitacao.xhtml");
	}

	public boolean isPedidoEdited() {
		return pedidoSolicitacao.getId() != null;
	}

	public PedidoSolicitacao getPedidoSolicitacao() {
		return pedidoSolicitacao;
	}

	public void setPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao) {
		this.pedidoSolicitacao = pedidoSolicitacao;
	}

	public List<TypeMachine> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<TypeMachine> maquinas) {
		this.maquinas = maquinas;
	}

}
