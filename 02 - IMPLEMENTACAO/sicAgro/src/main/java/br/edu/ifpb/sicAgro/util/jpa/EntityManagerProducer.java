package br.edu.ifpb.sicAgro.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitária para criar e fechar entityManager usando CDI.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class EntityManagerProducer {

	/**
	 * 
	 * @return
	 */
	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEMF() {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("SicAgroPU");
		} catch (Throwable t) {
			throw t;
		}
		return emf;
	}

    /**
     * 
     * @return
     */
    @Produces
    @RequestScoped
    public EntityManager createEM(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * 
     * @param entityManager
     */
    public void closeEM(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
    
    /**
     * 
     * @param emf
     */
	public void closeEMF(@Disposes EntityManagerFactory emf) {
		emf.close();
	}
}
