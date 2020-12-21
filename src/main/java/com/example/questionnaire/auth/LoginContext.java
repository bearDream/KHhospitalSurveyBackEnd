package com.example.questionnaire.auth;

import com.example.questionnaire.model.User;

/**
 * 当前登录用户信息获取的接口
 *
 * @author Beardream
 * @Date 2020/12/21 10:50
 */
public interface LoginContext {

    /**
     * 获取当前登录用户
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    User getUser();

    /**
     * 获取当前登录用户的token
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    String getToken();

    /**
     * 是否登录
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    boolean hasLogin();

    /**
     * 获取当前登录用户id
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    Long getUserId();

    /**
     * 判断当前用户是否是超级管理员
     */
    boolean isAdmin();


}
