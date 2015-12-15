package steamapi

class CookieFilterFilters {

    def filters = {
        all(controller:'home', action:'index') {
            before = {
                //Checks for a cookie with the user's steamId and sets it in session if found
                Utilities.checkCookieValue(request, session, grailsApplication.config.preference.cookieName, "steamId")
            }
        }
    }
}
