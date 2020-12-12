package com.example.questionnaire.dao;

import com.example.questionnaire.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentDao extends JpaRepository<Department, Integer> {
    List<Department> findAllByIdIs(Integer id);

    List<Department> findAll();
}
