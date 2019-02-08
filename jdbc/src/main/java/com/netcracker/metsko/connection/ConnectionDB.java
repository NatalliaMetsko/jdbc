package com.netcracker.metsko.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/net.cracker.metsko";
    private static final String USERNAME ="root";
    private static final String PASSWORD="lkjhgfdsaASDFGHJKL258";
    private static final String MYSQL_DRIVER="com.mysql.jdbc.Driver";

    private ConnectionDB()
    {

    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(MYSQL_DRIVER);
            connection= DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
        }
       catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static class Holder
    {
        private final static ConnectionDB INSTANCE = new ConnectionDB();
    }

    public static ConnectionDB getInstance()
    {
        return  Holder.INSTANCE;
    }
}
