package com.teamlinking.single

import com.google.common.collect.Lists
import com.teamlinking.single.enums.HeatType
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
            user.register = (1 as Byte)
            user.save(flush: true, failOnError: true)
        }
        return info
    }

    PageVO<UserInfoVO> query(List<Long> ids){
        PageVO<UserInfoVO> pageVO = new PageVO<UserInfoVO>()
        List<UserInfoVO> list = Lists.newArrayList()
        if (ids.size() > 0) {
            UserInfo.findAllByIdInList(ids).each {
                UserInfoVO infoVO = new UserInfoVO()
                BeanUtils.copyProperties(it, infoVO)
                list << infoVO
            }
        }
        pageVO.result = list
        pageVO.count = list.size()
        return pageVO
    }


    PageVO<UserInfoVO> friends(long id){
        User user = User.get(id)
        if (user) {
            List<String> md5s = Lists.newArrayList()
            RelationChain.findAllByStatusAndOwner(1 as Byte,user.mobilemd5).each {
                md5s << it.friend
            }
            List<Long> ids = Lists.newArrayList()
            if (md5s.size() > 0){
                User.findAllByStatusAndMobilemd5InList(1 as Byte,md5s)
            }
            return query(ids)
        }
        return null
    }

    Boolean contact(long ownerId,String ownerMd5,long otherId){
        User other = User.get(otherId)
        if (other == null){
            return null
        }
        if (RelationChain.findByOwnerAndFriend(ownerMd5,other.mobilemd5)){
            return true
        }
        if(Heat.findByStatusAndOwnerUidAndReceiverUidAndRelation(1 as Byte,ownerId,otherId,HeatType.ing.key)){
            return true
        }
        if(Heat.findByStatusAndOwnerUidAndReceiverUidAndRelation(1 as Byte,otherId,ownerId,HeatType.ing.key)){
            return true
        }
        return false
    }
}
