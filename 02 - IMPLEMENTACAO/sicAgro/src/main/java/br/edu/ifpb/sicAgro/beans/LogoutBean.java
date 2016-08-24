package br.edu.ifpb.sicAgro.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * Bean responsável por gerenciar operação de logout no sistema
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class LogoutBean implements Serializable {

	private static final long serialVersionUID = 5867258552343372479L;

	/**
	 * Método responsável por recuperar a sessão aberta do servidor e
	 * finaliza-la
	 */
	public String efetuarLogout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/pages/login.xhtml?faces-redirect=true";
	}
}
