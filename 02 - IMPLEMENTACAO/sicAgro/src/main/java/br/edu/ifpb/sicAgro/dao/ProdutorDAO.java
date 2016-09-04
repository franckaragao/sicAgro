package br.edu.ifpb.sicAgro.dao;

import java.util.List;

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
	
	List<Produtor> filter(ProdutorFilter filter);
	
	List<Produtor> findByName(String name);
	
	Produtor findByCPF(String cpf);
	
	Long getTotalProdutores();
	
	boolean isCPFExists(String cpf);

}
