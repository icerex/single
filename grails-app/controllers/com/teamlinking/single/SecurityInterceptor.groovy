package com.teamlinking.single

import com.alibaba.fastjson.JSONObject
import com.teamlinking.single.constants.BizErrorCode
import com.teamlinking.single.vo.ResultVO
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletResponse


class SecurityInterceptor {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(SecurityInterceptor.class)

    LoginService loginService

    public SecurityInterceptor() {
        matchAll().except(controller: ~/(nologin|error|notFound)/)
    }

    boolean before() {
        log.info("before authentication $controllerName $actionName")
        String token = request.getHeader("SINGLE_TOKEN")
        String sign = request.getHeader("SINGLE_SIGN")
        String timestamp = request.getHeader("TIME_STAMP")
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)) {
            log.info("authentication fail: header is null")
            ResultVO resultVO = ResultVO.ofFail(BizErrorCode.LOGIN_PARAM_NO_ERROR)
            responseOutWithJson(response,resultVO)
            return false
        }
        User user = loginService.check(token, sign, timestamp)
        if (user == null) {
            log.info("authentication fail,sign is error : token=" + token + ",sign=" + sign + ",timestamp=" + timestamp)
            ResultVO resultVO = ResultVO.ofFail(BizErrorCode.SESSION_TIME_OUT)
            responseOutWithJson(response,resultVO)
            return false
        }
        flash.user = user

        log.info(user.mobile + " authentication access ")
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

    void responseOutWithJson(HttpServletResponse response,
                             Object responseObject) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSONObject.toJSONString(responseObject));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
