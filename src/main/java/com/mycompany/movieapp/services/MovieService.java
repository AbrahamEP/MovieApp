/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieapp.services;

/**
 *
 * @author abrahamescamillapinelo
 */
import com.mycompany.movieapp.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import network.TMDBClient;

public class MovieService {
    
    TMDBClient client = new TMDBClient();
    
    private ArrayList<Movie> parseMovies(String jsonResponse) {
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
    
    public ArrayList<Movie> getTopRatedMovies() throws Exception {
        String endpoint = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        String jsonResponse = client.sendGetRequest(endpoint);
        return parseMovies(jsonResponse);
    }

}
