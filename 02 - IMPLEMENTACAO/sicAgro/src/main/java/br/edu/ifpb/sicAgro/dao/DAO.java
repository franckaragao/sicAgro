package br.edu.ifpb.sicAgro.dao;

import java.util.List;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 * @param <K>
 */
public interface DAO<T,K>{
	
	/**
	 * 
	 * @param entity
	 */
	public void add(T entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	
	/**
	 * 
	 * @param entity
	 */
	public void remove(T entity);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T findById(K id);
	
	/**
	 * 
	 * @return
	 */
	public List<T> findAll();

}
