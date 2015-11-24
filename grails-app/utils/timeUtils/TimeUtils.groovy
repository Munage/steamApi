package timeUtils

import org.joda.time.Period


class TimeUtils {
    static String prettifyTime(int time){
        Long millisec = time*60*1000;
        Period period = new Period(millisec);

        def hours = period.hours
        def minutes = period.minutes

        if(hours < 10){
            hours = "0" + hours
        }

        if (minutes < 10){
            minutes = "0" + minutes
        }

        return hours + "hrs " + minutes + "mins"
    }

}
