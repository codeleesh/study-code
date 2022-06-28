package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.Grade;
import me.lovethefeel.domain.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainDirect {

    private static Logger logger = LoggerFactory.getLogger(MainDirect.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Hotel hotel = new Hotel("H-01", "하얏트", 2022, Grade.S2);
            logger.info("persist 실행 전");
            em.persist(hotel);
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", hotel.getId());
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
