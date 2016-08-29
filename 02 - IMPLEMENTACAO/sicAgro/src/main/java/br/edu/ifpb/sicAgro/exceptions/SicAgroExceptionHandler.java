package br.edu.ifpb.sicAgro.exceptions;

/**
 * 
 * Exceção padrão do sistema
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class SicAgroExceptionHandler extends SicAgroException {

	private static final long serialVersionUID = 1L;

	public SicAgroExceptionHandler(String msg) {
		super(msg);
	}
}
