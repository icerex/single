package com.teamlinking.single

import com.alibaba.fastjson.JSONObject

class PersonData {
    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //手机号码MD5
    String mobilemd5
    //是否注册
    Byte register = 0 as Byte
    //单身朋友总数
    Integer totalSingle = 0
    //总牵线次数
    Integer totalRecommend = 0
    //版本号
    Long version = 0

    JSONObject toJSON(){
        JSONObject jsonObject = JSONObject.toJSON(this.properties)
        jsonObject.put("id",id)
        jsonObject.put("version",version)
        return jsonObject
    }

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        register inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        mobilemd5 nullable: false, blank: false
        register inList: [1 as byte, 0 as byte]
        version nullable: false, blank: false
    }

    static mapping = {
        table('t_person_data')
        id generator: 'identity'
        mobilemd5 unique: true
    }
}
