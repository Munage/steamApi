class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "home", action: "index")
		"/logout"(controller: "home", action: "logout")
		"500"(view:'/error')
	}
}
