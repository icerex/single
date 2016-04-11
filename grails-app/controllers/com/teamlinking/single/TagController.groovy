package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO

class TagController {

    TagService tagService

    def pull() {
        long edition = params.long('edition',0)
        long uid = params.long('uid',0)
        if (uid == 0L){
            uid = flash.user.id
        }
        ResultVO resultVO = null
        def tagsVO = tagService.pull(uid,edition)
        if (tagsVO){
            resultVO = ResultVO.ofSuccess(tagsVO)
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_TAG)
        }
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }

    }

    def add() {
        Byte type = params.getByte("type",0)
        Long uid = params."uid" as Long
        String content = params."content" as String

        long operatorUid = flash.user.id
        ResultVO resultVO = null

        if (uid == null || content == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else if (tagService.addEnable(uid,operatorUid)){
            def tag = tagService.add(type,uid,content,operatorUid)
            if (tag){
                resultVO = ResultVO.ofSuccess(tag.toJSON())
            }else {
                resultVO = ResultVO.ofFail(BizErrorCode.ADD_TAG_ERROR)
            }
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.ADD_TAG_MAX)
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def delete() {
        long id = params.long('id',0)
        String ids = params."ids" as String
        ResultVO resultVO = null
        if (id == 0L && ids == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            long uid = flash.user.id
            List<Long> idList = Lists.newArrayList()
            if (id > 0L){
                idList << id
            }else if (ids != null){
                ids.split(",").each {
                    idList << (it as Long)
                }
            }
            long edition = 0
            idList.each {
                edition = tagService.invalid(it,uid)
            }
            if (edition > 0){
                resultVO = ResultVO.ofSuccess(edition)
            }else if (edition == -2L){
                resultVO = ResultVO.ofFail(BizErrorCode.PERMISSION_DENIED)
            }else {
                resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_TAG)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
