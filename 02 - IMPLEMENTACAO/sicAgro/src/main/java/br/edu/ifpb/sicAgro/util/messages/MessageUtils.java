package br.edu.ifpb.sicAgro.util.messages;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.edu.ifpb.sicAgro.enumerations.MessagesType;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class MessageUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	

	/**
	 * 
	 * @param msg
	 */
	public static void messageSucess(String msg) {
		messageFaces(MessagesType.SUCESS.getMessage(), msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void messageError(String msg) {
		messageFaces(MessagesType.ERROR.getMessage(), msg);

	}

	/**
	 * 
	 * @param msg
	 */
	public static void messageWarn(String msg) {
		messageFaces(MessagesType.WARNING.getMessage(), msg);

	}

	/**
	 * 
	 * @param type
	 * @param message
	 */
	private static void messageFaces(String type, String message) {

		Severity severity = FacesMessage.SEVERITY_WARN;

		if (type.equals(MessagesType.ERROR.getMessage())) {
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}
		if (type.equals(MessagesType.SUCESS.getMessage())) {
			severity = FacesMessage.SEVERITY_INFO;
		}

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, type, message));
	}
}
