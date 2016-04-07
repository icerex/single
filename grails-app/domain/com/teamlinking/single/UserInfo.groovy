package com.teamlinking.single

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

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        mobile nullable: false, blank: false
        version nullable: false, blank: false
        tagVersion nullable: false, blank: false
        albumVersion nullable: false, blank: false
    }

    static mapping = {
        table('t_user_info')
        id generator: 'assigned'
    }
}
