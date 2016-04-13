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
        "/api/1.0/user/query.json"(controller: 'user', action: 'query')
        "/api/1.0/user/friends.json"(controller: 'user', action: 'friends')
        "/api/1.0/user/contact.json"(controller: 'user', action: 'contact')

        "/api/1.0/require/get.json"(controller: 'require', action: 'get')
        "/api/1.0/require/post.json"(controller: 'require', action: 'post')

        "/api/1.0/photo/pull.json"(controller: 'photo', action: 'pull')
        "/api/1.0/photo/upload.json"(controller: 'photo', action: 'upload')
        "/api/1.0/photo/delete.json"(controller: 'photo', action: 'delete')

        "/api/1.0/tag/pull.json"(controller: 'tag', action: 'pull')
        "/api/1.0/tag/add.json"(controller: 'tag', action: 'add')
        "/api/1.0/tag/delete.json"(controller: 'tag', action: 'delete')

        "/api/1.0/relationChain/add.json"(controller: 'relationChain', action: 'add')
        "/api/1.0/relationChain/common.json"(controller: 'relationChain', action: 'common')

        "/api/1.0/recommend/pull.json"(controller: 'recommend', action: 'pull')
        "/api/1.0/recommend/add.json"(controller: 'recommend', action: 'add')

        "/api/1.0/personData/query.json"(controller: 'personData', action: 'query')
        "/api/1.0/personData/get.json"(controller: 'personData', action: 'get')

        "/api/1.0/heat/add.json"(controller: 'heat', action: 'add')
        "/api/1.0/heat/like.json"(controller: 'heat', action: 'like')
        "/api/1.0/heat/ing.json"(controller: 'heat', action: 'ing')
        "/api/1.0/heat/likeMe.json"(controller: 'heat', action: 'likeMe')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
