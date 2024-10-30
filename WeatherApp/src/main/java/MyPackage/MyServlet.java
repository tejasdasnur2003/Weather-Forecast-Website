package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// @WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        if (city == null || city.isEmpty()) {
            response.getWriter().write("No city provided!");
            return;
        }
        city = URLEncoder.encode(city, "UTF-8");

        // API integration
        String apiKey = "9a1844bb147c46c99b7122127241810";
        String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        // Make the HTTP request
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder responseContent = new StringBuilder();
        
        // Get the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                 InputStreamReader reader = new InputStreamReader(inputStream);
                 Scanner scanner = new Scanner(reader)) {

                while (scanner.hasNext()) {
                    responseContent.append(scanner.nextLine());
                }
            }

            // Parse the JSON response using the older parse method
            JsonObject jsonObject = new JsonParser().parse(responseContent.toString()).getAsJsonObject();

            // Extract relevant data from the response
            JsonObject location = jsonObject.getAsJsonObject("location");
            String cityName = location.get("name").getAsString();
            String region = location.get("region").getAsString();
            String country = location.get("country").getAsString();
            //String localtime = location.get("localtime").getAsString();
            
            String localtime=null;
            localtime = location.get("localtime").getAsString();
            String date = localtime.split(" ")[0]; // Splits by space and takes the first part
            localtime = location.get("localtime").getAsString();
            String time = localtime.split(" ")[1];
           
           
            
            
            JsonObject currentWeather = jsonObject.getAsJsonObject("current");
            double feelsLikeCelsius  = currentWeather.get("temp_c").getAsDouble();
            double tempCelsius = currentWeather.get("feelslike_c").getAsDouble();
            int humidity = currentWeather.get("humidity").getAsInt();
            double windKph = currentWeather.get("wind_kph").getAsDouble();
            JsonObject condition = currentWeather.getAsJsonObject("condition");
            String weatherCondition = condition.get("text").getAsString();
            String weatherIcon = condition.get("icon").getAsString();
            double visibilityKm = currentWeather.get("vis_km").getAsDouble();

            // Print the extracted information (for debug)
            System.out.println("City: " + cityName + ", " + region + ", " + country);
            System.out.println("Local Date: " + date);
            System.out.println("Local Time: " + time);
            System.out.println("Temperature: " + tempCelsius + " °C");
            System.out.println("Feels Like: " + feelsLikeCelsius + " °C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind Speed: " + windKph + " km/h");
            System.out.println("Weather Condition: " + weatherCondition);
            System.out.println("Weather Icon: " + weatherIcon);
            System.out.println("Visibility: " + visibilityKm + " km");

            // Determine icon and background based on weather condition
            String weatherIconUrl;
            String backgroundImg;
            switch (weatherCondition.toLowerCase()) {
                case "clear":
                case "sunny":
                    weatherIconUrl = "https://pics.clipartpng.com/midle/Sunny_Weather_Icon_PNG_Clip_Art-1526.png";
                    backgroundImg = "https://w0.peakpx.com/wallpaper/543/420/HD-wallpaper-rising-sun-behind-clouds-behind-bright-clouds-sun-rising-blue.jpg";
                    break;
                case "rain":
                case "light rain":
                case "moderate rain":
                    weatherIconUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSLV6gMLH9pQT9QGk9I-nThCqiD9BxwRWa8Lu7b1AQ65sTjhxPodwfg7zrCS315aYXWIQ&usqp=CAU";
                    backgroundImg = "https://static.vecteezy.com/system/resources/previews/042/146/518/non_2x/ai-generated-beautiful-rain-day-view-photo.jpg";
                    break;
                case "clouds":
                    weatherIconUrl = "https://cdn-icons-png.flaticon.com/512/5903/5903939.png";
                    backgroundImg = "https://plus.unsplash.com/premium_photo-1673278171570-18af2a6ece31?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Y2xvdWR5JTIwd2VhdGhlcnxlbnwwfHwwfHx8MA%3D%3D";
                    break;
                case "snow":
                    weatherIconUrl = "https://cdn2.vectorstock.com/i/1000x1000/20/11/snow-chalk-white-icon-on-black-background-vector-31012011.jpg";
                    backgroundImg = "https://nychazardmitigation.com/wp-content/uploads/2024/07/20170314_Snowy_Street_2-edited.jpg";
                    break;
                case "haze":
                    weatherIconUrl = "https://cdn-icons-png.flaticon.com/512/1779/1779862.png";
                    backgroundImg = "https://media.wired.com/photos/65e860922a01e579ac0d29f2/1:1/w_1280,h_1280,c_limit/london-heatwave.jpg";
                    break;
                default:
                    weatherIconUrl = "https://img.freepik.com/premium-photo/realistic-sun-with-rays-icon-weather-design-hot-temperature-sunshine-symbol-vector-stock_1199132-225610.jpg";
                    backgroundImg = "https://live.staticflickr.com/65535/52990520298_62cb3da5c6_b.jpg";
                    break;
            }

            // Set attributes for forwarding to JSP
            request.setAttribute("city", cityName);
            request.setAttribute("localDate", date);
            request.setAttribute("time", time);
            request.setAttribute("temperature", tempCelsius);
            request.setAttribute("feelsLike", feelsLikeCelsius);
            request.setAttribute("humidity", humidity);
            request.setAttribute("windSpeed", windKph);
            request.setAttribute("weatherCondition", weatherCondition);
            request.setAttribute("weatherIconUrl", weatherIconUrl);
            request.setAttribute("backgroundImg", backgroundImg);

            // Forward to JSP
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            request.getRequestDispatcher("/liveclock.jsp").forward(request, response);
            
        } else {
            response.getWriter().append("<br>Error: Failed to retrieve weather data. HTTP response code: " + responseCode);
        }
        
        connection.disconnect();
    }
}
