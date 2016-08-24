package br.edu.ifpb.sicAgro.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Classe fábrica utilitária, reponsável por criar as excessões handler wrapps
 * lançadas pela aplicação
 * 
 * @author franck
 *
 */

public class JSFExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parentWrapped;

	public JSFExceptionHandlerFactory(ExceptionHandlerFactory parentWrapped) {
		this.parentWrapped = parentWrapped;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JSFExceptionHandler(parentWrapped.getExceptionHandler());
	}

}
