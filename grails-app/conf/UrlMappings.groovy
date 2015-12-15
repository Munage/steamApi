class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "home", action: "index")
		"/refresh"(controller: "home", action: "refresh")
		"/logout"(controller: "home", action: "logout")
		"500"(view:'/error')
	}
}
