import com.teamlinking.single.sms.mob.SmsVerifyKit
import grails.util.Environment

// Place your Spring DSL code here
beans = {

    switch (Environment.current) {
        case Environment.PRODUCTION:
            break
        case Environment.DEVELOPMENT:
        case Environment.TEST:
            break
    }

    smsVerifyKit(SmsVerifyKit){
        address = "https://webapi.sms.mob.com/sms/verify"
        iosAppkey = "10fbbbbe5def5"
        andoridAppkey = "10fbfd24fa4fb"
    }

}
