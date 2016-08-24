package br.edu.ifpb.sicAgro.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private final EntityManagerFactory entityManagerFactory;

    public EntityManagerProducer() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("SicAgroPU");
    }


    /**
     * 
     * @return
     */
    @Produces
    @RequestScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * 
     * @param entityManager
     */
    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
