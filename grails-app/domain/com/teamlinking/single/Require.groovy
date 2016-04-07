package com.teamlinking.single

class Require {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated

    Long uid
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

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        uid nullable: false, blank: false
        version nullable: false, blank: false
    }

    static mapping = {
        table('t_require')
        id generator: 'identity'
    }
}
