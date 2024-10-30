<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-size: cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: #2f363e;
        }

        .container {
            
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-radius: 20px;
            overflow: hidden;
            width: 900px;
            height: 600px;
            background-size: cover;
            background-position: center center;
            background-size: cover; /* or contain */
  			background-position: center;
  			background-repeat: no-repeat;
        }

        .right {
            width: 350px;
            height: 600px;
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
            padding: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            text-align: center;
        }

        .search-bar {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 20px 0;
            width: 95%;
        }

        .search-bar input {
            width: 60%;
            padding: 10px;
            font-size: 1em;
            border-radius: 20px;
            border: none;
            padding-right: 50px; /* Add space for the submit button on the right */
            position: relative;
        }

        /* Reset button */
        .search-bar button[type="reset"] {
            position: absolute;
            right: 80px; /* Set this to the left of the submit button */
            top: 50%;
            transform: translateY(-50%);
            background: gray;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 50%;
            cursor: pointer;
            padding: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /* Submit button on the right side of the input */
        .search-bar button[type="submit"] {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            cursor: pointer;
            padding: 5px;
            height: 35px;
        }

        .search-bar button[type="submit"] img {
            width: 25px;
            height: 20px;
        }

        .custom-submit {
            background-color: #f8f9fa !important;
            color: #000 !important;
            border: none;
            border-radius: 50%;
            padding: 10px;
            font-size: 1.5em;
            cursor: pointer;
            width: 40px;
            height: 40px;
        }

        .custom-submit:hover {
            background-color: #e2e6ea !important;
        }

        .weather-status img {
            width: 60px;
            height: 60px;
        }

        .weather-details {
            text-align: left;
            font-size: 1.2em;
            margin-top: 20px;
        }

        .weather-details p {
            margin-bottom: 10px;
        }

        .weather-details hr {
            border: 0;
            border-top: 1px solid #ccc;
            margin: 10px 0;
            width: 95%;
        }
        .city-name {
            padding-top: 20px;
            text-align: center;
            padding-bottom: 25px;
        }
        
        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 5px 0;
        }

        .info-row p {
            margin: 0;
        }

        hr {
            border: none;
            border-top: 1px solid #ccc;
            margin: 3px 0;
        }
    </style>
</head>
<body>
<div class="container" style="background-image: url('<%= request.getAttribute("backgroundImg") %>'); width: 65%;">
    <div>
    </div>
    <div class="right">
        <div class="search-bar">
            <form action="MyServlet" method="POST">
                <input type="text" name="city" placeholder="Search City" id="cityInput" value="<%= request.getAttribute("city") %>">
                <button type="reset" aria-label="Clear input" title="Clear input">×</button>
                <button type="submit" class="custom-submit">
                    <img src="https://cdn-icons-png.flaticon.com/512/10629/10629681.png"/>
                </button>
            </form>
        </div>

        <div class="weather-status">
            <img src="<%= request.getAttribute("weatherIconUrl") %>" alt="Weather Icon" class="weather-icon">
            <h2><%= request.getAttribute("weatherCondition") %></h2>
        </div>

        <div class="weather-details">
            <p class="city-name"><%= request.getAttribute("city") %></p>
            <div class="info-row">
                <p>Date</p>
                <p><%= request.getAttribute("localDate") %></p>
            </div>
            <hr>
            <div class="info-row">
                <p>Temperature</p>
                <p><%= request.getAttribute("temperature") %> °C</p>
            </div>
            <hr>
            <div class="info-row">
                <p>Humidity</p>
                <p><%= request.getAttribute("humidity") %> %</p>
            </div>
            <hr>
            <div class="info-row">
                <p>Wind Speed</p>
                <p><%= request.getAttribute("windSpeed") %> m/s</p>
            </div>
            <hr>
            <div class="info-row">
                <p>Time</p>
                <p><%= request.getAttribute("time") %></p>
            </div>
            <hr>
        </div>
        <div>
        <div>
    		<a href="liveclock.jsp?city=<%= request.getAttribute("city") %>&time=<%= request.getAttribute("time") %>" class="more-info-link">More Info</a>
		</div>

        </div>
    </div>
</div>

<script>
    function clearInput() {
        document.getElementById("cityInput").value = ''; // Clear the input field
        document.getElementById("cityInput").focus();    // Refocus the input field after clearing
    }
</script>
</body>
</html>