package com.lus.dawm.atelier1.model;

import java.io.Serializable;

public class Categorie implements Serializable {

    private Long id;
    private String designation;
    private String description;
    private Produit produit;
    private Categorie categorie;

    public Categorie(Long id, String designation, String description, Produit produit, Categorie categorie) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.produit = produit;
        this.categorie = categorie;
    }

    public Categorie() {
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

    public Produit getProduit() {
        return produit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

}
