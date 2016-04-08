class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/1.0/login.json"(controller: 'nologin', action: 'login')
        "/api/1.0/user/info.json"(controller: 'user', action: 'info')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
