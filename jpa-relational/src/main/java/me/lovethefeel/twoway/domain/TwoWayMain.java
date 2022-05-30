package me.lovethefeel.twoway.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TwoWayMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userteam");
        EntityManager em = emf.createEntityManager();

        // transaction 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Line line = new Line();
            line.setName("2호선");
            em.persist(line);

            Subway subway = new Subway();
            subway.setName("잠실");
            subway.setLine(line);
            em.persist(subway);

            Subway subway2 = new Subway();
            subway2.setName("강변");
            subway2.setLine(line);
            em.persist(subway2);

            em.flush();
            em.clear();

            Subway findSubway = em.find(Subway.class, subway.getId());
            List<Subway> subways = findSubway.getLine().getSubways();
            subways.stream().forEach(System.out::println);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
