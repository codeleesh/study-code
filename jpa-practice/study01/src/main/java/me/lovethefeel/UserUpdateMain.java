package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import me.lovethefeel.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserUpdateMain {

    private static final Logger logger = LoggerFactory.getLogger(UserUpdateMain.class);

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
            } else {
                System.out.println("사용자 존재함 : " + user);
                user.changeName("test2");
                System.out.println("사용자 정보 수정됨 : " + user);
                logger.info("User.changeName 호출함");
            }

            tx.commit();
            logger.info("EntityTransaction.commit 호출함");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
