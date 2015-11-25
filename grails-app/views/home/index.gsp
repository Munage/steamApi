<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <g:if test="${!session.steamId}">
        <h2>Please sign in with Steam to see what you and your friends have been playing</h2>
        <g:link controller="home" action="login"><img src="/images/sits_large_border.png"/></g:link>
    </g:if>
    <g:else>
        <h2>Games Played - Past 2 weeks</h2>
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

        <h2>Friends' Play Time - Past 2 weeks</h2>
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
    </g:else>
</body>
</html>