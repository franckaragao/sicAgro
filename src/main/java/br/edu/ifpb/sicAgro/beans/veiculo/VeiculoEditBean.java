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
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

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

	public void preRenderView() {
		if (veiculo == null) {
			veiculo = new Veiculo();
		}
	}

	@PostConstruct
	public void init() {
		typesMachine = Arrays.asList(TypeMachine.values());
	}

	public void save() {
		if (isVeiculoEdited()) {
			veiculoService.update(veiculo);
			MessageUtils.messageSucess("Veículo atualizado com sucesso.");
		} else {
			veiculoService.add(veiculo);
			MessageUtils.messageSucess("Veículo cadastrado com sucesso.");
		}
		JSFUtils.rederTo("veiculos.xhtml");
	}

	public boolean isVeiculoEdited() {
		return veiculo.getId() != null;
	}
	
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