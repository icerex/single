package com.teamlinking.single

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
}
