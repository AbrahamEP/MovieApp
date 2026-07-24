package sync;

import com.mycompany.movieapp.model.Movie;
import database.WatchlistRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Coordina la sincronizacion entre la fuente remota y la base de datos local.
 */
public class SyncService {

    private final RemoteMovieSource remoteMovieSource;
    private final WatchlistRepository watchlistRepo;

    public SyncService(RemoteMovieSource remoteMovieSource, WatchlistRepository watchlistRepo) {
        this.remoteMovieSource = remoteMovieSource;
        this.watchlistRepo = watchlistRepo;
    }

    public SyncResult synchronizeUpcomingMovies() {
        int downloaded = 0;
        int inserted = 0;
        int updated = 0;
        int skipped = 0;
        int failed = 0;

        /*
         * TODO:
         * Obtener las peliculas remotas utilizando RemoteMovieSource.
         *
         * TODO:
         * Obtener las peliculas locales utilizando WatchlistRepository.
         *
         * TODO:
         * Convertir las peliculas locales a un Map<Integer, Movie> usando mapById.
         *
         * TODO:
         * Recorrer las peliculas remotas y actualizar los contadores primitivos:
         * downloaded, inserted, updated, skipped y failed.
         *
         * TODO:
         * Construir un SyncResult de exito o error segun el resultado final.
         */

        return SyncResult.failure(
                downloaded,
                inserted,
                updated,
                skipped,
                "Pendiente de implementar. Errores: " + failed
        );
    }

    private SyncResult synchronizeMovies(ArrayList<Movie> remoteMovies, Map<Integer, Movie> localMoviesById) {
        int downloaded = 0;
        int inserted = 0;
        int updated = 0;
        int skipped = 0;
        int failed = 0;

        /*
         * TODO:
         * Este metodo debe coordinar el recorrido de todas las peliculas remotas.
         *
         * Algoritmo esperado:
         * - Calcular downloaded con base en la cantidad de peliculas remotas.
         * - Recorrer cada Movie remota.
         * - Usar synchronizeMovie para decidir que hacer con cada pelicula.
         * - Incrementar inserted, updated, skipped o failed segun corresponda.
         *
         * Pista:
         * Los contadores deben ser variables primitivas locales. No crear una
         * clase auxiliar para guardar estas estadisticas.
         */

        return SyncResult.failure(
                downloaded,
                inserted,
                updated,
                skipped,
                "Pendiente de implementar. Errores: " + failed
        );
    }

    private void synchronizeMovie(Movie remoteMovie, Map<Integer, Movie> localMoviesById) {
        /*
         * TODO:
         * Sincronizar una sola pelicula remota contra el mapa de peliculas locales.
         *
         * Algoritmo esperado:
         * - Buscar la pelicula local por id dentro de localMoviesById.
         * - Si no existe localmente, intentar insertarla usando countInsert.
         * - Si existe localmente, comparar ambas peliculas usando shouldUpdate.
         * - Si debe actualizarse, intentar actualizarla usando countUpdate.
         * - Si no hay cambios, la pelicula debe considerarse omitida.
         *
         * Pista:
         * Este metodo debe ayudar a decidir el caso de una pelicula, pero los
         * contadores finales se deben manejar con variables primitivas en el flujo
         * principal de sincronizacion.
         */
    }

    private boolean countInsert(Movie remoteMovie) {
        /*
         * TODO:
         * Intentar guardar la pelicula remota usando WatchlistRepository.
         *
         * Pista:
         * Este metodo puede devolver true cuando la insercion fue exitosa y false
         * cuando fallo. El contador inserted o failed debe incrementarse fuera de
         * este metodo.
         */
        return false;
    }

    private boolean countUpdate(Movie remoteMovie) {
        /*
         * TODO:
         * Intentar actualizar la pelicula local usando WatchlistRepository.
         *
         * Pista:
         * Este metodo puede devolver true cuando la actualizacion fue exitosa y
         * false cuando fallo. El contador updated o failed debe incrementarse fuera
         * de este metodo.
         */
        return false;
    }

    private boolean shouldUpdate(Movie localMovie, Movie remoteMovie) {
        /*
         * TODO:
         * Comparar la pelicula remota con la pelicula local para decidir si debe
         * actualizarse o puede omitirse.
         *
         * Algoritmo esperado:
         * - Revisar los campos importantes del modelo Movie.
         * - Si al menos un campo cambio, devolver true.
         * - Si todos los campos relevantes son iguales, devolver false.
         *
         * Pista:
         * No compares objetos completos directamente. Compara sus propiedades.
         */
        return false;
    }

    private Map<Integer, Movie> mapById(ArrayList<Movie> movies) {
        Map<Integer, Movie> moviesById = new HashMap<>();

        /*
         * TODO:
         * Recorrer la lista de peliculas y guardar cada Movie en el mapa usando
         * su id como llave.
         *
         * Pista:
         * Este mapa permite buscar peliculas locales rapidamente durante la
         * sincronizacion.
         */

        return moviesById;
    }
}
