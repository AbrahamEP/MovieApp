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
import database.WatchlistRepository;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class MovieDetailsDialog extends JDialog {

    private JLabel lblPoster;

    private JLabel lblTitle;
    private JLabel lblRating;
    private JLabel lblReleaseDate;
    private JLabel lblLanguage;

    private JTextArea txtOverview;

    private JButton btnAddToWatchlist;
    private JButton btnClose;

    private Movie movie;
    private WatchlistRepository repository;
    
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342";

    public MovieDetailsDialog(Frame owner, Movie movie, WatchlistRepository repository) {

        super(owner, "Movie Details", true);

        this.movie = movie;
        this.repository = repository;

        initializeDialog();
        initializeComponents();
        addComponents();
        loadMovieData();
        registerEvents();
        loadLazyPoster();
    }

    private void initializeDialog() {

        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        lblPoster = new JLabel("NO IMAGE", SwingConstants.CENTER);
        lblPoster.setPreferredSize(new Dimension(220, 320));
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        lblTitle = new JLabel();
        lblRating = new JLabel();
        lblReleaseDate = new JLabel();
        lblLanguage = new JLabel();

        txtOverview = new JTextArea();

        txtOverview.setLineWrap(true);
        txtOverview.setWrapStyleWord(true);
        txtOverview.setEditable(false);

        btnAddToWatchlist = new JButton("Add To Watchlist");
        btnClose = new JButton("Close");
    }

    private void addComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // =====================================
        // LEFT PANEL
        // =====================================

        JPanel leftPanel = new JPanel(new BorderLayout());

        leftPanel.add(lblPoster, BorderLayout.CENTER);

        // =====================================
        // RIGHT PANEL
        // =====================================

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        rightPanel.add(lblTitle);
        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(lblRating);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(lblReleaseDate);
        rightPanel.add(Box.createVerticalStrut(10));

        rightPanel.add(lblLanguage);
        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(new JLabel("Overview:"));
        rightPanel.add(Box.createVerticalStrut(10));

        JScrollPane scrollPane = new JScrollPane(txtOverview);
        scrollPane.setPreferredSize(new Dimension(350, 200));

        rightPanel.add(scrollPane);

        // =====================================
        // BOTTOM PANEL
        // =====================================

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.add(btnAddToWatchlist);
        bottomPanel.add(btnClose);

        // =====================================
        // ADD TO MAIN PANEL
        // =====================================

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void registerEvents() {

        btnClose.addActionListener(e -> dispose());

        btnAddToWatchlist.addActionListener(e -> addMovieToWatchlist());
    }
    
    private void loadLazyPoster() {
        String path = movie.getPosterPath();
        
        if(path == null || path.isBlank()) {
            lblPoster.setText("Imagen no disponible");
            return;
        }
        
        lblPoster.setText("Cargando imagen...");
        
        SwingWorker<ImageIcon, Void> worker = 
                new SwingWorker<>() {
                    
                    @Override 
                    protected ImageIcon doInBackground() throws Exception {
                        URL imageUrl = new URL(IMAGE_BASE_URL + path);
                        ImageIcon originalIcon = new ImageIcon(imageUrl);
                        
                        Image scaledImage = originalIcon.getImage().getScaledInstance(320, 400, Image.SCALE_SMOOTH);
                        return new ImageIcon(scaledImage);
                    }
                    
                    @Override
                    protected void done() {
                        try{
                            ImageIcon icon = get();
                            
                            lblPoster.setText("");
                            lblPoster.setIcon(icon);
                        } catch (Exception e) {
                            lblPoster.setText("Error de imagen");
                        }
                    }
                    
                };
                worker.execute();
        
    }
    
    private void loadMovieData() {

        lblTitle.setText(movie.getTitle());

        lblRating.setText("Rating: " + movie.getRating());

        lblReleaseDate.setText(
                "Release Date: " + movie.getReleaseDate());

        lblLanguage.setText(
                "Language: " + movie.getLanguage());

        txtOverview.setText(movie.getOverview());
        
        updateWatchlistButtonState();
    }
    
    private void updateWatchlistButtonState() {
        boolean isAlreadyInWatchlist = repository.exists(movie.getId());
        
        if(isAlreadyInWatchlist) {
            btnAddToWatchlist.setText("Already in Watchlist");
            btnAddToWatchlist.setEnabled(false);
        } else {
            btnAddToWatchlist.setText("Add to watchlist");
            btnAddToWatchlist.setEnabled(true);
        }
    }

    private void addMovieToWatchlist() {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Guarda la Movie seleccionada usando repository.save(movie).
         *
         * Practica:
         * - Llamar a un método de la capa de base de datos desde un evento Swing
         * - Verificar si la operación de guardado fue exitosa
         * - Mostrar un mensaje de diálogo apropiado
         * - Pensar en películas duplicadas que ya están en la watchlist
         *
         * Por qué importa:
         * Este método conecta el clic del botón del usuario con la operación
         * INSERT que los estudiantes implementarán en WatchlistRepository.
         */
        JOptionPane.showMessageDialog(
                this,
                "Ejercicio para estudiantes: implementa repository.save(movie) y maneja el resultado."
        );

        updateWatchlistButtonState();
    }
}
