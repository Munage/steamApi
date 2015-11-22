<%--
  Created by IntelliJ IDEA.
  User: Jean
  Date: 11/6/15
  Time: 6:37 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <h1>Games Playes - Past 2 weeks</h1>


    <table>
        <thead>
            <tr>
                <td>Game</td>
                <td>Time Played</td>
                %{--<td></td>--}%
            </tr>

            <g:each in="${result}">
                <tr>
                    <td>${it.name}</td>
                    <td>${it.playtime}</td>
                    %{--<td>${it["img_logo_url"]}</td>--}%
                </tr>
            </g:each>

        </thead>
    </table>
</body>
</html>