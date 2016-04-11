package com.teamlinking.single

import com.teamlinking.single.event.NotifyEventBusService
import com.teamlinking.single.event.UserRegisterEvent
import com.teamlinking.single.uitl.SecurityUtil

class LoginService {

    NotifyEventBusService notifyEventBusService

    User login(String mobile){
        User user = User.findByMobile(mobile)
        boolean isUpdate = false
        if(user == null){
            user = new User()
            user.dateCreated = new Date()
            user.mobile = mobile
            user.mobilemd5 = SecurityUtil.md5(mobile)
            isUpdate = true
        }
        user.token = SecurityUtil.generateTokon(mobile)
        user.salt = SecurityUtil.generateKey()
        user.lastUpdated = new Date()
        user.loginTime = new Date()
        user = user.save(flush: true, failOnError: true)
        if(isUpdate){
            //发现\圈子变更
            notifyEventBusService.post(new UserRegisterEvent(
                    uid: user.id,
                    mobilemd5: user.mobilemd5,
                    mobile: user.mobile
            ))
        }

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
