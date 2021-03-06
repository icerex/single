package com.teamlinking.single.event

import com.google.common.eventbus.Subscribe
import com.teamlinking.single.PersonData
import com.teamlinking.single.Recommend
import com.teamlinking.single.RelationChain
import com.teamlinking.single.User

class UserRegisterListenerService {

    /**
     * 更新自己数据
     * @param event
     */
    @Subscribe
    void updateSelfData(UserRegisterEvent event) {
        PersonData personData = PersonData.findByMobilemd5(event.mobilemd5)
        if (personData){
            if(personData.register == (0 as Byte)){
                personData.register = (1 as Byte)
                personData.lastUpdated = new Date()
                personData.save(flush: true, failOnError: true)
            }else if(personData.status == (0 as Byte)) {
                personData.status = (1 as Byte)
                personData.lastUpdated = new Date()
                personData.save(flush: true, failOnError: true)
            }
        }else {
            personData = new PersonData(
                    dateCreated: new Date(),
                    lastUpdated: new Date(),
                    mobilemd5: event.mobilemd5,
                    register: 1 as Byte
            )
            personData.save(flush: true, failOnError: true)
        }
    }

    /**
     * 更新朋友的数据
     * @param event
     */
    @Subscribe
    void updateFriendsDataAndRecommend(UserRegisterEvent event){
        RelationChain.findAllByStatusAndFriend(1 as Byte,event.mobilemd5).each {
            PersonData personData = PersonData.findByMobilemd5(it.owner)
            if (personData){
                personData.totalSingle += 1
                personData.lastUpdated = new Date()
                personData.save(flush: true, failOnError: true)
            }
        }
    }

}
