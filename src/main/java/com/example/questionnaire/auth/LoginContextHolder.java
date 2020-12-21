package com.example.questionnaire.auth;


import com.example.questionnaire.config.SpringContextHolder;

/**
 * 当前登录用户信息获取的接口
 *
 * @author fengshuonan
 * @Date 2020/12/21
 */
public class LoginContextHolder {

    public static LoginContext getContext() {
        return SpringContextHolder.getBean(LoginContext.class);
    }

}
