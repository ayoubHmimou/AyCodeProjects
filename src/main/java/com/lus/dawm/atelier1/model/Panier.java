package com.lus.dawm.atelier1.model;

import java.io.Serializable;
import java.util.Scanner;

public class Panier  implements Serializable  {

    private LigneCommande ligneCommande;

    public Panier(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    Scanner scanner = new Scanner(System.in);
    public void voirPanier(Client client){
        System.out.println("Please enter your password in order to see your Cart");
        String password = scanner.nextLine();
        if(password.equals(client.getPassword())){
            System.out.println("*Panier visible*");
        }else{
            System.out.println("Your password is incorrect");
        }

    }
}
