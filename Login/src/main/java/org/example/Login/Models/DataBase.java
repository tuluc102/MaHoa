package org.example.Login.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class
DataBase {
    private static DataBase instance;
    private Connection connection;

    private DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanli" , "root", "23102005");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

