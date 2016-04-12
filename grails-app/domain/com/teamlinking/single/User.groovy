package com.teamlinking.single

import com.alibaba.fastjson.JSONObject

class User {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //手机号码
    String mobile
    //手机号码MD5
    String mobilemd5
    //是否注册
    Byte register = 0 as Byte
    //登陆token
    String token
    //签名验证的盐
    String salt
    //登陆时间
    Date loginTime

    JSONObject toJSON(){
        JSONObject jsonObject = JSONObject.toJSON(this.properties)
        jsonObject.put("id",id)
        return jsonObject
    }

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        mobile nullable: false, blank: false
        mobilemd5 nullable: false, blank: false
        register inList: [1 as byte, 0 as byte]
        token nullable: true, blank: true
        salt nullable: true, blank: true
        loginTime nullable: true, blank: true
    }

    static mapping = {
        table('t_user')
        version(false)
        id generator: 'identity'
        mobile unique: true
        mobilemd5 unique: true
        token unique: true
    }
}
