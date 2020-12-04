package com.example.questionnaire.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author zhangchi
 * @Date 12/4/20
 **/
@Data
@Entity(name = "user_department")
public class UserDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer depId;
    private LocalDateTime createdTime;
}
