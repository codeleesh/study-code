package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.hotel.Grade;
import me.lovethefeel.domain.hotel.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MainNullEmbedded {

    private static Logger logger = LoggerFactory.getLogger(MainNullEmbedded.class);

    public static void main(String[] args) {
        CommonEntityMangerFactory.init();
        saveHotel();
        printHotel();
        CommonEntityMangerFactory.close();
    }

    private static void saveHotel() {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(
                    new Hotel("H009", "HN", 2022, Grade.S7, null)
            );
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    private static void printHotel() {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Hotel hotel = em.find(Hotel.class, "H009");
            if (Objects.nonNull(hotel)) {
                logger.info("주소: {}", hotel.getAddress());
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
