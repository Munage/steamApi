package timeUtils

class TimeUtils {
    static String prettifyTime(int time){
        def hours = time / 60
        def minutes = time % 60

        if(hours < 10){
            hours = "0" + hours
        }

        if (minutes < 10){
            minutes = "0" + minutes
        }

        return hours + "hrs " + minutes + "mins"
    }

}
