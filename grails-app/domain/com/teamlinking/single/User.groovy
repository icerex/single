package com.teamlinking.single

class User {

    Long id

    Byte status = 1 as Byte

    Date dateCreated

    Date lastUpdated
    //手机号码
    String mobile
    //手机号码MD5
    String mobilemd5
    //登陆token
    String token
    // 秘钥
    String key
    //登陆时间
    Date loginDate
    //发现版本
    Long findVersion = 0
    //圈子版本
    Long coterieVersion = 0
    //我想认识>>版本
    Long gtVersion = 0
    //交往中==版本
    Long eqVersion = 0
    //想认识我<<版本
    Long ltVersion = 0

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        dateCreated nullable: false, blank: false
        mobile nullable: false, blank: false
        mobilemd5 nullable: false, blank: false
        findVersion nullable: false, blank: false
        coterieVersion nullable: false, blank: false
        gtVersion nullable: false, blank: false
        eqVersion nullable: false, blank: false
        ltVersion nullable: false, blank: false
    }

    static mapping = {
        table('t_user')
        version(false)
        id generator: 'identity'
    }
}
