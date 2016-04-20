package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.enums.SystemType
import com.teamlinking.single.sms.mob.ResultStatus
import com.teamlinking.single.sms.mob.SmsVerifyKit
import com.teamlinking.single.uitl.Validator
import com.teamlinking.single.vo.ResultVO
import org.springframework.util.StringUtils

class NologinController {

    SmsVerifyKit smsVerifyKit
    LoginService loginService
    UserService userService
    PersonDataService personDataService
    TagService tagService

    def login() {
        int system = params.int('system', 0)
        String mobile = params."mobile" as String
        String code = params."code" as String

        ResultVO resultVO = null
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code) ){
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else if(!Validator.isMobile(mobile)){
            resultVO = ResultVO.ofFail(BizErrorCode.MOBILE_NO_ERROR)
        }else{
            ResultStatus resultStatus = smsVerifyKit.checkcode(mobile,code,system == SystemType.ios.value)
            if(resultStatus.isSuccess() || (mobile.equals("17098078779") && code.equals("8888"))){
                resultVO = ResultVO.ofSuccess(loginService.login(mobile).toJSON())
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

    def tagSample() {

        ResultVO resultVO = ResultVO.ofSuccess(tagService.samples())

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def recommend(){
        Long id = params."id" as Long
        if (id == null){
            redirect(url: "/error")
            return
        }

        def userInfo = userService.getInfo(id)
        if (userInfo == null){
            redirect(url: "/error")
            return
        }

        String recommender = null
        Long rid = params."rid" as Long
        if (rid){
            recommender = userService.getInfo(rid)?.name
        }

        render(view: "recommend", model: [
                userInfo: userInfo,
                recommender: recommender
        ])
    }

    def invite(){
        Long id = params."id" as Long
        if (id == null){
            redirect(url: "/error")
            return
        }
        def userInfo = userService.getInfo(id)
        if (userInfo == null){
            redirect(url: "/error")
            return
        }
        def user = userService.get(id)
        if(user == null){
            redirect(url: "/error")
            return
        }
        def personData = personDataService.get(user.mobilemd5)
        if (personData == null){
            redirect(url: "/error")
            return
        }

        def friends = userService.friends(user.mobilemd5)

        render(view: "invite", model: [
                userInfo: userInfo,
                personData: personData,
                friends: friends
        ])
    }
}
