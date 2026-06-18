/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieapp.ui;

/**
 *
 * @author abrahamescamillapinelo
 */
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
import database.WatchlistStore;

public class MainFrame extends JFrame {

    // =========================
    // COMPONENTS
    // =========================
    private JTextField txtSearch;
    private JButton btnSearch;

    private JButton btnTopRatedMovies;
    private JButton btnTopRatedTVShows;
    private JButton btnUpcomingMovies;

    private JTable tblMovies;
    private DefaultTableModel tableModel;

    private JButton btnViewDetails;
    
    private JButton showWatchlistButton;
    private JButton deleteFromWatchlistButton;

    // =========================
    // STATE
    // =========================
    private ArrayList<Movie> movies;
    private ArrayList<TvShow> shows;
    MovieService movieService;
    private LoadedContentType contentType;
    private WatchlistStore watchlistStore;

    // =========================
    // CONSTRUCTOR
    // =========================
    public MainFrame() {

        movies = new ArrayList<>();
        watchlistStore = new WatchlistStore();
        
        initializeFrame();
        initializeComponents();
        configureTable();
        addComponents();
        registerEvents();

        movieService = new MovieService();
        
    }

    // =========================
    // INITIALIZATION
    // =========================
    private void initializeFrame() {

        setTitle("MovieApp");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        txtSearch = new JTextField(25);

        btnSearch = new JButton("Search");

        btnTopRatedMovies = new JButton("Top Rated Movies");
        btnTopRatedTVShows = new JButton("Top Rated TV Shows");
        btnUpcomingMovies = new JButton("Upcoming Movies");

        btnViewDetails = new JButton("View Details");
        
        showWatchlistButton = new JButton("Show Watchlist");
        deleteFromWatchlistButton = new JButton("Delete From Watchlist");

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
        topPanel.setBackground(Color.red);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonsPanel.add(btnTopRatedMovies);
        buttonsPanel.add(btnTopRatedTVShows);
        buttonsPanel.add(btnUpcomingMovies);
        buttonsPanel.add(showWatchlistButton);
        buttonsPanel.add(deleteFromWatchlistButton);

        topPanel.add(searchPanel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(buttonsPanel);

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

    // =========================
    // EVENTS
    // =========================
    private void registerEvents() {

        btnTopRatedMovies.addActionListener(e -> loadTopRatedMovies());

        btnTopRatedTVShows.addActionListener(e -> loadTopRatedTVShows());

        btnUpcomingMovies.addActionListener(e -> loadUpcomingMovies());

        btnSearch.addActionListener(e -> searchMovies());

        btnViewDetails.addActionListener(e -> openMovieDetails());
        
        showWatchlistButton.addActionListener(e -> showWatchlistButtonAction());
        
        deleteFromWatchlistButton.addActionListener(e -> removeWatchlistButtonAction());
    }

    // =========================
    // ACTION METHODS
    // =========================
    /**
     * TODO: Call movie service and load top rated movies from API
     */
    private void loadTopRatedMovies() {
        clearTable();
        try {
            movies = movieService.getTopRatedMovies();
            contentType = LoadedContentType.TOP_MOVIES;
            refreshTable();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar peliculas");
        }
        
    }

    /**
     * TODO: Call movie service and load top rated TV shows from API
     */
    private void loadTopRatedTVShows() {

        clearTable();
        try {
            shows = movieService.getTopRatedTVShows();
            contentType = LoadedContentType.TV_SHOWS;
            refreshTable();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar shows");
        }
    }

    /**
     * TODO: Call movie service and load upcoming movies from API
     */
    private void loadUpcomingMovies() {

        clearTable();
        try {
            movies = movieService.getUpcomingMovies();
            contentType = LoadedContentType.INCOMING_MOVIES;
            refreshTable();
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al cargar peliculas");
        }
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

        int idMovie = (int) tableModel.getValueAt(selectedRow, 0);
        

        Movie selectedMovie = movieService.getMovieById(idMovie);

        MovieDetailsDialog details = new MovieDetailsDialog(this, selectedMovie, watchlistStore);

        details.setSize(600, 600);
        details.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        details.setVisible(true);
    }
    
    private void removeWatchlistButtonAction() {
        int selectedRow = tblMovies.getSelectedRow();
        
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a movie.");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        boolean wasRemoved = watchlistStore.removeFromWatchlist(id);
        
        if(wasRemoved) {
            JOptionPane.showMessageDialog(null, "Pelicula eliminada correctamente");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar pelicula del Watchlist");
        }
        
    }
    
    private void showWatchlistButtonAction() {
        clearTable();
        
        movies = watchlistStore.getWatchlist();
        contentType = LoadedContentType.WATCHLIST;
        
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
        
        switch (contentType) {
            case LoadedContentType.WATCHLIST:
            case LoadedContentType.INCOMING_MOVIES:
            case LoadedContentType.TOP_MOVIES:
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
                break;
                
            case LoadedContentType.TV_SHOWS:
                for (TvShow show : shows) {
                    Object[] row = {
                        show.getId(),
                        show.getName(),
                        show.getRating(),
                        show.getOverview()
                    };
                    tableModel.addRow(row);
                }
                break;
             
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
