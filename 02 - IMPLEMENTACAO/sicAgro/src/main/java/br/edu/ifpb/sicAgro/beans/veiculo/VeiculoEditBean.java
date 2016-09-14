package br.edu.ifpb.sicAgro.beans.veiculo;

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
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager bean responsável por gerenciar a adição e edição de um 
 * veículo (Maquina).
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class VeiculoEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService veiculoService;
	
	@Inject
	private EnderecoService enderecoService;

	private Veiculo veiculo;

	private List<TypeMachine> typesMachine = new ArrayList<TypeMachine>();

	/**
	 * É chamado na renderização da página, método auxilia
	 * no instaciamento de classes de acordo com o tipo de 
	 * operação (salvar/editar)
	 */
	public void preRenderView() {
		if (veiculo == null) {
			veiculo = new Veiculo();
		}
	}

	/**
	 * Inicializa lista de tipos de máquinas.
	 * 
	 */
	@PostConstruct
	public void init() {
		typesMachine = Arrays.asList(TypeMachine.values());
	}

	/**
	 * Salva/edita um veiculo na base de dados.
	 * 
	 * @throws SicAgroException
	 */
	public void save() throws SicAgroException {
		if (isVeiculoEdited()) {
			veiculoService.update(veiculo);
			MessageUtils.messageSucess("Veículo atualizado com sucesso.");
		} else {
			veiculoService.add(veiculo);
			MessageUtils.messageSucess("Veículo cadastrado com sucesso.");
		}
		JSFUtils.rederTo("veiculos.xhtml");
	}

	/**
	 * Verifica se um veículo já pussuí ID.
	 * 
	 * @return
	 */
	public boolean isVeiculoEdited() {
		return veiculo.getId() != null;
	}
	
	/**
	 * Carrega lista de cidades de acordo com o estado escolhido.
	 * 
	 */
	public void loadCities(){
		enderecoService.getCities(veiculo.getUf(), veiculo.getUf().getCodigo());
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<TypeMachine> getTypesMachine() {
		return typesMachine;
	}

	public void setTypesMachine(List<TypeMachine> typesMachine) {
		this.typesMachine = typesMachine;
	}

}
