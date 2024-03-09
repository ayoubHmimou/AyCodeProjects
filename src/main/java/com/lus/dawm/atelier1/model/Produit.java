package com.lus.dawm.atelier1.model;

import java.io.Serializable;

public class Produit implements Serializable {

    private Long id;
    private String designation;
    private String description;
    private int qte;
    private Categorie categorie;
    private LigneCommande ligneCommande;

    public Produit(Long id, String designation, String description, int qte, Categorie categorie, LigneCommande ligneCommande) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.qte = qte;
        this.categorie = categorie;
        this.ligneCommande = ligneCommande;

    }

    public Produit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }
}
