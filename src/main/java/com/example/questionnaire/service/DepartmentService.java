package com.example.questionnaire.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Object> getAllDepartment();
}
