package com.teamlinking.single.constants

/**
 * Created by daniel on 15-7-8.
 */
enum BizErrorCode {


    SYSTEM_BUSY("系统繁忙，请稍后再试", 0),

    SESSION_TIME_OUT("登录会话失效", 1),

    CHECK_NO_ERROR("验证码错误", 2),

    PASSWORD_ERROR("密码错误", 3),

    PARAMS_ERROR("参数错误", 4),

    SEND_CHECKNO_FAILED("验证码已发送，请等待接收...", 5),

    LOGIN_PARAM_NO_ERROR("缺少登录参数", 6),

    SAVE_SELLER_FAILED("注册失败，请稍后再试", 7),

    NO_FOUND("请求错误", 10404),

    CANT_UPDATE_NOT_OWNER("无法更新，归属权限错误", 8),

    UPDATE_MATERIAL_FAILED("更新失败，请稍后重试", 10),

    NO_SUCH_USER("获取用户信息失败，请稍后重试", 11),

    PERMISSION_DENIED("操作权限校验失败，请稍后重试", 12),

    MOBILE_NO_ERROR("请输入正确的手机号码", 13),

    NO_SUCH_PHOTO("获取相片数据失败，请稍后重试", 14),

    UPLOAD_PHOTO_ERROR("上传相片失败，请稍后重试", 15),

    UPLOAD_PHOTO_MAX("最多只能上传20张相片", 16),

    NO_SUCH_TAG("获取标签数据失败，请稍后重试", 17),

    ADD_TAG_ERROR("新增标签失败，请稍后重试", 18),

    ADD_TAG_MAX("每人最多只能为一位添加5个标签", 19),

    ADD_ERROR("系统出错啦,是不能添加自己的哦", 20);

    // 成员变量
    private String msg;

    private int code;

    public static BizErrorCode getEnum(int value){
        for (BizErrorCode it : BizErrorCode.values()) {
            if (value == it.getCode()) {
                return it;
            }
        }
        return SYSTEM_BUSY;
    }

    // 构造方法
    private BizErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    String getMsg() {
        return msg
    }

    void setMsg(String msg) {
        this.msg = msg
    }

    int getCode() {
        return code
    }

    void setCode(int code) {
        this.code = code
    }
}
