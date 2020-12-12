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
    //返回给前端的NewTree List集合
    private List<Object> list = new ArrayList<Object>();

    public List<Object> menuList(List<Department> menu){
        this.menuCommon = menu;

        // 通过遍历menu，找到父节点为0的节点，它是顶级父节点
        // 然后调用menuChild，递归遍历所有子节点
        for (Department x : menu) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(x.getParentId() == 0){
                mapArr.put("id", x.getId());
                mapArr.put("deptName", x.getDepName());
                mapArr.put("parentId", x.getParentId());

                //遍历开始
                mapArr.put("childList", menuChild(x.getId()));
                list.add(mapArr);
            }
        }
        return list;
    }

    private List<?> menuChild(Integer id){
        List<Object> lists = new ArrayList<Object>();
        //继续遍历menu
        for(Department a:menuCommon){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            //找到父ID等于父节点ID的子节点
            if(a.getParentId().equals(id)){
                childArray.put("id", a.getId());
                childArray.put("deptName", a.getDepName());
                childArray.put("parentId", a.getParentId());
                //向下递归
                childArray.put("childList", menuChild(a.getId()));
                lists.add(childArray);
            }
        }
        return lists;
    }
}
