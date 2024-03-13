package com.lus.dawm.atelier1.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDB {

    public static Connection connection;

    private MySqlDB()  {

    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if(connection == null){
            String url = "jdbc:mysql://localhost:3306/eshop";
            String username = "root";
            String password = "ayoub";

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            new MySqlDB();
        }

        return connection;
    }



}
