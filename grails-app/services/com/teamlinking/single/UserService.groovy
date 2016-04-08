package com.teamlinking.single

import com.teamlinking.single.uitl.SecurityUtil
import com.teamlinking.single.vo.LoginResult
import grails.transaction.Transactional

@Transactional
class UserService {

    User get(String mobile) {
        User.findByMobile(mobile)
    }

    LoginResult login(String mobile){
        User user = get(mobile)
        if(user == null){
            user = new User()
            user.dateCreated = new Date()
            user.mobile = mobile
            user.mobilemd5 = SecurityUtil.md5(mobile)
        }
        user.token = SecurityUtil.generateTokon(mobile)
        user.key = SecurityUtil.generateKey()
        user.lastUpdated = new Date()
        user.loginDate = new Date()
        user = user.save(flush: true, failOnError: true)
        //todo 统计信息变更等其他操作


        def result = new LoginResult()
        result.key = user.key
        result.register = user.register
        result.token = user.token
        result.uid = user.id
        return result
    }
}
