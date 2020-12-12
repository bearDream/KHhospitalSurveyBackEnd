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
    public List<Object> getAllDepartment() {
        List<Department> list = buildTree(departmentDao.findAll());
        List<Object> menuList = menuTreeUtil.menuList(list);
        return menuList;
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
