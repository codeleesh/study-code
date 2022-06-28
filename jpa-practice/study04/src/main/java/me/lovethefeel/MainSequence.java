package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityManagerEntityByOracle;
import me.lovethefeel.domain.ActivityLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainSequence {

    private static Logger logger = LoggerFactory.getLogger(MainSequence.class);

    public static void main(String[] args) {
        CommonEntityManagerEntityByOracle.init();
        EntityManager em = CommonEntityManagerEntityByOracle.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ActivityLog log = new ActivityLog("U01", "VISIT");
            logger.info("persist 실행 전");
            em.persist(log);
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", log.getId());
            logger.info("커밋하기 전");
            tx.commit();
            logger.info("커밋함");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        CommonEntityManagerEntityByOracle.close();
    }
}
