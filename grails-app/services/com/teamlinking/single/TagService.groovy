package com.teamlinking.single

import com.teamlinking.single.vo.TagsVO

class TagService {

    TagsVO pull(long uid, long edition) {
        UserInfo info = UserInfo.get(uid)
        if (info) {
            TagsVO tagsVO = new TagsVO(
                    uid: uid,
                    edition: edition
            )
            if (edition != info.tagVersion.longValue()) {
                Tag.findAllByUidAndEditionGreaterThanEquals(uid, edition).each {
                    if (it.status.equals(1 as Byte)) {
                        tagsVO.add(it.id, it.type, it.uid, it.content, it.operatorUid)
                    } else {
                        tagsVO.invalid(it.id)
                    }
                }
                tagsVO.edition = info.tagVersion.longValue()
            }
            return tagsVO

        }
        return null
    }

    boolean addEnable(long uid, long operatorUid) {
        return Tag.countByStatusAndUidAndOperatorUid(1 as Byte, uid, operatorUid) < 5
    }

    Tag add(Byte type, long uid,String content,long operatorUid){
        UserInfo info = UserInfo.get(uid)
        if (info) {
            Tag tag = new Tag(
                    dateCreated: new Date(),
                    lastUpdated: new Date(),
                    type: type,
                    uid: uid,
                    content: content,
                    operatorUid: operatorUid
            ).save()

            info.tagVersion = tag.edition
            info.lastUpdated = new Date()
            info.save(flush: true, failOnError: true)
            return tag
        }
        return null
    }

    long invalid(long id,long uid){
        Tag tag = Tag.get(id)
        if (tag){
            if (tag.uid != uid){
                return -2
            }
            tag.edition = System.currentTimeMillis()
            tag.status = 0 as Byte
            tag.lastUpdated = new Date()
            tag.save()

            UserInfo info = UserInfo.get(tag.uid)
            info.tagVersion = tag.edition
            info.lastUpdated = new Date()
            info.save(flush: true, failOnError: true)
            return tag.edition
        }
        return -1
    }
}
