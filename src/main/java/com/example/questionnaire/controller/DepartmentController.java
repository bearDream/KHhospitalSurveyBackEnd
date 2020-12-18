package com.example.questionnaire.controller;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.service.DepartmentService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
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

    /**
     * 增加一条科室记录
     * */
    @PostMapping("/api/department/addDepartment")
    public JSONObject addDepartment(@RequestBody Department department){
        LocalDateTime time = LocalDateTime.now();
        department.setCreatedTime(time);
        department.setStartTime(time);
        JSONObject jsonRes = departmentService.addDepartment(department);
        return jsonRes;
    }
    /**
     * 根据Id删除科室
     * */
    @PostMapping("api/department/deleteById")
    public JSONObject deleteById(@Param("id") Integer id){
        JSONObject jsonRes = new JSONObject();
        try {
            departmentService.deleteById(id);
            jsonRes.put("success", true);
            jsonRes.put("msg", "删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "删除失败" + e);
        }
        return jsonRes;
    }

    /**
     * 根据id修改科室记录
     * */
    @PostMapping("/api/department/updateDepartment")
    public JSONObject updateDepartment(@RequestBody Department department){
        JSONObject jsonRes = new JSONObject();
        int count = departmentService.updateDepartment(department);
        if(count > 0){
            jsonRes.put("success", true);
            jsonRes.put("msg", "修改成功！");
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "修改失败！");
        }
        return jsonRes;
    }
}
