/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieapp.model;

/**
 *
 * @author abrahamescamillapinelo
 */
public enum LoadedContentType {
    TOP_MOVIES("Top Rated Movies"),
    INCOMING_MOVIES("Incoming Movies"),
    TV_SHOWS("TV Shows"),
    WATCHLIST("Watchlist");
    
    private String text;
    
    LoadedContentType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
