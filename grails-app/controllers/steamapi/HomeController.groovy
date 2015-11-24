package steamapi

import steam.oauth.SteamOpenID
import timeUtils.TimeUtils

class HomeController {

    def  restApiService
    def steamUserService
    def steamGameService

    def index() {
        String userId = params.steamId ?: "76561198041210011"

        def userRecentlyPlayed = steamUserService.getRecentlyPlayed(userId)
        def friendList = steamUserService.getFriendsList(userId)
        List final_result = []
        Map allGamesPlayed = steamGameService.getFriendsGamesPlayed2weeks(friendList)

        userRecentlyPlayed["response"]["games"].each {
            final_result.add([name:it["name"], playtime:TimeUtils.prettifyTime(it["playtime_2weeks"]), logo:it["img_logo_url"]])
        }

        [result:final_result, friendsGames:allGamesPlayed]
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
