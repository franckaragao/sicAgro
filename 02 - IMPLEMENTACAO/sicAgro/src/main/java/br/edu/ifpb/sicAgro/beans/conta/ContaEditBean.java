package br.edu.ifpb.sicAgro.beans.conta;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;
import br.edu.ifpb.sicAgro.util.userSession.UserLogged;

@Named
@RequestScoped
public class ContaEditBean implements Serializable {

	private static final long serialVersionUID = -3706977559975288412L;

	@Inject
	@UserLogged
	private Conta conta;

	@Inject
	private ContaService contaService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private ProdutorService produtorService;

	private String oldPassword;

	private Conta oldAcount = new Conta();

	private String newPassword;

	private String repeatPassword;

	@PostConstruct
	public void init() {
		oldPassword = conta.getPassword();
	}

	public void updateAcountLogged() throws SicAgroExceptionHandler {
		contaService.criptografarSenha(oldAcount);
		if (!oldPassword.equals(oldAcount.getPassword())) {
			throw new SicAgroExceptionHandler("Senha atual não confere!");
		}
		if (!repeatPassword.equals(newPassword)) {
			throw new SicAgroExceptionHandler("Senhas não coincidem!");
		}
		conta.setPassword(newPassword);
		contaService.criptografarSenha(conta);
		updateByTypeUser();
		MessageUtils.messageSucess("Dados de acesso alterados com sucesso.");

	}

	/**
	 * <pre>
	 * Método necessário para resolver problema de persistir
	 * objeto  que é gerenciado pelo CDI, no caso a conta.
	 * Neste caso pesiste pelo seus tipos de usuŕios.
	 * </pre>
	 * 
	 */
	private void updateByTypeUser() {
		if (conta.getUserRole().equals(UserRole.PRODUTOR)) {
			produtorService.update(conta.getProdutor());
		} else {
			funcionarioService.update(conta.getFuncionario());
		}
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Conta getOldAcount() {
		return oldAcount;
	}

	public void setOldAcount(Conta oldAcount) {
		this.oldAcount = oldAcount;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
}
