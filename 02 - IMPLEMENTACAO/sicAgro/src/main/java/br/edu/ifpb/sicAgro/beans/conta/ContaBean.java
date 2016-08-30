package br.edu.ifpb.sicAgro.beans.conta;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Pessoa;
import br.edu.ifpb.sicAgro.util.userSession.UserLastAccess;
import br.edu.ifpb.sicAgro.util.userSession.UserLogged;
import br.edu.ifpb.sicAgro.util.userSession.UserNameSession;

/**
 * Bean responsável por gerenciar informações da conta do usuário logado
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@SessionScoped
public class ContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@UserLogged
	private Conta conta;
	
	@Inject
	@UserLastAccess
	private String lastAccess;

	@Inject
	@UserNameSession
	private String userName;
	
	public String getLastAccess() {
		return lastAccess;
	}

	public String getUserName() {
		return userName;
	}

	public Pessoa getUsuario() {
		if(conta.getUserRole().equals(UserRole.PRODUTOR))
			return conta.getProdutor();
		return conta.getFuncionario();
	}
	
	public Integer getTyperUserLogged(){
		
		switch (conta.getUserRole()) {
		case ADMIN:
			return 1;
		case DRIVER:
			return 2;
		case ATTENDANT:
			return 3;
		case PRODUTOR:
			return 4;
		default:
			return 0;
		}
	}

	public Conta getConta() {
		return conta;
	}
}
