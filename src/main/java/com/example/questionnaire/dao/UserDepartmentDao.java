package com.example.questionnaire.dao;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDepartmentDao extends JpaRepository<UserDepartment, Integer> {
    List<UserDepartment> findAllByIdIs(Integer id);
}
