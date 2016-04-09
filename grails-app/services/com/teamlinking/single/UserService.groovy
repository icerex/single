package com.teamlinking.single

class UserService {

    User get(String mobile) {
        User.findByMobile(mobile)
    }

    UserInfo getInfo(long id){
        UserInfo info = UserInfo.get(id)
        if (info == null){
            User user = User.get(id)
            if (user == null){
                return null
            }
            info = new UserInfo()
            info.id = user.id
            info.dateCreated = new Date()
            info.lastUpdated = new Date()
            info.mobile = user.mobile
            info.save(flush: true, failOnError: true)
        }
        return info
    }
}
