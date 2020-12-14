package com.example.questionnaire.controller;

import com.example.questionnaire.service.DepartmentService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 查询科室树形结构
     * */
    @GetMapping("api/department/getDepartmentAll")
    public JSONObject getDepartment() {
        JSONObject departmentList = departmentService.getAllDepartment();
        return departmentList;
    }
}
