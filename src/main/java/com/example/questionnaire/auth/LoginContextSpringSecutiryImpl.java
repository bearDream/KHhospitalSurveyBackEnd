package com.example.questionnaire.auth;

import com.example.questionnaire.Beans.LoginUser;
import com.example.questionnaire.enu.AuthExceptionEnum;
import com.example.questionnaire.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author zhangchi
 * @Date 12/21/20
 **/
@Component
public class LoginContextSpringSecutiryImpl implements LoginContext  {

    @Override
    public LoginUser getUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            throw new AuthException(AuthExceptionEnum.NOT_LOGIN_ERROR);
        } else {
            return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public boolean hasLogin() {
        return false;
    }

    @Override
    public Long getUserId() {
        return null;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
}
