package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class RelationChainController {

    RelationChainService relationChainService

    def add() {
        String mobiles = params."mobiles" as String

        long uid = flash.user.id
        String owner = flash.user.mobilemd5

        ResultVO resultVO = null
        if (mobiles == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            List<String> friends = Lists.newArrayList()
            mobiles.split(",").each {
                friends << it
            }
            resultVO = ResultVO.ofSuccess(relationChainService.add(uid,owner,friends))
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
