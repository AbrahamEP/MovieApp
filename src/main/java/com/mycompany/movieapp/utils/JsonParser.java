/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieapp.utils;

/**
 *
 * @author abrahamescamillapinelo
 */
import java.util.ArrayList;
import java.util.List;
import com.mycompany.movieapp.model.Movie;
import com.mycompany.movieapp.model.TvShow;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    
    public ArrayList<TvShow> parseTvShows(String jsonResponse) {
        ArrayList<TvShow> parsedTvShows = new ArrayList<>();
        
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);

            int id = movieJson.getInt("id");
            String name = movieJson.getString("name");
            double rating = movieJson.getDouble("vote_average");
            String overview = movieJson.getString("overview");

            TvShow show = new TvShow(id, name, overview, rating);
            parsedTvShows.add(show);
        }
        
        return parsedTvShows;
    }
    
    public ArrayList<Movie> parseMovies(String jsonResponse) {
        
        ArrayList<Movie> parsedMovies = new ArrayList<>();
        
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);

            int id = movieJson.getInt("id");
            String title = movieJson.getString("title");
            double rating = movieJson.getDouble("vote_average");
            String releaseDate = movieJson.getString("release_date");
            String language = movieJson.getString("original_language");
            String overview = movieJson.getString("overview");

            Movie movie = new Movie(id, title, rating, releaseDate, language, overview);
            parsedMovies.add(movie);
        }
        
        return parsedMovies;
    }
}
