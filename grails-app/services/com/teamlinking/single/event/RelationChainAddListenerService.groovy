package com.teamlinking.single.event

import com.google.common.eventbus.Subscribe
import com.teamlinking.single.PersonData
import com.teamlinking.single.Recommend
import com.teamlinking.single.RelationChain
import com.teamlinking.single.User

class RelationChainAddListenerService {

    /**
     * 更新自己数据
     * @param event
     */
    @Subscribe
    void updateSelfData(RelationChainAddEvent event) {
        PersonData personData = PersonData.findByMobilemd5(event.owner)
        if (personData) {
            personData.totalSingle += 1
        } else {
            personData = new PersonData(
                    dateCreated: new Date(),
                    mobilemd5: event.owner,
                    register: 1 as Byte,
                    totalSingle: 1
            )
        }
        personData.lastUpdated = new Date()
        personData.save(flush: true, failOnError: true)
    }

    /**
     * 增加发现|推荐
     * @param event
     */
    @Subscribe
    void addSelfRecommend(RelationChainAddEvent event) {
        RelationChain.findAllByStatusAndOwnerNotEqualAndFriend(1 as Byte,event.owner,event.friend).each {
            User user = User.findByMobilemd5(it.owner)
            if (user){
                Recommend recommend = Recommend.findByBeRecommendUidAndReceiverUid(event.ownerId,user.id)
                if (recommend == null){
                    new Recommend(
                            dateCreated: new Date(),
                            receiverUid: user.id,
                            beRecommendUid:  event.ownerId,
                            recommendUid: 0L
                    ).save(flush: true, failOnError: true)
                }
                recommend = Recommend.findByBeRecommendUidAndReceiverUid(user.id,event.ownerId)
                if (recommend == null){
                    new Recommend(
                            dateCreated: new Date(),
                            receiverUid: event.ownerId,
                            beRecommendUid:  user.id,
                            recommendUid: 0L
                    ).save(flush: true, failOnError: true)
                }
            }
        }
    }

    /**
     * 更新朋友数据
     * @param event
     */
    @Subscribe
    void updateFriendData(RelationChainAddEvent event) {
        PersonData personData = PersonData.findByMobilemd5(event.friend)
        if (personData) {
            personData.totalSingle += 1
        } else {
            personData = new PersonData(
                    dateCreated: new Date(),
                    mobilemd5: event.owner,
                    totalSingle: 1
            )

            User user = User.findByMobilemd5(event.friend)
            if (user) {
                personData.register = (1 as Byte)
            }
        }
        personData.lastUpdated = new Date()
        personData.save(flush: true, failOnError: true)

    }
}
