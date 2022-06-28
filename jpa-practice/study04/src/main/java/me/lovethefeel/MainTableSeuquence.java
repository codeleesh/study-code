package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.AccessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class MainTableSeuquence {

    private static Logger logger = LoggerFactory.getLogger(MainTableSeuquence.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            AccessLog log = new AccessLog("/path01", LocalDateTime.now());
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
        CommonEntityMangerFactory.close();
    }
}
