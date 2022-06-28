package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.Grade;
import me.lovethefeel.domain.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class JpaMain {

    private static final Logger logger = LoggerFactory.getLogger(JpaMain.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();

        final Hotel hotel = new Hotel("H-01", "말", 2022, Grade.S5);
        save(hotel);

        final Hotel findHotel = findById("H-01");
        if (Objects.isNull(findHotel)) {
            logger.info("없음");
        } else {
            logger.info("있음: {}", findHotel);
        }
        CommonEntityMangerFactory.close();
    }

    private static void save(final Hotel entity) {

        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(entity);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    private static Hotel findById(final String id) {

        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Hotel hotel = em.find(Hotel.class, id);
            tx.commit();
            return hotel;
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
