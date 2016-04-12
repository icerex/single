package com.teamlinking.single

import com.teamlinking.single.vo.RecommendsVO

class RecommendService {

    RecommendsVO pull(long uid,long edition, List<Long> niUids) {
        RecommendsVO recommendsVO = new Recommend(
                edition: edition
        )
        Recommend.findAllByStatusAndReceiverUidAndBeRecommendUidNotInList(1 as Byte,uid,niUids).each {
            UserInfo info = UserInfo.get(it.beRecommendUid)
            if (info){
                recommendsVO.add(info.id,info.version)
            }
        }
        return recommendsVO
    }
}
