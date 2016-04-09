class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/1.0/login.json"(controller: 'nologin', action: 'login')

        "/api/1.0/user/get.json"(controller: 'user', action: 'get')
        "/api/1.0/user/post.json"(controller: 'user', action: 'post')

        "/api/1.0/require/get.json"(controller: 'require', action: 'get')
        "/api/1.0/require/post.json"(controller: 'require', action: 'post')

        "/api/1.0/photo/pull.json"(controller: 'photo', action: 'pull')
        "/api/1.0/photo/upload.json"(controller: 'photo', action: 'upload')
        "/api/1.0/photo/delete.json"(controller: 'photo', action: 'delete')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
