package com.teamlinking.single

class Recommend {
    Long id

    Byte status = 1 as Byte

    Date dateCreated
    //接收方uid
    Long receiverUid
    //被推荐人uid
    Long beRecommendUid
    //推荐人uid
    Long recommendUid
    //版本号
    Long edition = System.currentTimeMillis()

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        receiverUid nullable: false, blank: false
        beRecommendUid nullable: false, blank: false
        recommendUid nullable: false, blank: false
        edition nullable: false, blank: false
    }

    static mapping = {
        table('t_recommend')
        version(false)
        id generator: 'identity'
    }
}
