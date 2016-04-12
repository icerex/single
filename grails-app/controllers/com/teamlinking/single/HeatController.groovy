package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class HeatController {

    HeatService heatService

    def add() {
        Long uid = params."uid" as Long
        long ownerUid = flash.user.id
        ResultVO resultVO = null

        if (uid == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else if (uid == ownerUid) {
            resultVO = ResultVO.ofFail(BizErrorCode.ADD_ERROR)
        }else{
            def heat = heatService.add(ownerUid,uid)
            if (heat){
                resultVO = ResultVO.ofSuccess(heat.toJSON())
            }else {
                resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def like(){
        long edition = params.long('edition',0)
        long uid = flash.user.id
        ResultVO resultVO = null

        resultVO = ResultVO.ofSuccess(heatService.like(uid,edition))

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def ing(){
        long edition = params.long('edition',0)
        long uid = flash.user.id
        ResultVO resultVO = null

        resultVO = ResultVO.ofSuccess(heatService.ing(uid,edition))

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def likeMe(){
        long edition = params.long('edition',0)
        long uid = flash.user.id
        ResultVO resultVO = null

        resultVO = ResultVO.ofSuccess(heatService.likeMe(uid,edition))

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
