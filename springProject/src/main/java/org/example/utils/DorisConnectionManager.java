package org.example.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DorisConnectionManager {
    private static final String URL = "jdbc:mysql://10.91.125.43:9030/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "s#ab4s2e";

    public static Connection getConnection(String dataBase) throws SQLException {
        return DriverManager.getConnection(URL+dataBase, USERNAME, PASSWORD);
    }
}
