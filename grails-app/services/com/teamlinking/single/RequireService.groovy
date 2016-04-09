package com.teamlinking.single

class RequireService {

    Require get(long id){
        Require require = Require.get(id)
        if (require == null){
            User user = User.get(id)
            if (user == null){
                return null
            }
            require = new Require()
            require.id = user.id
            require.dateCreated = new Date()
            require.lastUpdated = new Date()
            require.save(flush: true, failOnError: true)
        }
        return require
    }
}
