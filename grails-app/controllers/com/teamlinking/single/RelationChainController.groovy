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
            List<String> friend = Lists.newArrayList()
            mobiles.split(",").each {
                friend << it
            }
            resultVO = ResultVO.ofSuccess(relationChainService.add(uid,owner,friend))
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def common(){
        long uid = params.long("uid",0)

        ResultVO resultVO = null
        if (uid == 0L) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            String me = flash.user.mobilemd5
            def list = relationChainService.common(me,uid)
            if (list == null){
                resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER)
            }else {
                resultVO = ResultVO.ofSuccess(list)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
