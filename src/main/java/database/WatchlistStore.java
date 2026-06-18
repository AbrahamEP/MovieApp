/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.movieapp.model.Movie;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author abrahamescamillapinelo
 */
public class WatchlistStore {
    
    private static final String DATABASE_URL = "jdbc:sqlite:movieapp.db";
            
    private ArrayList<Movie> watchlist;
    
    public WatchlistStore() {
        watchlist = new ArrayList<>();
        createTableIfNeeded();
    }
    
    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(DATABASE_URL);
    }
    
    private void createTableIfNeeded() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS watchlist (
                        id INTEGER PRIMARY KEY,
                        title TEXT NOT NULL,
                        rating REAL,
                        release_date TEXT,
                        language TEXT,
                        overview TEXT
                     );
                     """;
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch(Exception e) {
            System.out.println("Error crear tabla: " + e.getMessage());
        }
    }
    
    //Metodos para BD
    public boolean addMovieToWatchlist(Movie movie) {
        
        if(movie == null) {
            return false;
        }
        
        if(isMovieInWatchlist(movie.getId())) {
            return false;
        }
        
        String sql = """
                     INSERT INTO watchlist (
                        id,
                        title,
                        rating,
                        release_date,
                        language,
                        overview
                     )
                     VALUES (?,?,?,?,?,?);
                     """;
        
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, movie.getId());
            statement.setString(2, movie.getTitle());
            statement.setDouble(3, movie.getRating());
            statement.setString(4, movie.getReleaseDate());
            statement.setString(5, movie.getLanguage());
            statement.setString(6, movie.getOverview());
            
            statement.executeUpdate();
            
            return true;
            
        } catch( Exception e) {
            System.out.println("Error al guardar pelicula: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isMovieInWatchlist(int id) {
        
        for(Movie movie: watchlist) {
            if(movie.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Movie> getWatchlist() {
        
        String sql = """
                        SELECT * FROM watchlist;
                     """;
        
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                Movie movie = new Movie(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getDouble("rating"),
                        result.getString("release_date"),
                        result.getString("language"),
                        result.getString("overview")
                );
                watchlist.add(movie);
            }
            
        }catch (Exception e) {
            System.out.println("Error al obtener peliculas de DB: " + e.getMessage());
        }
        
        return watchlist;
    }
    
    public boolean removeFromWatchlist(int movieId) {
        
        for(int i = 0; i < watchlist.size(); i++) {
            Movie currentMovie = watchlist.get(i);
            
            if(currentMovie.getId() == movieId) {
                watchlist.remove(i);
                return true;
            }
        }
        return false;
    }
}
