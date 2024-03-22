package com.lus.dawm.atelier1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends Utilisateur implements Serializable{

    private Date dateNaissance;
    private String adresse;
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();

    public Client(Date dateNaissance, String adresse, List<Commande> commandes) {
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.commandes = commandes;
    }

    public Client(){

    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
