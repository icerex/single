package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.event.NotifyEventBusService
import com.teamlinking.single.event.RelationChainAddEvent
import com.teamlinking.single.vo.RelationChainVO

class RelationChainService {

    NotifyEventBusService notifyEventBusService

    RelationChainVO add(long uid,String o, List<String> friends) {
        RelationChainVO relationChainVO = new RelationChainVO(
                uid: uid
        )
        friends.each {
            if (RelationChain.countByOwnerAndFriend(o, it) == 0){
                new RelationChain(
                        owner: o,
                        friend: it,
                        dateCreated: new Date()
                ).save(flush: true, failOnError: true)
                //发现,圈子更新
                notifyEventBusService.post(new RelationChainAddEvent(
                        ownerId: uid,
                        owner: o,
                        friend: it
                ))
            }
            User user = User.findByMobilemd5(it)
            relationChainVO.add(it,user?.register,user?.id)
        }
        return relationChainVO
    }

    List<String> common(String owner,long friendId){
        User friend = User.get(friendId)
        if (friend){
            List<String> list = Lists.newArrayList()
            List<RelationChain> orc = RelationChain.findAllByStatusAndOwner(1 as Byte,owner)
            List<RelationChain> frc = RelationChain.findAllByStatusAndOwner(1 as Byte,friend.mobilemd5)

            orc.each {
                String common = it.friend
                for(RelationChain rc: frc) {
                    if (common.equals(rc.friend)){
                        list << common
                        break
                    }
                }
            }
            return list
        }
        return null
    }
}
