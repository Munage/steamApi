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

        def userRecentlyPlayed
        if(session.userRecentlyPlayed){
            userRecentlyPlayed = session.userRecentlyPlayed
        } else {
            userRecentlyPlayed = steamGameService.getMyRecentlyPlayed(userId)
            session.userRecentlyPlayed = userRecentlyPlayed
        }

        def friendList
        Map allGamesPlayed
        if(session.allGamesPlayed){
            allGamesPlayed = session.allGamesPlayed
            friendList = session.friendList
        } else {
            friendList = steamUserService.getFriendsList(userId)
            allGamesPlayed = steamGameService.getFriendsGamesPlayed2weeks(friendList)

            session.friendList = friendList
            session.allGamesPlayed = allGamesPlayed
        }

        [result: userRecentlyPlayed, friendsGames: allGamesPlayed]
    }

    def login(){
        SteamOpenID steamOpenID = new SteamOpenID()
        redirect(url: steamOpenID.login(grailsApplication.config.grails.serverURL + "/home/verify"))
    }

    def verify(){
        SteamOpenID steamOpenID = new SteamOpenID()
        session.steamId = steamOpenID.verify(grailsApplication.config.grails.serverURL + "/home/verify", (Map)params)
        Utilities.setCookie(response, grailsApplication.config.preference.cookieName, session.steamId)
        redirect(action: "index")
    }

    def logout(){
        session.invalidate()
        redirect(action: "index")
    }
}
