/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.movieapp.model;

/**
 *
 * @author abrahamescamillapinelo
 */

@FunctionalInterface
public interface BackgroundTaskInterface<T> {
    T execute() throws Exception;
}
