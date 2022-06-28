package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import me.lovethefeel.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UserSaveMain {

    private static final Logger logger = LoggerFactory.getLogger(UserSaveMain.class);

    public static void main(String[] args) {

        // persistence.xml 파일에 정의한 영속 단위 기준으로 초기화
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();
        // EntityTransaction 구함
        EntityTransaction tx = em.getTransaction();
        // Transaction 시작
        tx.begin();

        try {

            User user = new User("test@gmail.com", "test", LocalDateTime.now());
            em.persist(user);
            logger.info("EntityManager.persist 호출함");
            // Transaction 커밋
            tx.commit();
            logger.info("EntityManager.commit 호출함");

//            02:00:23.388 [main] INFO  study1.UserSaveMain - EntityManager.persist 호출함
//            02:00:23.401 [main] DEBUG org.hibernate.SQL -
//                    /* insert study1.domain.User
//                     */ insert
//            into
//            t_user (create_date, name, email)
//            values
//                    (?, ?, ?)
//            02:00:23.418 [main] INFO  study1.UserSaveMain - EntityManager.commit 호출함
        } catch (Exception e) {
            e.printStackTrace();
            // Transaction 롤백
            tx.rollback();
        } finally {
            // EntityManger 닫음
            em.close();
        }

        // 팩토리 닫음, 사용한 자원 반환
        emf.close();
    }
}
