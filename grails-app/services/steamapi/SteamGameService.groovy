package steamapi

import timeUtils.TimeUtils

class SteamGameService {

    def steamUserService

    def getFriendsGamesPlayed2weeks(Map friendList) {
        if(!friendList){
            return null
        }

        Map allGamesPlayed = [:]
        def friendGames

        friendList.friendslist.friends.each {
            friendGames = steamUserService.getRecentlyPlayed(it.steamid)

            friendGames["response"]["games"].each{
                if(allGamesPlayed.containsKey(it.name)){
                    allGamesPlayed[it.name]["playtime_2weeks"] += it["playtime_2weeks"]
                    allGamesPlayed[it.name]["playtime_forever"] += it["playtime_forever"]
                } else {
                    allGamesPlayed.put(it.name, it)
                }
            }
        }

        //Sort by time played in desc order
        allGamesPlayed = allGamesPlayed.sort { a, b ->
            b.value["playtime_2weeks"]<=> a.value["playtime_2weeks"]
        }

        //change the format of the time to hours and minutes
        allGamesPlayed.each {
            it.value["playtime_2weeks"] = TimeUtils.prettifyTime(it.value["playtime_2weeks"])
        }

        return allGamesPlayed
    }
}
