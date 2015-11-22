package steamapi

import org.joda.time.Duration
import org.joda.time.Minutes
import org.joda.time.Period

class HomeController {

    def  restApiService

    def index() {
//        http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=XXXXXXXXXXXXXXXXX&steamid=76561197960434622&format=json

        Map q = [key: grailsApplication.config.steam.api.key, steamid:"76561198041210011", format:"json"]
        def result = restApiService.request("http://api.steampowered.com", "/IPlayerService/GetRecentlyPlayedGames/v0001", q)

        List final_result = []

        Duration duration
        result["response"]["games"].each {
            Long millisec = it["playtime_2weeks"]*60*1000;
            Period period = new Period(millisec);

            def hours = period.hours
            def minutes = period.minutes

            if(hours < 10){
                hours = "0" + hours
            }

            if (minutes < 10){
                minutes = "0" + minutes
            }

            String time = hours + ":" + minutes
            final_result.add([name:it["name"], playtime:time, logo:it["img_logo_url"]])
        }

        [result:final_result]
    }
}
