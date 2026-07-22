package sync;

import com.mycompany.movieapp.model.Movie;
import com.mycompany.movieapp.services.MovieService;
import java.util.Collections;
import java.util.List;

/**
 * Represents the remote movie catalog used by the synchronization module.
 */
public class RemoteMovieSource {

    private final MovieService movieService;

    public RemoteMovieSource(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getUpcomingMovies() {
        /*
         * EJERCICIO
         *
         * Descarga la lista de peliculas proximas desde TMDB reutilizando
         * MovieService.
         *
         * Practica:
         * - Delegar trabajo a una clase existente
         * - Evitar duplicar codigo de red
         * - Entender que esta clase representa la fuente remota de datos
         *
         * Pistas:
         * - Revisa que metodo de MovieService ya obtiene peliculas proximas.
         * - Decide si este metodo debe declarar Exception o manejarla aqui.
         * - No modifiques TMDBClient para completar este ejercicio.
         */
        return Collections.emptyList();
    }
}
