package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;

/**
 * Interface responsável por definir todas as operações específicas de dados 
 * para um produtor
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ProdutorDAO extends DAO<Produtor, Long>{
	
	/**
	 * Filtro de busca.
	 * 
	 * @param filter
	 * @return
	 */
	List<Produtor> filter(ProdutorFilter filter);
	
	/**
	 * Busca um produtor pelo nome.
	 * 
	 * @param name
	 * @return
	 * @throws SicAgroException
	 */
	List<Produtor> findByName(String name) throws SicAgroException;
	
	/**
	 * Consulta um produtor pelo CPF.
	 * 
	 * @param cpf cpf
	 * @return Produtor
	 * @throws SicAgroException
	 */
	Produtor findByCPF(String cpf) throws SicAgroException;
	
	/**
	 * Busca um produtor pelo código.
	 * 
	 * @param cod
	 * @return
	 * @throws SicAgroException
	 */
	Produtor findByCod(Integer cod) throws SicAgroException;
	
	/**
	 * Consulta a quantidade de produtores cadastrados.
	 * 
	 * @return 
	 * @throws SicAgroException
	 */
	Long getTotalProdutores() throws SicAgroException;
	
	/**
	 * Verifica se um CPF existe na base de dados.
	 * @param cpf
	 * @return
	 * @throws SicAgroException
	 */
	boolean isCPFExists(String cpf) throws SicAgroException;

}
