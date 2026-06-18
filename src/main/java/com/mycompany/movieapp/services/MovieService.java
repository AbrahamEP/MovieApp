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
import com.mycompany.movieapp.model.TvShow;
import com.mycompany.movieapp.utils.JsonParser;
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
    JsonParser parser = new JsonParser();
    ArrayList<Movie> movies;
    ArrayList<TvShow> shows;
    ArrayList<Movie> watchlist = new ArrayList();
    
    //Métodos para obtener informacion de MovieAPI
    public ArrayList<Movie> getTopRatedMovies() throws Exception {
        String endpoint = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        String jsonResponse = client.sendGetRequest(endpoint);
        movies = parser.parseMovies(jsonResponse);
        return movies;
    }
    
    public ArrayList<Movie> getUpcomingMovies() throws Exception {
        //TODO: Obtener las peliculas por venir
        String endpoint = "https://api.themoviedb.org/3/movie/upcoming";
        String jsonResponse = client.sendGetRequest(endpoint);
        movies = parser.parseMovies(jsonResponse);
        return movies;
    }
    
    public ArrayList<TvShow> getTopRatedTVShows() throws Exception {
        String endpoint = "https://api.themoviedb.org/3/trending/tv/day?language=en-US"; 
        String jsonReponse = client.sendGetRequest(endpoint);
        shows = parser.parseTvShows(jsonReponse);
        System.out.println("TVSHows: " + jsonReponse);
        return shows;
    }
    
    //Método de ayuda para obtener pelicula por ID
    public Movie getMovieById(int id) {
        
        for(int i = 0; i < movies.size(); i++) {
            //Buscar pelicula por ID
            Movie currentMovie = movies.get(i);
            if(currentMovie.getId() == id) {
                return currentMovie;
            }
        }
        return null;
    }
    
}
