package com.lus.dawm.atelier1.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Commande implements Serializable {

    private Long id;
    private LocalDateTime dateCreation;
    private Date dateLivraison;
    private String adresseLivraison;

    public Commande(Long id, LocalDateTime dateCreation, Date dateLivraison, String adresseLivraison) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateLivraison = dateLivraison;
        this.adresseLivraison = adresseLivraison;
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

    public void setAdresseLivraison(Client client ) {
        this.adresseLivraison = client.getAdresse();
    }

}
