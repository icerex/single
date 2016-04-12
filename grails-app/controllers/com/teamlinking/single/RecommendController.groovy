package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.vo.ResultVO

class RecommendController {

    RecommendService recommendService

    def pull() {
        long edition = params.long('edition',0)
        String niUids = params."niUids" as String

        long uid = flash.user.id

        ResultVO resultVO = null
        List<Long> niUidList = Lists.newArrayList()
        if (niUids) {
            niUids.split(",").each {
                niUidList << (it as Long)
            }
        }

        resultVO = ResultVO.ofSuccess(recommendService.pull(uid,edition,niUidList))

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
