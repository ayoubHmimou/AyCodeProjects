package com.lus.dawm.atelier1.model;

import java.io.Serializable;
import java.util.Date;

public class Client extends Utilisateur {

    private Date dateNaissance;
    private String adresse;


    public Client(Date dateNaissance, String adresse) {
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
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


}
