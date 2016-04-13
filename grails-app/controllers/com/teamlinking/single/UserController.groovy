package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO


class UserController {

    UserService userService

    def get(){
        long id = params.long('id',0)
        if (id == 0L){
            id = flash.user.id
        }
        ResultVO resultVO = null
        def info = userService.getInfo(id)
        if (info){
            resultVO = ResultVO.ofSuccess(info.toJSON())
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
        //姓名
        String name = params."name" as String
        //头像
        String avatar = params."avatar" as String
        //性别
        Byte sex = params."sex" as Byte
        //出生年月
        Long birthday = params."birthday" as Long
        //身高
        Integer height = params."height" as Integer
        //所在地 省
        String locationProv = params."locationProv" as String
        //所在地 市
        String locationCity = params."locationCity" as String
        //故乡 省
        String birthplaceProv = params."birthplaceProv" as String
        //故乡 市
        String birthplaceCity = params."birthplaceCity" as String
        //学历
        Integer degree = params."degree" as Integer
        //职业
        String job = params."job" as String
        //微信
        String wechat = params."wechat" as String

        ResultVO resultVO = null
        long id = flash.user.id
        def info = userService.getInfo(id)
        if (name){
            info.name = name
        }
        if (avatar){
            info.avatar = avatar
        }
        if (sex){
            info.sex = sex
        }
        if (birthday){
            info.birthday = new Date(birthday)
        }
        if (height){
            info.height = height
        }
        if (locationProv){
            info.locationProv = locationProv
        }
        if (locationCity){
            info.locationCity = locationCity
        }
        if (birthplaceProv){
            info.birthplaceProv = birthplaceProv
        }
        if (birthplaceCity){
            info.birthplaceCity = birthplaceCity
        }
        if (degree){
            info.degree = degree
        }
        if (job){
            info.job = job
        }
        if (wechat){
            info.wechat = wechat
        }
        info.lastUpdated = new Date()
        info.version = info.version + 1
        info.save(flush: true, failOnError: true)

        resultVO = ResultVO.ofSuccess(info.toJSON())
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def query(){
        String ids = params."ids" as String

        ResultVO resultVO = null
        if (ids == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            List<Long> idList = Lists.newArrayList()
            ids.split(",").each {
                idList << (it as Long)
            }
            resultVO = ResultVO.ofSuccess(userService.query(idList))
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def friends(){
        long id = params.long('id',0)
        def user = flash.user
        if (id != 0L){
            user = userService.get(id)
        }
        ResultVO resultVO = null
        if (user){
            resultVO = ResultVO.ofSuccess(userService.friends(user.mobilemd5))
        }else {
            resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER)
        }
        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

    def contact(){
        Long id = params."id" as Long

        ResultVO resultVO = null
        if (id == null) {
            resultVO = ResultVO.ofFail(BizErrorCode.PARAMS_ERROR)
        }else {
            long ownerId = flash.user.id
            String ownerMd5 = flash.user.mobilemd5
            Boolean contact = userService.contact(ownerId,ownerMd5,id)
            if (contact == null){
                resultVO = ResultVO.ofFail(BizErrorCode.NO_SUCH_USER)
            }else {
                resultVO = ResultVO.ofSuccess(contact)
            }
        }

        withFormat {
            json {
                render text: resultVO.toJSONString(), contentType: 'application/json;', encoding: "UTF-8"
            }
        }
    }

}
