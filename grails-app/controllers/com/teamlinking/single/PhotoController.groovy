package com.teamlinking.single

import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.uitl.MultipartFileUtil
import com.teamlinking.single.vo.ResultVO
import org.springframework.web.multipart.MultipartFile

class PhotoController {

    PhotoService photoService

    def pull() {
        long edition = params.long('edition',0)
        long uid = params.long('uid',0)
        if (uid == 0L){
            uid = flash.user.id
        }
        ResultVO resultVO = null
        def album = photoService.pull(uid,edition)
        if (album){
            resultVO = ResultVO.ofSuccess(album)
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_PHOTO)
        }
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }

    }

    def upload() {
        MultipartFile photoFile = params."photoFile" as MultipartFile
        if (photoFile == null ){
            photoFile = MultipartFileUtil.getMultipartFile(request,"photoFile")
        }
        ResultVO resultVO = null
        long uid = flash.user.id
        if (photoFile == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else if (photoService.addEnable(uid)){
            def photo = photoService.add(uid,photoFile)
            if (photo){
                resultVO = ResultVO.ofSuccess(photo.toJSON())
            }else {
                resultVO = ResultVO.ofFail(BizErrorCode.UPLOAD_PHOTO_ERROR)
            }
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.UPLOAD_PHOTO_MAX)
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def delete() {
        long id = params.long('id',0)
        ResultVO resultVO = null
        if (id == 0L) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            long edition = photoService.invalid(id)
            if (edition > 0){
                resultVO = ResultVO.ofSuccess(edition)
            }else {
                resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_PHOTO)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }
}
