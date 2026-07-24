package sync;

import com.mycompany.movieapp.model.Movie;
import com.mycompany.movieapp.services.MovieService;
import java.util.ArrayList;

/**
 * Fuente remota de peliculas para el modulo de sincronizacion.
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
