package com.teamlinking.single

import com.alibaba.fastjson.JSONObject
import com.google.common.collect.Lists
import com.teamlinking.single.enums.SexType
import com.teamlinking.single.push.apicloud.Push
import com.teamlinking.single.vo.RecommendsVO

class RecommendService {

    Push push

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

        //发消息
        UserInfo info = UserInfo.get(recommendUid)
        def map = [
                alert :info.name+ "为你推荐了个"+ SexType.getEnum(info.sex)+",赶紧去看吧",
                redirectType: 1,
                redirectContent:beRecommendUid
        ]
        User user = User.get(receiverUid)
        push.pushMessage("新推荐",JSONObject.toJSONString(map),1,0,"",user.mobilemd5)

        return recommend
    }
}
