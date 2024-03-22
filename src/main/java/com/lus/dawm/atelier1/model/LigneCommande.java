package com.lus.dawm.atelier1.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class LigneCommande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double prix;
    private int qte;
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Produit produit;

    public LigneCommande(Double prix, int qte, Commande commande, Produit produit) {
        this.prix = prix;
        this.qte = qte;
        this.commande = commande;
        this.produit = produit;
    }

    public LigneCommande() {
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
