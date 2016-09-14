package br.edu.ifpb.sicAgro.beans.produtor;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de um produtor
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ManagedBean
@ViewScoped
public class ProdutorViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produtor produtor;

	/**
	 * É iniciando no inicio da renderização da pagina de produtorView,
	 * responsável por obter um produtor pelo contexto de aplicação.
	 */
	public void preRenderView() {
		produtor = (Produtor) JSFUtils.getParam("produtorToDetail");
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
}
