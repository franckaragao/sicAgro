package br.edu.ifpb.sicAgro.beans.carga;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Carga;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de uma carga
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class CargaViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Carga carga;

	/**
	 * É iniciando no inicio da renderização da pagina de produtoView,
	 * responsável por obter um produto pelo contexto de aplicação.
	 */
	public void preRenderView() {
		carga = (Carga) JSFUtils.getParam("carga");
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}
}
