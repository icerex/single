package com.teamlinking.single

import com.teamlinking.single.vo.RelationChainVO

class RelationChainService {

    RelationChainVO add(long uid,String o, List<String> friends) {
        RelationChainVO relationChainVO = new RelationChainVO(
                uid: uid
        )
        friends.each {
            boolean isUpdate = false
            if (RelationChain.countByOwnerAndFriend(o, it) == 0){
                new RelationChain(
                        owner: o,
                        friend: it,
                        dateCreated: new Date()
                ).save(flush: true, failOnError: true)
                isUpdate = true
            }
            User user = User.findByMobilemd5(it)
            if (user && isUpdate){
                //todo 发现,圈子更新
            }
            relationChainVO.add(it,user?.register,user?.id)
        }
        return relationChainVO
    }
}
