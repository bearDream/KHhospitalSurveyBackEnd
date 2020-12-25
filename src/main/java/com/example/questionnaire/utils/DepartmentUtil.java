package com.example.questionnaire.utils;

import com.example.questionnaire.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DepartmentUtil {
    private List<Object> depAll;
    public List<Object> getDepAll(List<Department> menuCommon, Integer id, Integer level){
        depAll = new ArrayList<Object>();
        get_dep_all(menuCommon, id, level);
        System.out.println("depAll:" + depAll);
        return depAll;
    }

    private List<Object> get_dep_all(List<Department> menuCommon, Integer id, Integer level){
        List<Object> lists = new ArrayList<Object>();
        //继续遍历menu
        for(int i = 0; i < menuCommon.size(); i++){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            //找到父ID等于父节点ID的子节点
            if(menuCommon.get(i).getParentId().equals(id)){
                childArray.put("id", menuCommon.get(i).getId());
                childArray.put("depName", menuCommon.get(i).getDepName());
                childArray.put("parentId", menuCommon.get(i).getParentId());
                //向下递归
                //childArray.put("level", level);
                if (level == 3) {
                    System.out.println("level:" + level);
                    System.out.println("childArray:" + childArray);

                    depAll.add(childArray);
                    System.out.println("3 depAll:" + depAll);
                }
                lists.add(childArray);
                childArray.put("childList", get_dep_all(menuCommon, menuCommon.get(i).getId(), level + 1));
            }
        }
        return lists;
    }
}
