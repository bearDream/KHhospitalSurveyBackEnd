package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.DepartmentDao;
import com.example.questionnaire.model.Department;
import com.example.questionnaire.service.DepartmentService;
import com.example.questionnaire.utils.MenuTreeUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    final DepartmentDao departmentDao;

    @Resource
    MenuTreeUtil menuTreeUtil;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * 查询科室返回树形结构
     * */
    @Override
    public JSONObject getAllDepartment() {
        JSONObject jsonRes = new JSONObject();
        try {
            List<Department> list = departmentDao.findAll();

            if(list != null){
                List<Object> menuList  = menuTreeUtil.menuList(list);
                if(menuList != null){
                    jsonRes.put("success", true);
                    jsonRes.put("json", menuList);
                }else{
                    jsonRes.put("success", false);
                    jsonRes.put("msg", "查询出错！");
                }
            }else{
                jsonRes.put("success", false);
                jsonRes.put("msg", "查询出错！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "查询出错！" + e);
        }
        return jsonRes;
    }

    //将实体类，变成标准的树结构，即NewTree类型
    private List<Department> buildTree(List<Department> list){
        List<Department> newDepartments = new LinkedList<>();
        for(Department department:list){
            Department newDepartment = new Department();
            newDepartment.setId(department.getId());
            newDepartment.setParentId(department.getParentId());
            newDepartment.setDepName(department.getDepName());
            newDepartments.add(newDepartment);
        }
        return newDepartments;
    }
}
