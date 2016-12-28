package br.edu.ifpb.sicAgro.beans.entrega;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Entrega;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de uma entrega (distribuição de produto(item de carga))
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class EntregaViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Entrega entrega;

	/**
	 * É iniciando no inicio da renderização da pagina de produtoView,
	 * responsável por obter uma entrega pelo contexto de aplicação.
	 */
	public void preRenderView() {
		entrega = (Entrega) JSFUtils.getParam("entrega");
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

}
