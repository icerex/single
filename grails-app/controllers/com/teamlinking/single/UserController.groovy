package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO


class UserController {

    UserService userService

    def info(){
        long id = params.long('id',0)
        if (id == 0){
            id = flash.user.id
        }
        ResultVO resultVO = null
        def info = userService.getInfo(id)
        if (info){
            resultVO = ResultVO.ofSuccess(info.toJSON())
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER.code,BizErrorCode.NO_SUCH_USER.msg)
        }
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }

    }

}
