package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.article.Intro;
import me.lovethefeel.domain.article.Writer;
import me.lovethefeel.domain.common.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainSecondaryTables {

    private static Logger logger = LoggerFactory.getLogger(MainSecondaryTables.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();
        Long id = save();
//        update(id);
        print(id);
//        delete(id);
        CommonEntityMangerFactory.close();
    }

    private static Long save() {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Writer w = new Writer("name",
                    new Address("주소1", "주소2", "12345"),
                    new Intro("text/plain", "소개글"));

            em.persist(w);

            tx.commit();

            return w.getId();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private static void print(Long id) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Writer writer = em.find(Writer.class, id);
            logger.info("writer: {}", writer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private static void update(Long id) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Writer writer = em.find(Writer.class, id);
            writer.getIntro().setContent("변경");
            writer.setAddress(new Address("새주소1", "새주소2", "11111"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private static void delete(Long id) {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Writer writer = em.find(Writer.class, id);
            em.remove(writer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
