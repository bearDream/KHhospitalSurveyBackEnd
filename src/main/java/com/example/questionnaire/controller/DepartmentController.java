package com.example.questionnaire.controller;

import com.example.questionnaire.service.DepartmentService;
import net.sf.json.JSONObject;
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
    @PostMapping("api/department/getDepartmentAll")
    public JSONObject getDepartment() {
        JSONObject jsonRes = new JSONObject();
        List<Object> departmentList = departmentService.getAllDepartment();
        if(departmentList != null){
            jsonRes.put("success", true);
            jsonRes.put("json", departmentList);
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "查询出错！");
        }
        return jsonRes;
    }
}
