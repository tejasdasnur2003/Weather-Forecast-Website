<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Digital Clock</title>
    <link rel="stylesheet" href="clockstyle.css">

    <!-- Pass the local time to JavaScript dynamically -->
    <script type="text/javascript">
        var localTimeFromServer = "<%= request.getParameter("time") != null ? request.getParameter("time").toString().replace("\"", "\\\"") : "00:00" %>";
        console.log(localTimeFromServer);
    </script>

    <!-- Include your JS file -->
    <script type="text/javascript" src="realTime.js"></script>
</head>
<body>
    <div class="container">
        <div id="time">
            <p>City: <%= request.getParameter("city") %></p>
            <p>Local Time: <%= request.getParameter("time") %></p>

            <!-- Clock circles and time elements as per your existing design -->
            <div class="circle" style="--color: #ff2972">
                <div class="dots h_dot"></div>
                <svg>
                    <circle cx="70" cy="70" r="70"></circle>
                    <circle cx="70" cy="70" r="70" id="hh"></circle>
                </svg>
                <div id="hours">00</div>
            </div>
            <div class="circle" style="--color: #fee800">
                <div class="dots m_dot"></div>
                <svg>
                    <circle cx="70" cy="70" r="70"></circle>
                    <circle cx="70" cy="70" r="70" id="mm"></circle>
                </svg>
                <div id="minutes">00</div>
            </div>
            <div class="circle" style="--color: #04fc43">
                <div class="dots s_dot"></div>
                <svg>
                    <circle cx="70" cy="70" r="70"></circle>
                    <circle cx="70" cy="70" r="70" id="ss"></circle>
                </svg>
                <div id="seconds">00</div>
            </div>
            <div class="ap">
                <div id="ampm">AM</div>
            </div>
        </div> 
    </div>
</body>
</html>
