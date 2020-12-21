package com.example.questionnaire.auth;

import com.example.questionnaire.enu.AbstractBaseExceptionEnum;
import lombok.Data;

/**
 * @Description TODO
 * @Author zhangchi
 * @Date 12/21/20
 **/
@Data
public class AuthException extends RuntimeException {

    private Integer code;
    private String errorMessage;

    public AuthException() {
        super("认证失败！");
        this.code = 500;
        this.errorMessage = "认证失败！";
    }

    public AuthException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

}