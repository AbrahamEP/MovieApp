/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syncro;

import com.mycompany.movieapp.model.Movie;
import com.mycompany.movieapp.services.MovieService;
import java.util.ArrayList;

/**
 *
 * @author abrahamescamillapinelo
 */
public class RemoteMovieSource {
    private final MovieService movieService;
    
    public RemoteMovieSource(MovieService movieService) {
        this.movieService = movieService;
    }
    
    public ArrayList<Movie> getUpcomingMovies() throws Exception {
        return movieService.getUpcomingMovies();
    }
}
