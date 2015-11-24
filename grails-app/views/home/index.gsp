<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <h1>Games Played - Past 2 weeks</h1>
    <table>
        <thead>
            <tr>
                <td></td>
                <td>Game</td>
                <td>Time Played</td>

            </tr>
            <g:each in="${result}">
                <tr>
                    <td><img src="${grailsApplication.config.steam.images.url + "/" + it.value.appid + "/" + it.value.img_logo_url + ".jpg"}"></td>
                    <td>${it?.key}</td>
                    <td>${it?.value.playtime_2weeks}</td>
                </tr>
            </g:each>

        </thead>
    </table>

    <h1>Friends' Play Time - Past 2 weeks</h1>
    <table>
        <thead>
        <tr>
            <td></td>
            <td>Game</td>
            <td>Time Played</td>
        </tr>
        <g:each in="${friendsGames}">
            <tr>
                <td><img src="${grailsApplication.config.steam.images.url + "/" + it.value.appid + "/" + it.value.img_logo_url + ".jpg"}"></td>
                <td>${it.key}</td>
                <td>${it.value.playtime_2weeks}</td>
            </tr>
        </g:each>

        </thead>
    </table>

</body>
</html>