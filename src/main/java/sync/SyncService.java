package sync;

import com.mycompany.movieapp.model.Movie;
import database.WatchlistRepository;
import java.util.List;

/**
 * Coordinates synchronization between a remote movie source and local storage.
 */
public class SyncService {

    private final RemoteMovieSource remoteSource;
    private final WatchlistRepository repository;

    public SyncService(RemoteMovieSource remoteSource, WatchlistRepository repository) {
        this.remoteSource = remoteSource;
        this.repository = repository;
    }

    public SyncResult synchronizeUpcomingMovies() {
        /*
         * EJERCICIO
         *
         * Implementa el algoritmo de sincronizacion.
         *
         * Pasos sugeridos:
         *
         * 1. Descarga las peliculas remotas.
         * 2. Consulta las peliculas almacenadas localmente.
         * 3. Compara ambas listas utilizando el ID.
         * 4. Inserta las peliculas que no existan.
         * 5. Actualiza las peliculas cuyos datos hayan cambiado.
         * 6. Cuenta cuantos registros fueron insertados, actualizados y omitidos.
         * 7. Devuelve un objeto SyncResult con el resumen.
         *
         * Preguntas para reflexionar:
         *
         * - Que hace que una pelicula sea "la misma"?
         * - Que datos deberian actualizarse?
         * - Que ocurre si TMDB elimina una pelicula?
         *
         * Importante:
         * SyncService no debe hablar directamente con TMDB ni con SQLite. Su
         * responsabilidad es coordinar RemoteMovieSource y WatchlistRepository.
         */
        List<Movie> remoteMovies = remoteSource.getUpcomingMovies();
        List<Movie> localMovies = repository.findAll();

        return new SyncResult(
                remoteMovies.size(),
                0,
                0,
                localMovies.size(),
                false,
                SyncConstants.DEFAULT_SYNC_MESSAGE
        );
    }

    private void synchronizeMovie(Movie remoteMovie) {
        /*
         * EJERCICIO
         *
         * Sincroniza una sola pelicula remota contra la base local.
         *
         * Practica:
         * - Buscar una pelicula local por ID
         * - Decidir entre insertar, actualizar u omitir
         * - Mantener pequeno cada paso del algoritmo
         *
         * Pistas:
         * - Usa repository.findById(remoteMovie.getId()).
         * - Si no existe localmente, usa repository.save(remoteMovie).
         * - Si existe, llama a shouldUpdate(localMovie, remoteMovie).
         */
    }

    private boolean shouldUpdate(Movie localMovie, Movie remoteMovie) {
        /*
         * EJERCICIO
         *
         * Decide si los datos locales deben actualizarse con la version remota.
         *
         * Practica:
         * - Comparar objetos de dominio
         * - Elegir campos relevantes para sincronizacion
         * - Evitar escrituras innecesarias en SQLite
         *
         * Preguntas:
         * - Cambios en rating justifican una actualizacion?
         * - Que pasa si overview cambia pero title no?
         * - Como compararias valores null de forma segura?
         */
        return false;
    }
}
