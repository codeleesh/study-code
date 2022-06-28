package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import me.lovethefeel.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserFindMain {

    private static final Logger logger = LoggerFactory.getLogger(UserFindMain.class);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        // transaction 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            User user = em.find(User.class, "test@gmail.com");
            if (Objects.isNull(user)) {
                System.out.println("사용자가 존재하지 않음");
                logger.debug("사용자가 존재하지 않음");
            } else {
                System.out.println("사용자 존재함 : " + user);
                logger.debug("사용자 존재함 {}", user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
