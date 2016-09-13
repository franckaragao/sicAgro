package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.EntregaService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * Classe de serviço responsável por implemtar operações especificas
 * de um produtor.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class ProdutorServiceImpl extends GenericServiceImpl<Produtor, Long> implements ProdutorService{

	
	private static final long serialVersionUID = -9171041213373059450L;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;
	
	@Inject
	private EntregaService entregaService; 
	
	public ProdutorServiceImpl() {
	}

	/**
	 * Construtor responsávelpor ejetar o dao de produtor no dao generico do service
	 * generico.
	 * 
	 * @param produtorDAO
	 */
	@Inject
	public ProdutorServiceImpl(ProdutorDAO produtorDAO) {
		this.dao = produtorDAO;
	}
	
	/**
	 * salva um produtor considerando restrições de atributos.
	 * 
	 */
	@Override
	@Transactional
	public void add(Produtor entity) throws SicAgroExceptionHandler {
		if(isCPFExists(entity.getCpf())){
			throw new SicAgroExceptionHandler("Já existe um produtor cadastrado com este CPF: "+ entity.getCpf());
		}
		Conta conta = contaService.findByUserName(entity.getCpf());
		if(conta != null){
			throw new SicAgroExceptionHandler("Já existe um usuário cadastrado com este CPF: "+ entity.getCpf());
		}
		Produtor produtor = finByCod(entity.getCod());
		if(produtor != null){
			throw new SicAgroExceptionHandler("Já existe um produtor cadastrado com este código: "+ entity.getCod());
		}
		dao.add(entity);
	}
	
	/**
	 * Remove um produtor considerando as suas restrições 
	 * de remoção.
	 * 
	 */
	@Override
	@Transactional
	public void remove(Produtor entity) throws SicAgroException {
		Long count = solicitacaoServicoService.getCountSolicitacoesByProdutor(entity);
		count = entregaService.getCountEntregasByProdutor(entity);
		if(count > 0){
			throw new SicAgroExceptionHandler(
					"Produtor não não pode ser removido, existe solicitações de serviços ou entregas registradas para o mesmo.");
		}
		dao.remove(entity);
	}
	
	/**
	 * Filtro com diversos atributos.
	 * 
	 */
	@Override
	public List<Produtor> filter(ProdutorFilter filter) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.filter(filter);
	}
	
	/**
	 * Verifica se CPF existe.
	 * 
	 * @param cpf
	 * @return
	 */
	private boolean isCPFExists(String cpf){
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		boolean result = false;
		try {
			result = produtorDAO.isCPFExists(cpf);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Consulta a quantidade de produtores.
	 */
	@Override
	public Long getTotalProdutores() {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		Long result = 0l;
		try {
			result = produtorDAO.getTotalProdutores();
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Consulta produtor pelo CPF.
	 */
	@Override
	public Produtor findByCPF(String cpf) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		Produtor result = null;
		try {
			result = produtorDAO.findByCPF(cpf);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Consulta produtor pelo nome.
	 */
	@Override
	public List<Produtor> findByName(String name) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		List<Produtor> results = null;
		try {
			results = produtorDAO.findByName(name);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Consulta um produtor pelo código.
	 */
	@Override
	public Produtor finByCod(Integer cod) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		Produtor result = null;
		try {
			result = produtorDAO.findByCod(cod);
		} catch (SicAgroException e) {
			e.printStackTrace();
		}
		return result;
	}
}
