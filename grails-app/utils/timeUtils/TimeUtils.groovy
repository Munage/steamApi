package timeUtils

import java.util.concurrent.TimeUnit

class TimeUtils {
    static String prettifyTime(int time){

        long hours = TimeUnit.MINUTES.toHours(time)
        long remainMinute = time - TimeUnit.HOURS.toMinutes(hours)
        String result = String.format("%02d", hours) + ":" + String.format("%02d", remainMinute);

        return result
    }

}
