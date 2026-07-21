/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieapp.ui;

/**
 *
 * @author abrahamescamillapinelo
 */
import com.mycompany.movieapp.model.BackgroundTaskInterface;
import com.mycompany.movieapp.model.Movie;
import com.mycompany.movieapp.model.TvShow;
import com.mycompany.movieapp.services.MovieService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import org.json.JSONArray;
import com.mycompany.movieapp.ui.MovieDetailsDialog;
import com.mycompany.movieapp.model.LoadedContentType;
import database.DatabaseManager;
import database.WatchlistRepository;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainFrame extends JFrame {

    // =========================
    // COMPONENTS
    // =========================
    private JTextField txtSearch;
    private JButton btnSearch;

    private JTable tblMovies;
    private DefaultTableModel tableModel;

    private JButton btnViewDetails;
    private JButton deleteFromWatchlistButton;
    
    private JComboBox comboBox;
    
    private JLabel statusLabel;
    private JProgressBar progressBar;

    // =========================
    // STATE
    // =========================
    private ArrayList<Movie> movies;
    private ArrayList<TvShow> shows;
    MovieService movieService;
    private LoadedContentType contentType;
    private WatchlistRepository repository;

    // =========================
    // CONSTRUCTOR
    // =========================
    public MainFrame() {

        movies = new ArrayList<>();
        DatabaseManager databaseManager = new DatabaseManager();
        repository = new WatchlistRepository(databaseManager);
        repository.createTableIfNeeded();
        movieService = new MovieService();
        
        initializeFrame();
        initializeComponents();
        configureTable();
        addComponents();
        registerEvents();
        loadFirstTime();
        
    }

    // =========================
    // INITIALIZATION
    // =========================
    private void initializeFrame() {

        setTitle("MovieApp");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        txtSearch = new JTextField(25);

        btnSearch = new JButton("Search");

        btnViewDetails = new JButton("View Details");
        
        deleteFromWatchlistButton = new JButton("Delete From Watchlist");
        
        statusLabel = new JLabel("Ready");
        
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);

        initComboBox();
        
        tableModel = new DefaultTableModel();
        tblMovies = new JTable(tableModel);

        tblMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMovies.setRowHeight(28);
        tblMovies.getTableHeader().setReorderingAllowed(false);
    }

    private void configureTable() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Release Date");
        tableModel.addColumn("Language");
    }

    private void addComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // =========================================
        // TOP PANEL
        // =========================================
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonsPanel.add(comboBox);
        buttonsPanel.add(deleteFromWatchlistButton);

        topPanel.add(searchPanel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(buttonsPanel);
        
        //Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);
        statusPanel.add(progressBar);
        
        topPanel.add(statusPanel);

        // =========================================
        // CENTER PANEL
        // =========================================
        JScrollPane scrollPane = new JScrollPane(tblMovies);

        // =========================================
        // BOTTOM PANEL
        // =========================================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.add(btnViewDetails);

        // =========================================
        // ADD TO MAIN PANEL
        // =========================================
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    
    private void initComboBox() {
        LoadedContentType[] items = LoadedContentType.values();
        comboBox = new JComboBox(items);
    }
    
    private void loadFirstTime() {
        loadTopRatedMovies();
    }

    // =========================
    // EVENTS
    // =========================
    private void registerEvents() {

        btnSearch.addActionListener(e -> searchMovies());

        btnViewDetails.addActionListener(e -> openMovieDetails());
        
        comboBox.addActionListener(e -> loadButtonAction());
        
        deleteFromWatchlistButton.addActionListener(e -> removeWatchlistButtonAction());
    }

    // =========================
    // ACTION METHODS
    // =========================
    
    private void setLoadingState(boolean isLoading, String message) {
        statusLabel.setText(message);
        progressBar.setVisible(isLoading);
        
        comboBox.setEnabled(!isLoading);
        btnViewDetails.setEnabled(!isLoading);
        deleteFromWatchlistButton.setEnabled(!isLoading);
    }
    
    /**
     * TODO: Call movie service and load top rated movies from API
     */
    private void loadTopRatedMovies() {
        executeBackgroundTask(
                movieService::getTopRatedMovies,
                loadedMovies -> this.movies = loadedMovies,
                LoadedContentType.TOP_MOVIES,
                "Loading Top Movies...",
                "Movies loaded"
        );
    }

    /**
     * TODO: Call movie service and load top rated TV shows from API
     */
    private void loadTopRatedTVShows() {
       executeBackgroundTask(
               movieService::getTopRatedTVShows,
               loadedShows -> this.shows = loadedShows,
               LoadedContentType.TV_SHOWS,
               "Loading TV Shows",
               "Shows Loaded"
       );
    }

    /**
     * TODO: Call movie service and load upcoming movies from API
     */
    private void loadUpcomingMovies() {
        executeBackgroundTask(movieService::getUpcomingMovies, 
                loadedMovies -> this.movies = loadedMovies, 
                LoadedContentType.INCOMING_MOVIES, 
                "Loading Incoming movies", 
                "Incoming movies loaded");
    }
    
    private void loadWatchlist() {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Recupera todas las Movie guardadas en SQLite llamando a repository.findAll().
         *
         * Practica:
         * - Conectar una acción de Swing con la capa de base de datos
         * - Convertir la List<Movie> devuelta en los datos que muestra la tabla
         * - Refrescar el JTable después de cambios en la base de datos local
         *
         * Después de recuperar la lista:
         * - Guárdala en el campo movies
         * - Asigna WATCHLIST a contentType
         * - Refresca el JTable
         *
         * Por qué importa:
         * Este método es el puente entre el repositorio y la pantalla visible de
         * watchlist que los estudiantes pueden probar manualmente.
         */
        movies = new ArrayList<>();
        contentType = LoadedContentType.WATCHLIST;
        refreshTable();
    }
    
    private void loadButtonAction() {
        LoadedContentType selectedItem = (LoadedContentType) comboBox.getSelectedItem();
        
        switch(selectedItem) {
            case TOP_MOVIES -> {
                loadTopRatedMovies();
            }
            case INCOMING_MOVIES -> {
                loadUpcomingMovies();
            }
            case TV_SHOWS -> {
                loadTopRatedTVShows();
            }
            case WATCHLIST -> {
                loadWatchlist();
            }
        }
    }
    /*
    Execute Background Method
    */
    
    private <T> void executeBackgroundTask(
            BackgroundTaskInterface<ArrayList<T>> backgroundTask,
            Consumer<ArrayList<T>> onFinished,
            LoadedContentType type,
            String loadingMessage,
            String successMessage
    ){
        setLoadingState(true, loadingMessage);
        
        SwingWorker<ArrayList<T>, Void> worker =
                new SwingWorker<>() {
                    @Override
                    protected ArrayList<T> doInBackground() throws Exception {
                        return backgroundTask.execute();
                    }
                    @Override
                    protected void done() {
                        try {
                            ArrayList<T> result = get();
                            onFinished.accept(result);
                            contentType = type;
                            refreshTable();
                            statusLabel.setText(successMessage);
                            
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(MainFrame.this, e.getMessage());
                        } finally {
                            setLoadingState(false, statusLabel.getText());
                        }
                    }
                };
        worker.execute();
    }

    /**
     * TODO: Search movies using API
     */
    private void searchMovies() {

        String query = txtSearch.getText().trim();

        if (query.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a search term.");

            return;
        }

        JOptionPane.showMessageDialog(this,
                "TODO: Search movies");
    }

    /**
     * TODO: Open details dialog with selected movie
     */
    private void openMovieDetails() {

        int selectedRow = tblMovies.getSelectedRow();

        if(selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Please select a movie.");
            return;
        }
        
        if(contentType == LoadedContentType.TV_SHOWS) {
            JOptionPane.showMessageDialog(this,
                    "Tienes que seleccionar pelicula");
            return;
        }
        
        Movie selectedMovie = getSelectedMovieFromCurrentList(selectedRow);
        
        if(selectedMovie == null) {
            JOptionPane.showMessageDialog(this, "La película no esta disponible");
            return;
        }

        MovieDetailsDialog details = new MovieDetailsDialog(this, selectedMovie, repository);

        details.setSize(600, 600);
        details.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        details.setVisible(true);
    }
    
    private Movie getSelectedMovieFromCurrentList(int selectedRow) {
        if(selectedRow < 0 || selectedRow >= movies.size()) {
            return null;
        }
        if(contentType == LoadedContentType.TOP_MOVIES 
                || contentType == LoadedContentType.INCOMING_MOVIES
                || contentType == LoadedContentType.WATCHLIST) 
        {
            return movies.get(selectedRow);
        }
        
        return null;
    }
    
    private void removeWatchlistButtonAction() {
        
        if(contentType != LoadedContentType.WATCHLIST){
            JOptionPane.showMessageDialog(this, "Abre el Watchlist");
            return;
        }
        
        int selectedRow = tblMovies.getSelectedRow();
        
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a movie.");
            return;
        }
        
        Movie selectedMovie = getSelectedMovieFromCurrentList(selectedRow);
        
        if(selectedMovie == null) {
            JOptionPane.showMessageDialog(this, "Pelicula NO disponible");
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(this, 
                "Eliminar pelicula del Watchlist?", 
                "Confirmar", 
                JOptionPane.YES_NO_OPTION);
        
        if(option != JOptionPane.YES_OPTION){
            return;
        }
        
        boolean wasRemoved = repository.delete(selectedMovie.getId());
        if(wasRemoved) {
            JOptionPane.showMessageDialog(this, "Pelicula eliminada correctamente");
            loadWatchlist();
        } else {
            JOptionPane.showMessageDialog(this, "Error al borrar pelicula");
        }
        
        refreshTable();
    }
    
    

    // =========================
    // TABLE METHODS
    // =========================
    /**
     * Refresh JTable using movie collection
     */
    private void refreshTable() {
        clearTable();
        
        if(contentType == null) {
            return;
        }
        
        switch(contentType) {
            case LoadedContentType.WATCHLIST, LoadedContentType.INCOMING_MOVIES, LoadedContentType.TOP_MOVIES -> {
                for (Movie movie : movies) {
                    Object[] row = {
                        movie.getId(),
                        movie.getTitle(),
                        movie.getRating(),
                        movie.getReleaseDate(),
                        movie.getLanguage()
                    };
                    tableModel.addRow(row);
                }
            }
            case LoadedContentType.TV_SHOWS -> {
                for (TvShow show : shows) {
                    Object[] row = {
                        show.getId(),
                        show.getName(),
                        show.getRating(),
                        show.getOverview()
                    };
                    tableModel.addRow(row);
                }
            }
        }
    }

    /**
     * Remove all rows from JTable
     */
    private void clearTable() {
        tableModel.setRowCount(0);
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
