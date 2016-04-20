package com.teamlinking.single

class TagSample {

    Long id

    Byte status = 1 as Byte
    //标签内容
    String content

    static constraints = {
        status inList: [1 as byte, 0 as byte]
        content nullable: false, blank: false
    }

    static mapping = {
        table('t_sample_tag')
        version(false)
        id generator: 'identity'
        content unique: true
    }
}
