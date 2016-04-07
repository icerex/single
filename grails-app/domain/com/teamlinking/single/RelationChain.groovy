package com.teamlinking.single

class RelationChain {
    Long id

    Byte status = 1 as Byte

    Date dateCreated
    //主人(手机号码MD5)
    String owner
    //朋友(手机号码MD5)
    String friend

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        owner nullable: false, blank: false
        friend nullable: false, blank: false
    }

    static mapping = {
        table('t_relation_chain')
        version(false)
        id generator: 'identity'
    }
}
