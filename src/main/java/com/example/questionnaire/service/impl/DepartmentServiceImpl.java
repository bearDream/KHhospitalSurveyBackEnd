package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.DepartmentDao;
import com.example.questionnaire.model.Department;
import com.example.questionnaire.service.DepartmentService;
import com.example.questionnaire.utils.MenuTreeUtil;
import net.sf.json.JSON;
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

    @Override
    public JSONObject deleteById(Integer id) {
        JSONObject jsonRes = new JSONObject();
        try {
            List<Department> list = departmentDao.findAllByParentId(id);
            if(list != null && list.size() > 0){
                int count = departmentDao.removeByParentId(id);
                if(count > 0){
                    departmentDao.deleteById(id);
                    jsonRes.put("success", true);
                    jsonRes.put("msg", "删除成功！");
                }else{
                    jsonRes.put("success", false);
                    jsonRes.put("msg", "删除失败!" );
                }
            }else{
                departmentDao.deleteById(id);
                jsonRes.put("success", true);
                jsonRes.put("msg", "删除成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "删除失败!" + e );
        }
        return jsonRes;
    }

    @Override
    public JSONObject addDepartment(Department department) {
        JSONObject jsonRes = new JSONObject();
        try {
            Department dep = departmentDao.save(department);
            jsonRes.put("dep", dep);
            jsonRes.put("success", true);
            jsonRes.put("msg", "增加成功！");
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "增加失败" + e);
        }
        return jsonRes;
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public JSONObject departmentList() {
        JSONObject jsonRes = new JSONObject();
        List<Department> departmentLists = departmentDao.findAll();
        if(departmentLists != null){
            jsonRes.put("depList", departmentLists);
            jsonRes.put("success", true);
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "科室不存在！");
        }
        return jsonRes;
    }
}
