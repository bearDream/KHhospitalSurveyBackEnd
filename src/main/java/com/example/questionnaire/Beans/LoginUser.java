package com.example.questionnaire.Beans;

import com.example.questionnaire.model.User;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * @Description LoginUser
 * @Author zhangchi
 * @Date 12/21/20
 **/
@Data
public class LoginUser extends User implements UserDetails, Serializable {

    private Integer departmentId;

    private String department;
}
