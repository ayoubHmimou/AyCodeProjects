package com.lus.dawm.atelier1.main;

import com.lus.dawm.atelier1.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("atelier");
        EntityManager em= emf.createEntityManager();
        Produit produit= new Produit();
        produit.setDesignation("ordinateur");
        produit.setPrix(1000D);
        produit.setDescription("description ordinateur");
        em.getTransaction().begin();
        em.persist(produit);
        em.getTransaction().commit();
        em.close();
        emf.close();




    }
}
