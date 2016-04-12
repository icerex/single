package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.vo.RecommendsVO

class RecommendService {

    RecommendsVO pull(long uid, long edition, List<Long> niUids) {
        RecommendsVO recommendsVO = new RecommendsVO(
                edition: System.currentTimeMillis()
        )
        List<Recommend> list = Recommend.createCriteria().list {
            if (niUids) {
                not { 'in'("beRecommendUid", niUids) }
            }
            eq("receiverUid", uid)
            gt("edition", edition)
        }
        List<Long> ids = Lists.newArrayList()
        list.each {
            if (it.status == (1 as Byte)) {
                recommendsVO.invalid(it.beRecommendUid)
            } else {
                ids << it.beRecommendUid
            }
        }
        if (ids) {
            UserInfo.findAllByStatusAndIdInList(1 as Byte, ids).each {
                recommendsVO.add(it.id, it.version)
            }
        }
        return recommendsVO
    }

    Recommend add(long receiverUid,long beRecommendUid,long recommendUid){
        Recommend recommend = Recommend.findByReceiverUidAndBeRecommendUid(receiverUid,beRecommendUid)
        if (recommend == null){
            recommend = new Recommend(
                    dateCreated: new Date(),
                    receiverUid: receiverUid,
                    beRecommendUid: beRecommendUid
            )
        }
        recommend.recommendUid = recommendUid
        recommend.edition = System.currentTimeMillis()
        recommend.lastUpdated = new Date()
        recommend = recommend.save(flush: true, failOnError: true)

        //todo 发消息

        return recommend
    }
}
