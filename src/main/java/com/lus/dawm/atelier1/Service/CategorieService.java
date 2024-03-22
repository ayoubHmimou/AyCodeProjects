package com.lus.dawm.atelier1.Service;

import com.lus.dawm.atelier1.dataBase.MySqlDB;
import com.lus.dawm.atelier1.model.Categorie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {

    Connection connection;

    public CategorieService() throws SQLException, ClassNotFoundException{
        connection = MySqlDB.getInstance();
    }

    public List<Categorie> getAllCategories() throws SQLException{
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM categorie";
        try
                (
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        ){
            while(rs.next()){
                Categorie categorie = new Categorie();
                Categorie categorieParent = new Categorie();
                categorie.setId(rs.getLong("id"));
                categorie.setDesignation(rs.getString("designation"));
                categorie.setDescription(rs.getString("description"));
                categorie.setParentCategorie(categorieParent);

                categories.add(categorie);
            }
        }
        return categories;
    }
}
