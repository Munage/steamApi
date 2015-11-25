package steamapi

import steam.oauth.SteamOpenID

class HomeController {

    def steamUserService
    def steamGameService

    def index() {
        String userId = session.steamId ?: params.steamId
        if(!userId) {
            return
        }

        def userRecentlyPlayed = steamGameService.getMyRecentlyPlayed(userId)
        def friendList = steamUserService.getFriendsList(userId)
        Map allGamesPlayed = steamGameService.getFriendsGamesPlayed2weeks(friendList)

        [result: userRecentlyPlayed, friendsGames: allGamesPlayed]
    }

    def login(){
        SteamOpenID steamOpenID = new SteamOpenID()
        redirect(url: steamOpenID.login(grailsApplication.config.grails.serverURL + "/home/verify"))
    }

    def verify(){
        SteamOpenID steamOpenID = new SteamOpenID()
        session.steamId = steamOpenID.verify(grailsApplication.config.grails.serverURL + "/home/verify", (Map)params)
        redirect(action: "index")
    }
}
