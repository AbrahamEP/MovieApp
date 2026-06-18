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
import com.mycompany.movieapp.services.MovieService;
import database.WatchlistStore;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
    private WatchlistStore watchlistStore;

    public MovieDetailsDialog(Frame owner, Movie movie, WatchlistStore watchlistStore) {

        super(owner, "Movie Details", true);

        this.movie = movie;
        this.watchlistStore = watchlistStore;

        initializeDialog();
        initializeComponents();
        addComponents();
        loadMovieData();
        registerEvents();
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

        btnAddToWatchlist.addActionListener(e -> addToWatchlist());
    }

    private void loadMovieData() {

        lblTitle.setText(movie.getTitle());

        lblRating.setText("Rating: " + movie.getRating());

        lblReleaseDate.setText(
                "Release Date: " + movie.getReleaseDate());

        lblLanguage.setText(
                "Language: " + movie.getLanguage());

        txtOverview.setText(movie.getOverview());
    }

    /**
     * TODO:
     * Add movie to watchlist collection
     */
    private void addToWatchlist() {
        
        boolean wasAdded = watchlistStore.addMovieToWatchlist(movie);
        
        if(wasAdded) {
            JOptionPane.showMessageDialog(null, "La pelicula se guardó correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar la pelicula");
        }
        
    }
}