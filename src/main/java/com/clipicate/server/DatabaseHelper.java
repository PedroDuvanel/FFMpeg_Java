package com.clipicate.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/gif_database";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "123456";

    public DatabaseHelper() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS gifs ("
                + "id SERIAL PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "path TEXT NOT NULL, "
                + "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt= conn.prepareStatement(createTableSQL)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGif(String name, String path) {
        String insertSQL = "INSERT INTO gifs(name, path) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, name);
            stmt.setString(2, path);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listGifs() {
        String selectSQL = "SELECT * FROM gifs";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(selectSQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("name"));
                System.out.println("Caminho: " + rs.getString("path"));
                System.out.println("Data de Criação: " + rs.getTimestamp("creation_date"));
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGifs() {
      String deleteSQL = "DELETE FROM gifs";
      try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
       PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
           int rowsAffected = stmt.executeUpdate();
           System.out.println(rowsAffected + " gif deletado com sucesso!.");
       }
       catch (SQLException e) {
        e.printStackTrace();
       } 
    }

    

}
