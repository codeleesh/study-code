package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.acl.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaMainRole {

    private static Logger logger = LoggerFactory.getLogger(JpaMainRole.class);

    public static void main(String[] args) {

        CommonEntityMangerFactory.init();
        final String roleId = "R11";
        saveRole(roleId);
        readRole(roleId);
    }

    private static void saveRole(final String roleId) {

        logger.info("saveRole roleId {}", roleId);

        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //final Set<String> role01 = Set.of("ROLE01", "ROLE02");
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

    private static void readRole(final String roleId) {

        logger.info("readRole");

        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            final Role role = em.find(Role.class, roleId);
            logger.info("role id: {}", role.getId());
            for (String perm : role.getPermissions()) {
                logger.info("perm: {}", perm);
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
