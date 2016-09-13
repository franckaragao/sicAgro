package br.edu.ifpb.sicAgro.beans.funcionario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

@Named
@RequestScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService funcionarioService;

	private List<Funcionario> funcionarios;

	private Funcionario selectedFuncionario;

	@PostConstruct
	public void init() {
		this.listProdutores();
	}

	public void listProdutores() {
		this.funcionarios = funcionarioService.findAll();
	}

	public void remove() throws SicAgroException {
		funcionarioService.remove(selectedFuncionario);
		MessageUtils.messageSucess("Funcionário removido com sucesso.");
		JSFUtils.rederTo("funcionarios.xhtml");
	}

	/**
	 * <pre>
	 * Utilizado como solução pra conseguir passar um paramtro apartir do 
	 * manager bean, devido a forma que a linha da datatable é selecionada,
	 * desta forma sem usar um componente que tenha um outcome, tem-se a necessidade
	 * de fazer o manager bean redireiconar para outra página, diante isso o parametro
	 * deve ser passado do manager bean.
	 * </pre>
	 */
	public void renderTo() {
		JSFUtils.rederTo("funcionarioView.xhtml");
		JSFUtils.setParam("funcionario", selectedFuncionario);
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getSelectedFuncionario() {
		return selectedFuncionario;
	}

	public void setSelectedFuncionario(Funcionario selectedFuncionario) {
		this.selectedFuncionario = selectedFuncionario;
	}

}
