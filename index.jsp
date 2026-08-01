<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Forecast</title>

<link rel="stylesheet" href="style.css">

</head>
<body>

<div class="container">
    <img src="images/weather-logo.png" alt="Weather Logo" class="logo">

    <h1>Weather Forecast</h1>

    <form action="WeatherServlet" method="post">

        <input type="text"
               name="city"
               placeholder="Enter City Name"
               required>

        <button type="submit">
            Get Weather
        </button>

    </form>

</div>

</body>
</html>