package com.lus.dawm.atelier1.Service;

import com.lus.dawm.atelier1.dataBase.MySqlDB;
import com.lus.dawm.atelier1.model.Utilisateur;

import java.sql.*;

public class UserService {

    Connection connection;
    public UserService() throws SQLException, ClassNotFoundException {
        connection = MySqlDB.getInstance();
    }
    public Utilisateur findUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        Utilisateur user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try{
            connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setRole(rs.getString("role"));
            }
        }catch(SQLSyntaxErrorException | NumberFormatException e){
            System.err.println(e.getMessage());
        }

        return user;
    }
}
