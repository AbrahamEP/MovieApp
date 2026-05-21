/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.movieapp;

import com.mycompany.movieapp.ui.MainFrame;
import javax.swing.JFrame;

/**
 *
 * @author abrahamescamillapinelo
 */
public class MovieApp {

    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();
        mainFrame.setSize(600, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
