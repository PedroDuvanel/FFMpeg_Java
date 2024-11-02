package com.clipicate.server.repository;
import com.clipicate.server.Gif;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GifRepository {
    
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/gif_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS gifs (" +
                "id SERIAL PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "path TEXT NOT NULL, " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGif(Gif gif) {
        String insertSQL = "INSERT INTO gifs(name, path) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, gif.getName());
            stmt.setString(2, gif.getPath());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Gif> listGifs() {
        List<Gif> gifs = new ArrayList<>();
        String selectSQL = "SELECT * FROM gifs";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(selectSQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                gifs.add(new Gif(rs.getInt("id"), rs.getString("name"),
                        rs.getString("path"), rs.getTimestamp("creation_date")));
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gifs;
    }

    public void deleteGifs() {
        String deleteSQL = "DELETE FROM gifs";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " gif(s) deletado(s) com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}