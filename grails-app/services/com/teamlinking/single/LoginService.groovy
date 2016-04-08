package com.teamlinking.single

import com.teamlinking.single.uitl.SecurityUtil

import grails.transaction.Transactional

@Transactional
class LoginService {

    User login(String mobile){
        User user = User.findByMobile(mobile)
        if(user == null){
            user = new User()
            user.dateCreated = new Date()
            user.mobile = mobile
            user.mobilemd5 = SecurityUtil.md5(mobile)
        }
        user.token = SecurityUtil.generateTokon(mobile)
        user.salt = SecurityUtil.generateKey()
        user.lastUpdated = new Date()
        user.loginTime = new Date()
        user = user.save(flush: true, failOnError: true)
        //todo 统计信息变更等其他操作


        return user
    }

    User check(String token,String sign,String timestamp){
        User user = User.findByToken(token)
        if (user){
            try {
                long time = Long.parseLong(timestamp)
                if (SecurityUtil.checkSign(sign,time,user.salt)){
                    return user
                }
            }catch (Exception e){
                return null
            }
        }
        return null
    }
}
