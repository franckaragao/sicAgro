package br.edu.ifpb.sicAgro.services.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ContaDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.services.ContaService;


/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ContaServiceImpl extends GenericServiceImpl<Conta, Long> implements ContaService{

	private static final long serialVersionUID = 1L;
	
	public ContaServiceImpl() {
	}
	
	/**
	 * 
	 * @param dao
	 */
	@Inject
	public ContaServiceImpl(ContaDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 */
	@Override
	public Conta findByMail(String mail) {
		ContaDAO contaDAO = (ContaDAO) this.dao;
		Conta conta = contaDAO.findByMail(mail);
		
		return conta;
	}
	
	/**
	 * 
	 */
	@Override
	public Conta findByUserName(String userName) {
		ContaDAO contaDAO = (ContaDAO) this.dao;
		Conta conta = contaDAO.findByUserName(userName);
		
		return conta;
	}
	
	/**
	 * 
	 */
	public void criptografarSenha(Conta conta) throws SicAgroExceptionHandler {
		conta.setPassword(criptografarSenha(conta.getPassword()));
	}

	/**
	 * Método que criptografa uma dada senha usando o método hash SHA-256.
	 * 
	 * @param password
	 *            senha a ser criptografada
	 * @return senha criptografada
	 * @throws DacaServiceException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private static String criptografarSenha(String password) throws SicAgroExceptionHandler{
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new SicAgroExceptionHandler("Não foi possível criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new SicAgroExceptionHandler("Não foi possível criptografar a senha!");
		}
	}
}
