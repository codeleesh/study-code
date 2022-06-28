package me.lovethefeel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CommonEntityMangerFactory {

    private static EntityManagerFactory emf;

    public static void init() {
        emf = Persistence.createEntityManagerFactory("mysql");
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }

    private CommonEntityMangerFactory() {
    }
}
