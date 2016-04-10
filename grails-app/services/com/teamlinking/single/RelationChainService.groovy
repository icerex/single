package com.teamlinking.single

import com.teamlinking.single.vo.RelationChainVO

class RelationChainService {

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
            }
            User user = User.findByMobilemd5(it)
            relationChainVO.add(it,user?.register,user?.id)
        }
        return relationChainVO
    }
}
