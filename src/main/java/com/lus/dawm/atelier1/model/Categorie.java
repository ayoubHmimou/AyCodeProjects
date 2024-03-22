package com.lus.dawm.atelier1.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designation;
    private String description;
    @ManyToMany(mappedBy = "categories")
    @JoinTable
            (
                    name = "categorie_produit",
                    joinColumns = @JoinColumn(name = "categorie_id"),
                    inverseJoinColumns = @JoinColumn(name = "produit_id")
            )
    private List<Produit> produits = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "parent_categorie_id")
    Categorie parentCategorie;
    @OneToMany(mappedBy = "parentCategorie")
    private List<Categorie> subCategories = new ArrayList<>();

    public Categorie(Long id, String designation, String description, Categorie parentCategorie) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.parentCategorie = parentCategorie;
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

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Categorie getParentCategorie() {
        return parentCategorie;
    }

    public void setParentCategorie(Categorie parentCategorie) {
        this.parentCategorie = parentCategorie;
    }

    public List<Categorie> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Categorie> subCategories) {
        this.subCategories = subCategories;
    }
}
