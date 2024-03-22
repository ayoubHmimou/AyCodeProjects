package com.lus.dawm.atelier1.Service;

import com.lus.dawm.atelier1.dataBase.MySqlDB;
import com.lus.dawm.atelier1.model.Produit;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    Connection connection;

    public ProduitService() throws SQLException, ClassNotFoundException {
        connection = MySqlDB.getInstance();
    }

    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try(
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);){


        while (rs.next()){
            Produit produit = new Produit();

            produit.setId(rs.getLong("id"));
            produit.setDescription(rs.getString("description"));
            produit.setDesignation(rs.getString("designation"));
            produit.setPrix(rs.getDouble("prix"));
            produit.setQte(rs.getInt("qte"));
            produit.setCategories(null);
            produit.setLigneCommandes(null);

            produits.add(produit);
            }
        }
        return produits;
    }

    public List<Produit> getProduitsByCategorieId(int categorieId) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE id_categorie = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categorieId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Produit produit = new Produit();

                produit.setId(rs.getLong("id"));
                produit.setDescription(rs.getString("description"));
                produit.setDesignation(rs.getString("designation"));
                produit.setPrix(rs.getDouble("prix"));
                produit.setQte(rs.getInt("qte"));
                produit.setCategories(produit.getCategories());
                produit.setLigneCommandes(produit.getLigneCommandes());

                produits.add(produit);

            }

        return produits;

    }

}
