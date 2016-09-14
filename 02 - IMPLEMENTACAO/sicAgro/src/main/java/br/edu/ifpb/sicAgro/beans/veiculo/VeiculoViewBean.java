package br.edu.ifpb.sicAgro.beans.veiculo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de um veículo
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class VeiculoViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Veiculo veiculo;

	/**
	 * É iniciando no inicio da renderização da pagina de produtoView,
	 * responsável por obter um veiculo pelo contexto de aplicação.
	 */
	public void preRenderView() {
		veiculo = (Veiculo) JSFUtils.getParam("veiculo");
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
