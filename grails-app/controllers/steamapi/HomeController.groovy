package steamapi

import steam.oauth.SteamOpenID

class HomeController {

    def steamUserService
    def steamGameService

    def index() {
        String userId = params.steamId ?: "76561198041210011"

        def userRecentlyPlayed = steamGameService.getMyRecentlyPlayed(userId)
        def friendList = steamUserService.getFriendsList(userId)
        Map allGamesPlayed = steamGameService.getFriendsGamesPlayed2weeks(friendList)

        [result:userRecentlyPlayed, friendsGames:allGamesPlayed]
    }

    def login(){
        SteamOpenID steamOpenID = new SteamOpenID()
        println(steamOpenID.login("http://localhost:8080/"))
        redirect(url: steamOpenID.login("http://localhost:8080/home/verify"))
    }

    def verify(){
        println(params)
        Map response = [:]
        response.put("openid.op_endpoint", params["openid.op_endpoint"])

        SteamOpenID steamOpenID = new SteamOpenID()
        steamOpenID.verify("http://localhost:8080/home/verify", (Map)params)
    }
}
