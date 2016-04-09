package com.teamlinking.single

class Tag {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //标签类型
    Byte type = 0 as Byte
    //标签所与人
    Long uid
    //标签内容
    String content
    //打标人
    Long operatorUid
    //版本号
    Long edition = System.currentTimeMillis()

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        uid nullable: false, blank: false
        type nullable: false, blank: false
        content nullable: false, blank: false
        operatorUid nullable: false, blank: false
        edition nullable: false, blank: false
    }

    static mapping = {
        table('t_tag')
        version(false)
        id generator: 'identity'
    }
}
