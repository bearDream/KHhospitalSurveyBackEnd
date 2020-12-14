package com.example.questionnaire.utils;

import com.example.questionnaire.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询组织树
 * */
@Component
public class MenuTreeUtil {
    //已经被buildTree的list集合
    private List<Department> menuCommon;

    public List<Object> menuList(List<Department> menu){
        //返回给前端的NewTree List集合
        List<Object> list = new ArrayList<Object>();
        this.menuCommon = menu;

        // 通过遍历menu，找到父节点为0的节点，它是顶级父节点
        // 然后调用menuChild，递归遍历所有子节点
        for (int i = 0; i < menu.size(); i ++) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(menu.get(i).getParentId() == 0){
                mapArr.put("id", menu.get(i).getId());
                mapArr.put("deptName", menu.get(i).getDepName());
                mapArr.put("parentId", menu.get(i).getParentId());

                //遍历开始
                mapArr.put("level", i + 1);
                Integer level = 0;
                level = i + 2;
                mapArr.put("childList", menuChild(menu.get(i).getId(), level));
                list.add(mapArr);
            }
        }
        return list;
    }

    private List<?> menuChild(Integer id, Integer level){
        List<Object> lists = new ArrayList<Object>();
        //继续遍历menu
        for(int i = 0; i < menuCommon.size(); i++){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            //找到父ID等于父节点ID的子节点
            if(menuCommon.get(i).getParentId().equals(id)){
                childArray.put("id", menuCommon.get(i).getId());
                childArray.put("deptName", menuCommon.get(i).getDepName());
                childArray.put("parentId", menuCommon.get(i).getParentId());
                //向下递归
                childArray.put("level", level);
                childArray.put("childList", menuChild(menuCommon.get(i).getId(), level + 1));
                lists.add(childArray);
            }
        }
        return lists;
    }
}
