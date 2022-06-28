package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainIdentity {
    private static Logger logger = LoggerFactory.getLogger(MainIdentity.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Review review = new Review("H-01", 5, "작성자", "댓글");
            logger.info("persist 실행 전");
            em.persist(review);
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", review.getId());
            logger.info("커밋하기 전");
            tx.commit();
            logger.info("커밋함");
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
        }
        CommonEntityMangerFactory.close();
    }
}
