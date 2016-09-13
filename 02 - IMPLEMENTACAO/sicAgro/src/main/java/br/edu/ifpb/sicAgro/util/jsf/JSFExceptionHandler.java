package br.edu.ifpb.sicAgro.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Classe utilitária para tratar excessões de renderização de paginas JSF,
 * quando alterações são feitas do lado do servidor e não atualizadas no
 * cliente.
 * 
 * Quando uma exceção deste tipo é lançada o cliente é redirecionando para
 * pagina inicial do sistema
 * 
 * @author franck
 *
 */
public class JSFExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public JSFExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapped = exceptionHandler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
				.iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			Throwable exception = context.getException();
			
			SicAgroExceptionHandler sicAgroException = getSicAgroException(exception);

			boolean handled = false;

			try {
				if (exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/");
					
				}else if(sicAgroException != null){
					handled = true;
					MessageUtils.messageError(sicAgroException.getMessage());
					
				} else {
					handled = true;
 					redirect("/500.xhtml");
				}
			} finally {
				if (handled) {
					events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private SicAgroExceptionHandler getSicAgroException(Throwable exception) {
		
		if(exception instanceof SicAgroExceptionHandler){
			return (SicAgroExceptionHandler) exception;
			
		} else if(exception.getCause() != null){
			return getSicAgroException(exception.getCause());
		}

		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException ex) {
			throw new FacesException(ex);
		}
	}
}
