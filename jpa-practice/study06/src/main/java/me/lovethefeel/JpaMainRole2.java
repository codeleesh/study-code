package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.acl.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class JpaMainRole2 {

    private static Logger logger = LoggerFactory.getLogger(JpaMainRole2.class);

    public static void main(String[] args) {

    }

    private static void saveRole(final String roleId) {

        logger.info("saveRole roleId {}", roleId);

        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            final Set<String> role01 = Set.of("ROLE01", "ROLE02");
            final Role role = new Role(roleId, "관리자");
            em.persist(role);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
