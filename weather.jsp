<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Weather Report</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

</head>

<body>

<div class="container">

    <img src="<%=request.getContextPath()%>/images/weather-logo.png"
         class="logo"
         alt="Weather Logo">

    <h1>🌤️ Weather Forecast</h1>

    <div class="weather-card">

        <h2><%=request.getAttribute("city")%></h2>

        <p><strong>🌡️ Temperature :</strong>
        <%=request.getAttribute("temp")%> °C</p>

        <p><strong>💧 Humidity :</strong>
        <%=request.getAttribute("humidity")%>%</p>

        <p><strong>🌬️ Wind Speed :</strong>
        <%=request.getAttribute("wind")%> km/h</p>

        <p><strong>☁️ Weather :</strong>
        <%=request.getAttribute("description")%></p>

    </div>

    <br>

    <form action="index.jsp">
        <button type="submit" class="btn">
            🔍 Search Another City
        </button>
    </form>

</div>

</body>
</html>