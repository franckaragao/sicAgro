package br.edu.ifpb.sicAgro.dao;

import br.edu.ifpb.sicAgro.model.Carga;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface CargaDAO extends DAO<Carga, Long>{
	
	Long getTotalCargas();

}
