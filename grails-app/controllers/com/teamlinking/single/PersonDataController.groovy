package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class PersonDataController {

    PersonDataService personDataService

    def query() {
        String mobiles = params."mobiles" as String

        ResultVO resultVO = null

        String mobilemd5 = flash.user.mobilemd5
        List<String> friend = Lists.newArrayList()
        if (mobiles) {
            mobiles.split(",").each {
                friend << it
            }
        }

        resultVO = ResultVO.ofSuccess(personDataService.query(mobilemd5,friend))

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
