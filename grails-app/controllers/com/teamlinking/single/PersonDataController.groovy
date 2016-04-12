package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class PersonDataController {

    PersonDataService personDataService

    def query() {

        ResultVO resultVO = null

        String mobilemd5 = flash.user.mobilemd5

        resultVO = ResultVO.ofSuccess(personDataService.query(mobilemd5))

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def get() {
        String mobile = params."mobile" as String

        ResultVO resultVO = null
        if (mobile == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            PersonData personData = personDataService.get(mobile)
            if (personData) {
                resultVO = ResultVO.ofSuccess(personData.toJSON())
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
}
