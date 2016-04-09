package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class RequireController {

    RequireService requireService

    def get() {
        long id = params.long('id',0)
        if (id == 0L){
            id = flash.user.id
        }
        ResultVO resultVO = null
        def require = requireService.get(id)
        if (require){
            resultVO = ResultVO.ofSuccess(require.toJSON())
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER)
        }
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def post(){
        //年龄 小
        Integer minAge = params."minAge" as Integer
        //年龄 大
        Integer maxAge = params."maxAge" as Integer
        //身高 小
        Integer minHeight = params."minHeight" as Integer
        //身高 大
        Integer maxHeight = params."maxHeight" as Integer
        //所在地 省
        String locationProv = params."locationProv" as String
        //所在地 市
        String locationCity = params."locationCity" as String
        //学历区间
        Integer degreeRange = params."degreeRange" as Integer

        ResultVO resultVO = null
        long id = flash.user.id
        def require = requireService.get(id)
        if (minAge){
            require.minAge = minAge
        }
        if (maxAge){
            require.maxAge = maxAge
        }
        if (minHeight){
            require.minHeight = minHeight
        }
        if (maxHeight){
            require.maxHeight = maxHeight
        }
        if (locationProv){
            require.locationProv = locationProv
        }
        if (locationCity){
            require.locationCity = locationCity
        }
        if (degreeRange){
            require.degreeRange = degreeRange
        }
        require.lastUpdated = new Date()
        require.version = require.version + 1
        require.save(flush: true, failOnError: true)

        resultVO = ResultVO.ofSuccess(require.toJSON())
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
