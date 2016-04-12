package com.teamlinking.single

import com.teamlinking.single.storage.ImageResult
import com.teamlinking.single.storage.QiniuUpload
import com.teamlinking.single.uitl.MultipartFileUtil
import com.teamlinking.single.vo.AlbumVO
import org.springframework.web.multipart.MultipartFile

class PhotoService {

    QiniuUpload qiniuUpload

    AlbumVO pull(long uid,long edition) {
        UserInfo info = UserInfo.get(uid)
        if (info){
            AlbumVO albumVO = new AlbumVO(
                uid: uid,
                edition: edition
            )
            if (edition != info.albumVersion.longValue()) {
                Photo.findAllByUidAndEditionGreaterThan(uid,edition).each {
                    if (it.status.equals(1 as Byte)){
                        albumVO.add(it.id,it.url)
                    }else {
                        albumVO.invalid(it.id)
                    }
                }
                albumVO.edition = info.albumVersion.longValue()
            }
            return albumVO

        }
        return null
    }

    boolean addEnable(long uid){
        return Photo.countByStatusAndUid(1 as Byte, uid) < 20
    }

    Photo add(long uid, MultipartFile file){
        UserInfo info = UserInfo.get(uid)
        if (info) {
            String fileName = MultipartFileUtil.baseFileName(file.getOriginalFilename())
            ImageResult result = qiniuUpload.uploadImage(file.bytes, fileName)
            if (result) {
                Photo photo = new Photo(
                        uid: uid,
                        dateCreated: new Date(),
                        lastUpdated: new Date(),
                        url:qiniuUpload.getDomain() + result.key
                ).save()

                info.albumVersion = photo.edition
                info.lastUpdated = new Date()
                info.save(flush: true, failOnError: true)

                return photo
            }
        }
        return null
    }

    long invalid(long id,long uid){
        Photo photo = Photo.get(id)
        if (photo){
            if (photo.uid != uid){
                return -2
            }
            photo.edition = System.currentTimeMillis()
            photo.status = 0 as Byte
            photo.lastUpdated = new Date()
            photo.save()

            UserInfo info = UserInfo.get(photo.uid)
            info.albumVersion = photo.edition
            info.lastUpdated = new Date()
            info.save(flush: true, failOnError: true)
            return photo.edition
        }
        return -1
    }

}
