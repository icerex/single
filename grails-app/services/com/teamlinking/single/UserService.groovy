package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.vo.PageVO
import com.teamlinking.single.vo.UserInfoVO
import org.springframework.beans.BeanUtils

class UserService {

    User get(String mobile) {
        User.findByMobile(mobile)
    }

    UserInfo getInfo(long id){
        UserInfo info = UserInfo.get(id)
        if (info == null){
            User user = User.get(id)
            if (user == null){
                return null
            }
            info = new UserInfo()
            info.id = user.id
            info.dateCreated = new Date()
            info.lastUpdated = new Date()
            info.mobile = user.mobile
            info.save()

            user.lastUpdated = new Date()
            user.register = 1 as Byte
            user.save(flush: true, failOnError: true)
        }
        return info
    }

    PageVO<UserInfoVO> query(List<Long> ids){
        PageVO<UserInfoVO> pageVO = new PageVO<UserInfoVO>()
        List<UserInfoVO> list = Lists.newArrayList()
        UserInfo.findAllByIdInList(ids).each {
            UserInfoVO infoVO = new UserInfoVO()
            BeanUtils.copyProperties(it,infoVO)
            list << infoVO
        }
        pageVO.result = list
        pageVO.count = list.size()
        return pageVO
    }
}
