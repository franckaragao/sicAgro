package br.edu.ifpb.sicAgro.util.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

/**
 * Classe utilitária do JSF, fornece métodos utilitários para ajudar nos bens do
 * projeto
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class JSFUtils implements Serializable {

	private static final long serialVersionUID = 712599930272775732L;

	/**
	 * Método utilitário para rederizar contexto do JSF para outa pagina
	 * 
	 * @param page
	 */
	public static void rederTo(String page) {
	      try {
	            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
	            FacesContext.getCurrentInstance().responseComplete();
	        } catch (IOException ex) {
	            throw new FacesException(ex);
	        }
	}

	/**
	 * Método serve para passar um objeto para outro contexto (pagina) usando
	 * scopo de aplicação
	 * 
	 * @param tag
	 *            - identificação de um objeto que será passado por parametro
	 * @param obj
	 *            - objeto que será passado por parametro
	 */
	public static void setParam(String tag, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(tag, obj);
	}

	/**
	 * 
	 * @param tag
	 *            identificação de um objeto que será recuperado do contexto
	 * @return objeto referente
	 */
	public static Object getParam(String tag) {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(tag);
	}
}
