/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syncro;

import com.mycompany.movieapp.model.Movie;
import database.WatchlistRepository;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author abrahamescamillapinelo
 */
public class SyncWatchlistService {
    private final RemoteMovieSource remoteMovieSource;
    private final WatchlistRepository watchlistRepo;
    
    public SyncWatchlistService(RemoteMovieSource remoteMovieSource, WatchlistRepository watchlistRepo) {
        this.remoteMovieSource = remoteMovieSource;
        this.watchlistRepo = watchlistRepo;
    }
    
    public SyncResult synchronizedUpcomingMovies() {
        return null;
    }
    
    private SyncStats synchronizeMovies(ArrayList<Movie> remoteMovies, Map<Integer, Movie> localMovies) {
        SyncStats stats = new SyncStats(remoteMovies.size());
        
       
        
        return null;
    }
    
    private void synchronizeMovie(Movie remoteMovie, Map<Integer, Movie> localMoviesbyId, SyncStats stats) {
        
    }
    
    private boolean shouldUpdate(Movie localMovie, Movie remoteMovie) {
        return false;
    }
}
