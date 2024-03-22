package com.lus.dawm.atelier1.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateCreation;
    private Date dateLivraison;
    private String adresseLivraison;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();



    public Commande(LocalDateTime dateCreation, Date dateLivraison, String adresseLivraison, Client client) {
        this.dateCreation = dateCreation;
        this.dateLivraison = dateLivraison;
        this.adresseLivraison = adresseLivraison;
        this.client = client;
    }

    public Commande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
