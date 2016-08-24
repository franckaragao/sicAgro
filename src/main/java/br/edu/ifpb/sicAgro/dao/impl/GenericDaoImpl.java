package br.edu.ifpb.sicAgro.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.sicAgro.dao.DAO;

/**
 * Classe genrica que implementa métodos básicos de um crud
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public abstract class GenericDaoImpl<T, K> implements Serializable, DAO<T, K> {

	private static final long serialVersionUID = -9015078382878893066L;

	@Inject
	protected EntityManager entityManager;

	private Class<T> type;

	/**
	 * Construtor responsável por obter o tipo (classe) concreto de retorno do
	 * generics
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * Método generico responsável por pesistir uma entidade no banco de dados
	 * 
	 * @param entity
	 *            - entidade correspondente a ser persistido
	 */
	public void add(T entity) {
		entityManager.persist(entity);
	}

	/**
	 * Método generico responsável por atualizar uma entidade no banco de dados
	 * 
	 * @param entity
	 *            - entidade correspondente a ser atualizada
	 * @return - entidade atualizada
	 */
	public T update(T entity) {

		T result = entityManager.merge(entity);
		this.entityManager.flush();
		this.entityManager.detach(result);

		return result;

	}

	/**
	 * Método genérico responsável por deletar uma entidade presente no banco de
	 * dados
	 * 
	 * @param entity
	 *            entidade a ser deletada do banco de dados
	 */
	public void remove(T entity) {
		T produto = entityManager.merge(entity);
		entityManager.remove(produto);
	}

	/**
	 * Método generico responsável por buscar uma determinada entidade por sua
	 * identificação no banco de dados
	 * 
	 * @param id
	 *            - identificação única da entidade
	 * @return - entidade encontrada
	 */
	public T findById(K id) {
		return (T) entityManager.find(type, id);
	}

	/**
	 * Método generico responsável por buscar e retornar todas as entidades no
	 * banco de dados correspondente ao @T (tipo da entidade)
	 * 
	 * @return lista com todos as entidades persistidas de acordo com seu tipo
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = entityManager.createQuery("SELECT t FROM "
				+ type.getName() + " t");
		return query.getResultList();
	}
}
