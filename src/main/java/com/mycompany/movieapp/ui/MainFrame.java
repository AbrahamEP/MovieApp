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

    // =========================
    // STATE
    // =========================

    private List<Movie> movies;

    // =========================
    // CONSTRUCTOR
    // =========================

    public MainFrame() {

        movies = new ArrayList<>();

        initializeFrame();
        initializeComponents();
        configureTable();
        addComponents();
        registerEvents();

        // Temporary fake data
        loadFakeData();
    }

    // =========================
    // INITIALIZATION
    // =========================

    private void initializeFrame() {

        setTitle("MovieApp");
        setSize(1000, 600);
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
    }

    // =========================
    // ACTION METHODS
    // =========================

    /**
     * TODO:
     * Call movie service and load top rated movies from API
     */
    private void loadTopRatedMovies() {
        
        String endpoint = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        
        try {
            URL url = new URL(endpoint);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTQ4NzMwZDQ4NTdhZWU3MzczYTAzZTJjNDE4YzQ0ZiIsIm5iZiI6MTU4MDMzMjI1Mi43NTYsInN1YiI6IjVlMzFmNGRjOThmMWYxMDAwZjAwM2UzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JC3_vSjWY8m4sd8xNZWB2lipv9FPirjbjBwuvMVqaLY");
            
            //Leer la respuesta del servidor
            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line; 
            
            while ((line = reader.readLine()) != null ) {
                response.append(line);
            }
            reader.close();
            
            System.out.println("Respuesta de endpoint: " + response);
            
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            
            for(int i = 0; i < results.length(); i++) {
                JSONObject movieJson = results.getJSONObject(i);
                
                int id = movieJson.getInt("id");
                String title = movieJson.getString("title");
                double rating = movieJson.getDouble("vote_average");
                String releaseDate = movieJson.getString("release_date");
                String language = movieJson.getString("original_language");
                String overview = movieJson.getString("overview");
                
                Movie movie = new Movie(id, title, rating, releaseDate, language, overview);
                movies.add(movie);
            }
            
            for(Movie movie: movies) {
                System.out.println("Id: " + movie.getId() + "Title: " +movie.getTitle());
            }
            
            refreshTable();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, "Error al cargar peliculas");
        }
    }

    /**
     * TODO:
     * Call movie service and load top rated TV shows from API
     */
    private void loadTopRatedTVShows() {

        JOptionPane.showMessageDialog(this,
                "TODO: Load Top Rated TV Shows");
    }

    /**
     * TODO:
     * Call movie service and load upcoming movies from API
     */
    private void loadUpcomingMovies() {

        JOptionPane.showMessageDialog(this,
                "TODO: Load Upcoming Movies");
    }

    /**
     * TODO:
     * Search movies using API
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
     * TODO:
     * Open details dialog with selected movie
     */
    private void openMovieDetails() {

        int selectedRow = tblMovies.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Please select a movie.");

            return;
        }

        
    }

    // =========================
    // TABLE METHODS
    // =========================

    /**
     * Refresh JTable using movie collection
     */
    private void refreshTable() {
        clearTable();
        
        for(Movie movie : movies) {
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

    /**
     * Remove all rows from JTable
     */
    private void clearTable() {
        tableModel.setRowCount(0);
    }

    // =========================
    // TEMPORARY TEST DATA
    // =========================

    /**
     * Temporary fake data
     */
    private void loadFakeData() {

        movies.add(new Movie(
                1,
                "Dune Part Two",
                8.8,
                "2024-03-01",
                "EN",
                "Epic science fiction movie."
        ));

        movies.add(new Movie(
                2,
                "The Batman",
                7.9,
                "2022-03-04",
                "EN",
                "Batman investigates corruption in Gotham."
        ));

        movies.add(new Movie(
                3,
                "Interstellar",
                8.6,
                "2014-11-07",
                "EN",
                "Space exploration to save humanity."
        ));

        refreshTable();
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