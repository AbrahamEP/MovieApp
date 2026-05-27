/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

/**
 *
 * @author abrahamescamillapinelo
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class TMDBClient {

    private static final String apiToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTQ4NzMwZDQ4NTdhZWU3MzczYTAzZTJjNDE4YzQ0ZiIsIm5iZiI6MTU4MDMzMjI1Mi43NTYsInN1YiI6IjVlMzFmNGRjOThmMWYxMDAwZjAwM2UzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JC3_vSjWY8m4sd8xNZWB2lipv9FPirjbjBwuvMVqaLY";

    public String sendGetRequest(String endpoint) throws Exception {
        URL url = new URL(endpoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiToken);

        //Leer la respuesta del servidor
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
