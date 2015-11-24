package steamapi


class SteamUserService {

    def restApiService
    def grailsApplication

    def getFriendsList(String id){
        if(!id){
            return null
        }

        Map q = [key: grailsApplication.config.steam.api.key, steamid:id, "relationship":"friend", format:"json"]
        def response = restApiService.request(grailsApplication.config.steam.api.url, "/ISteamUser/GetFriendList/v0001", q)
    }

    def getRecentlyPlayed(String id){
        if(!id){
            return null
        }

        Map q = [key: grailsApplication.config.steam.api.key, steamid:id, format:"json"]
        def response = restApiService.request(grailsApplication.config.steam.api.url, "/IPlayerService/GetRecentlyPlayedGames/v0001", q)
    }
}
