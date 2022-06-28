package me.lovethefeel.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.User;

public class GetUserService {
    public User getUser(String email) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        try {
            User user = em.find(User.class, email);
            if (user == null) {
                throw new EntityNotFoundException();
            }
            return user;
        } finally {
            em.close();
        }
    }
}
