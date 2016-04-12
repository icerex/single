package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.enums.HeatType
import com.teamlinking.single.vo.HeatsVO

class HeatService {

    Heat add(long ownerId, long receiverId) {
        Heat heat = Heat.findByOwnerUidAndReceiverUid(ownerId, receiverId)
        boolean isLikeMe = true
        if (heat == null) {
            heat = Heat.findByOwnerUidAndReceiverUid(receiverId, ownerId)
        } else {
            isLikeMe = false
        }
        if (heat == null) {
            heat = new Heat(
                    dateCreated: new Date(),
                    lastUpdated: new Date(),
                    ownerUid: ownerId,
                    receiverUid: receiverId,
                    relation: HeatType.like.key
            ).save(flush: true, failOnError: true)
        } else if (isLikeMe) {
            heat.relation = HeatType.ing.key
            heat.lastUpdated = new Date()
            heat.edition = System.currentTimeMillis()
            heat = heat.save(flush: true, failOnError: true)
        }
        Recommend recommend = Recommend.findByStatusAndReceiverUidAndBeRecommendUid(1 as Byte, ownerId, receiverId)
        if (recommend) {
            recommend.status = 0 as Byte
            recommend.edition = System.currentTimeMillis()
            recommend.save(flush: true, failOnError: true)
        }
        return heat
    }

    HeatsVO like(long uid, long edition) {
        HeatsVO heatsVO = new HeatsVO(
                edition: System.currentTimeMillis()
        )
        List<Long> ids = Lists.newArrayList()
        Heat.findAllByOwnerUidAndRelationAndEditionGreaterThan(
                 uid, HeatType.like.key, edition).each {
            if (it.status == (0 as Byte)){
                heatsVO.invalid(it.receiverUid)
            }else {
                ids << it.receiverUid
            }
        }
        if (ids){
            UserInfo.findAllByStatusAndIdInList(1 as Byte,ids).each {
                heatsVO.add(it.id,it.version)
            }
        }
        return heatsVO
    }


    HeatsVO ing(long uid, long edition) {
        HeatsVO heatsVO = new HeatsVO(
                edition: System.currentTimeMillis()
        )
        List<Long> ids = Lists.newArrayList()
        List<Heat> list = Heat.createCriteria().list {
            eq("relation",HeatType.ing.key)
            gt("edition", edition)
            or {
                eq("ownerUid", uid)
                eq("receiverUid", uid)
            }
        }
        list.each {
            if (it.status == (0 as Byte)){
                heatsVO.invalid(it.receiverUid)
            }else {
                ids << it.receiverUid
            }
        }
        if (ids){
            UserInfo.findAllByStatusAndIdInList(1 as Byte,ids).each {
                heatsVO.add(it.id,it.version)
            }
        }
        return heatsVO
    }

    HeatsVO likeMe(long uid, long edition) {
        HeatsVO heatsVO = new HeatsVO(
                edition: System.currentTimeMillis()
        )
        List<Long> ids = Lists.newArrayList()
        Heat.findAllByReceiverUidAndRelationAndEditionGreaterThan(
                uid, HeatType.like.key, edition).each {
            if (it.status == (0 as Byte)){
                heatsVO.invalid(it.ownerUid)
            }else {
                ids << it.ownerUid
            }
        }
        if (ids){
            UserInfo.findAllByStatusAndIdInList(1 as Byte,ids).each {
                heatsVO.add(it.id,it.version)
            }
        }
        return heatsVO
    }
}
