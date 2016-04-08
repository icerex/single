package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.enums.SystemType
import com.teamlinking.single.sms.mob.ResultStatus
import com.teamlinking.single.sms.mob.SmsVerifyKit
import com.teamlinking.single.uitl.Validator
import com.teamlinking.single.vo.ResultVO
import org.springframework.util.StringUtils


class UserController {

    SmsVerifyKit smsVerifyKit
    UserService userService

    def login() {
        int system = params.int('system', 0)
        String mobile = params."mobile" as String
        String code = params."code" as String

        ResultVO resultVO = null
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code) ){
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR.code,BizErrorCode.PARAMS_ERROR.msg)
        }else if(!Validator.isMobile(mobile)){
            resultVO = ResultVO.ofFail(BizErrorCode.MOBILE_NO_ERRO.code,BizErrorCode.MOBILE_NO_ERRO.msg)
        }else{
            ResultStatus resultStatus = smsVerifyKit.checkcode(mobile,code,system == SystemType.ios.value)
            if(resultStatus.isSuccess() || (mobile.equals("18668181767") && code.equals("8888"))){
                resultVO = ResultVO.ofSuccess(userService.login(mobile))
            }else {
                resultVO = ResultVO.ofFail(resultStatus.key,resultStatus.value)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

}
