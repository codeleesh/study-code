package me.lovethefeel.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.User;

public class ChangeNameService {

    public void changeName(String email, String newName) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, email);
            if (user == null) {
                throw new EntityNotFoundException();
            }
            user.changeName(newName);
            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
