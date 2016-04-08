package com.teamlinking.single

import com.alibaba.fastjson.JSONObject

class Require {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //年龄 小
    String minAge
    //年龄 大
    String maxAge
    //身高 小
    String minHeight
    //身高 大
    String maxHeight
    //所在地 省
    String locationProv
    //所在地 市
    String locationCity
    //学历区间
    Integer degreeRange
    //版本号
    Long version = 0

    JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject()
        jsonObject.putAll(this.properties)
        jsonObject.putAll("id",id)
        return jsonObject
    }

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        version nullable: false, blank: false
        lastUpdated nullable: true, blank: true
        minAge nullable: true, blank: true
        maxAge nullable: true, blank: true
        minHeight nullable: true, blank: true
        maxHeight nullable: true, blank: true
        locationProv nullable: true, blank: true
        locationCity nullable: true, blank: true
        degreeRange nullable: true, blank: true
    }

    static mapping = {
        table('t_require')
        id generator: 'assigned'
    }
}
