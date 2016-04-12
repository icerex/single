package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.vo.PersonDataVO
import org.springframework.beans.BeanUtils

class PersonDataService {

    List<PersonDataVO> query(String mobilemd5,List<String> friends) {
        if (friends == null || friends.size() == 0) {
            friends = Lists.newArrayList()
            RelationChain.findAllByStatusAndOwner(1 as Byte, mobilemd5).each {
                friends << it.friend
            }
        }
        List<PersonDataVO> list = Lists.newArrayList()
        PersonData.findAllByStatusAndMobilemd5InList(1 as Byte,friends).each {
            PersonDataVO personDataVO = new PersonData()
            BeanUtils.copyProperties(it,personDataVO)
            list << personDataVO
        }
        return list
    }

    PersonData get(String mobilemd5){
        PersonData.findByMobilemd5(mobilemd5)
    }
}
