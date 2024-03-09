package com.lus.dawm.atelier1.resources;

import com.lus.dawm.atelier1.model.Produit;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {

    public static List<Produit> produits = new ArrayList<>();
    public static List<Produit> getProduits(HttpServletRequest request) {
        List<Produit> produits = (List<Produit>) request.getSession().getAttribute("produits");
        if(produits == null){
            produits = new ArrayList<>();
            request.getSession().setAttribute("produits", produits);
        }
        return produits;

    }
    public static void addProduits(HttpServletRequest request, Produit produit){
        getProduits(request).add(produit);
    }


}
