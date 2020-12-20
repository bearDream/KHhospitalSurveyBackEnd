package com.example.questionnaire.service;

import com.example.questionnaire.model.Department;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    JSONObject getAllDepartment();

    JSONObject deleteById(Integer id);

    JSONObject addDepartment(Department department);

    int updateDepartment(Department department);
}
