import com.teamlinking.single.push.apicloud.Push
import com.teamlinking.single.sms.mob.SmsVerifyKit
import com.teamlinking.single.storage.QiniuUpload
import grails.util.Environment
import org.springframework.web.multipart.commons.CommonsMultipartResolver

// Place your Spring DSL code here
beans = {

    switch (Environment.current) {
        case Environment.PRODUCTION:
            break
        case Environment.DEVELOPMENT:
        case Environment.TEST:
            break
    }

    qiniuUpload(QiniuUpload){
        accessKey = "EacROcMBQ0iTbhxN4B5q5n4Qtvafx4qelGaFDpm6"
        secretKey = "ZfD33BUE3CDP9HcLSulF2hBtW_tiTPWfjOrWnEdi"
        bucket = "teamlinking"
    }

    smsVerifyKit(SmsVerifyKit){
        address = "https://webapi.sms.mob.com/sms/verify"
        iosAppkey = "10fbbbbe5def5"
        andoridAppkey = "10fbfd24fa4fb"
    }

    push(Push){
        appId = "A6904694668561"
        appKey = "7CD66B9F-EA16-9512-677C-B3DE807D57A1"
        domain = "https://p.apicloud.com"
    }

    multipartResolver(CommonsMultipartResolver) {
        maxUploadSize = 20485760
//        defaultEncoding = "UTF-8"
    }
}
