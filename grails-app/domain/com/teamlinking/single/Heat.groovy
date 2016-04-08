package com.teamlinking.single

class Heat {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //发起方UID
    Long ownerUid
    //接收方UID
    Long receiverUid
    //关系 >>  , ==
    Byte relation = 0
    //版本号
    Long version = System.currentTimeMillis()

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        lastUpdated nullable: false, blank: false
        ownerUid nullable: false, blank: false
        receiverUid nullable: false, blank: false
        relation inList: [1 as byte, 0 as byte]
    }

    static mapping = {
        table('t_heat')
        version(false)
        id generator: 'identity'
    }
}