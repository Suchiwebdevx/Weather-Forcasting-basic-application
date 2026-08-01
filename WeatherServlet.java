package com.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String city = request.getParameter("city");

        System.out.println("City : " + city);

        String apiKey = " Replace with your API key";

        String apiURL = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&units=metric";

        System.out.println(apiURL);

        try {

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("Response Code : " + responseCode);

            if (responseCode != 200) {

                request.setAttribute("error", "City not found or API error.");

                RequestDispatcher rd = request.getRequestDispatcher("weather.jsp");
                rd.forward(request, response);
                return;
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            StringBuilder result = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();

            System.out.println(result.toString());

            JsonObject json = JsonParser.parseString(result.toString()).getAsJsonObject();

            String temp = json.getAsJsonObject("main").get("temp").getAsString();
            String humidity = json.getAsJsonObject("main").get("humidity").getAsString();
            String wind = json.getAsJsonObject("wind").get("speed").getAsString();

            String description = json.getAsJsonArray("weather")
                    .get(0)
                    .getAsJsonObject()
                    .get("description")
                    .getAsString();

            request.setAttribute("city", city);
            request.setAttribute("temp", temp);
            request.setAttribute("humidity", humidity);
            request.setAttribute("wind", wind);
            request.setAttribute("description", description);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error", "Unable to fetch weather information.");

        }

        RequestDispatcher rd = request.getRequestDispatcher("weather.jsp");
        rd.forward(request, response);
    }
}