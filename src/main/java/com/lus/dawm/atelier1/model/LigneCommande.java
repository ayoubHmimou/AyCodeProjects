package com.lus.dawm.atelier1.model;

import java.io.Serializable;

public class LigneCommande implements Serializable {

    private Double prix;
    private int qte;
    private Commande commande;
    private Panier panier;
    private Produit produit;

    public LigneCommande(Double prix, int qte, Commande commande, Panier panier, Produit produit) {
        this.prix = prix;
        this.qte = qte;
        this.commande = commande;
        this.panier = panier;
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

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
