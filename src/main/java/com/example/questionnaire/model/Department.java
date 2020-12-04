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
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String depName;
    private Integer parentId;
    private LocalDateTime startTime;
}
