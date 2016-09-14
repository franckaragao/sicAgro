package br.edu.ifpb.sicAgro.beans.funcionario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Endereco;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

@Named
@ViewScoped
public class FuncionarioEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private EnderecoService enderecoService;
	
	@Inject
	private ContaService contaService;

	private List<UserRole> roleTypes = new ArrayList<UserRole>();

	private Funcionario funcionario;

	private String confirmPassword;

	public void preRenderView() {
		if (funcionario == null) {
			funcionario = new Funcionario();
			funcionario.setAcount(new Conta());
			funcionario.setEndereco(new Endereco());
		} else {
			this.loadCities();
		}
	}

	@PostConstruct
	public void init() {
		roleTypes = Arrays.asList(UserRole.values());
	}

	public void save() throws SicAgroException {
		if (isEdited()) {
			funcionarioService.update(funcionario);
			MessageUtils.messageSucess("Funcionário atualizado com sucesso.");

		} else {
			setDefaultAtributtes();
			funcionarioService.add(funcionario);
			MessageUtils.messageSucess("Funcionário cadastrado com sucesso.");
		}
		JSFUtils.rederTo("funcionarioView.xhtml");
		JSFUtils.setParam("funcionario", funcionario);

	}
	
	private void setDefaultAtributtes() throws SicAgroExceptionHandler{
		funcionario.getAcount().setUserName(funcionario.getCpf());
		funcionario.getAcount().setPassword(funcionario.getCpf());;
		funcionario.getAcount().setFuncionario(funcionario);
		contaService.criptografarSenha(funcionario.getAcount());
	}

	public boolean isEdited() {
		return funcionario.getId() != null;
	}

	public void loadCities() {
		if (funcionario.getEndereco().getState() != null)
			enderecoService.getCities(funcionario.getEndereco().getState(),
					funcionario.getEndereco().getState().getCodigo());
	}

	/**
	 * Método auxiliar utilizado para resolver o problema do auto preenchimento
	 * de campos de endereço, caso um endereço digitado pelo usuário já exista.
	 * Já que usando o autocomplete com para o campo endereço, quando não
	 * existir um endereço já cadastrado seja possível continuar o cadastro
	 * 
	 * @param queryAddress
	 */
	public void updateCamposEnderecos(String queryAddress) {
		List<Endereco> address = enderecoService.findByAddress(queryAddress);
		
		if(address != null && address.get(0).getId() != null)
			this.funcionario.setEndereco(address.get(0));
			this.funcionario.getEndereco().setId(null);
	}

	public List<UserRole> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<UserRole> roleTypes) {
		this.roleTypes = roleTypes;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
