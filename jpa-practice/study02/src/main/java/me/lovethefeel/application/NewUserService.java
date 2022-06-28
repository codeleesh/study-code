package me.lovethefeel.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.User;

public class NewUserService {

    public void saveNewUser(final User user) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(user);
            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
