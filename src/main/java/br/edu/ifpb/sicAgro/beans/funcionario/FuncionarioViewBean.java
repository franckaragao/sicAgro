package br.edu.ifpb.sicAgro.beans.funcionario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Funcionario;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de um funcionário
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class FuncionarioViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	/**
	 * É iniciando no inicio da renderização da pagina de produtoView,
	 * responsável por obter um funcionário pelo contexto de aplicação.
	 */
	public void preRenderView() {
		funcionario = (Funcionario) JSFUtils.getParam("funcionario");
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
