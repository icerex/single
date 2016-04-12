package com.teamlinking.single

import com.alibaba.fastjson.JSONObject

class Photo {
    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated

    Long uid
    //图片地址
    String url
    //版本号
    Long edition = System.currentTimeMillis()

    JSONObject toJSON(){
        JSONObject jsonObject = JSONObject.toJSON(this.properties)
        jsonObject.put("id",id)
        return jsonObject
    }

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        uid nullable: false, blank: false
        url nullable: false, blank: false
        edition nullable: false, blank: false
    }

    static mapping = {
        table('t_photo')
        version(false)
        id generator: 'identity'
    }
}
