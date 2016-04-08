package com.teamlinking.single

import com.alibaba.fastjson.JSONObject

class UserInfo {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //手机号码
    String mobile
    //姓名
    String name
    //头像
    String avatar
    //性别
    Byte sex
    //出生年月
    Date birthday
    //身高
    Integer height
    //所在地 省
    String locationProv
    //所在地 市
    String locationCity
    //故乡 省
    String birthplaceProv
    //故乡 市
    String birthplaceCity
    //学历
    Integer degree
    //职业
    String job
    //微信
    String wechat
    //版本号
    Long version = 0
    //标签版本号
    Long tagVersion = 0
    //相册版本号
    Long albumVersion = 0

    JSONObject toJSON(){
        JSONObject jsonObject = JSONObject.toJSON(this.properties)
        jsonObject.put("id",id)
        jsonObject.put("version",version)
        return jsonObject
    }

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        mobile nullable: false, blank: false
        version nullable: false, blank: false
        tagVersion nullable: false, blank: false
        albumVersion nullable: false, blank: false
        name nullable: true, blank: true
        avatar nullable: true, blank: true
        sex nullable: true, blank: true
        birthday nullable: true, blank: true
        height nullable: true, blank: true
        locationProv nullable: true, blank: true
        locationCity nullable: true, blank: true
        birthplaceProv nullable: true, blank: true
        birthplaceCity nullable: true, blank: true
        degree nullable: true, blank: true
        job nullable: true, blank: true
        wechat nullable: true, blank: true
    }

    static mapping = {
        table('t_user_info')
        id generator: 'assigned'
        mobile unique: true
    }
}
