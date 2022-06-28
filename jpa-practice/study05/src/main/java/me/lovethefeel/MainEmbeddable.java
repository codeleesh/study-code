package me.lovethefeel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import me.lovethefeel.config.CommonEntityMangerFactory;
import me.lovethefeel.domain.common.Address;
import me.lovethefeel.domain.hotel.Grade;
import me.lovethefeel.domain.hotel.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MainEmbeddable {

    private static Logger logger = LoggerFactory.getLogger(MainEmbeddable.class);

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
            Address address = new Address("주소1", "주소2", "12345");
            Hotel hotel = new Hotel("H00", "HN", 2022, Grade.S7, address);
            em.persist(hotel);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void printHotel() {
        EntityManager em = CommonEntityMangerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Hotel hotel = em.find(Hotel.class, "H00");
            if (Objects.nonNull(hotel)) {
                logger.info("주소: {}", hotel.getAddress());
            }
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
