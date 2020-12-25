package com.example.questionnaire.service;

import com.example.questionnaire.model.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserManagementService {

    //修改用户
    JSONObject updateUser(User user);

    //删除用户
    int delUser(Integer id);

    //查询用户
    JSONObject userLists();
}
