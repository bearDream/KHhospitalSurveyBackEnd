package com.example.questionnaire.service;

import com.example.questionnaire.dao.UserDao;
import com.example.questionnaire.dao.UserDepartmentDao;
import com.example.questionnaire.model.User;
import com.example.questionnaire.model.UserDepartment;
import net.sf.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class LoginService {
    final
    UserDao userDao;
    final UserDepartmentDao userDepartmentDao;

    public LoginService(UserDao userDao, UserDepartmentDao userDepartmentDao) {
        this.userDao = userDao;
        this.userDepartmentDao = userDepartmentDao;
    }

    public JSONObject reg(User user, Integer depId) {
        JSONObject jsonRes = new JSONObject();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(user.getPassword());

        if(userDao.findDistinctByUsername(user.getUsername())!=null){
            jsonRes.put("success", false);
            jsonRes.put("msg", "用户名已存在！");
            return jsonRes;
        }

        user.setCreateTime(new Date());
        user.setStatus("0");
        user.setPassword(encodePassword);
        User userRes = userDao.save(user);
        if(userRes != null){
            UserDepartment userDepartment = new UserDepartment();
            LocalDateTime time = LocalDateTime.now();
            userDepartment.setUserId(userRes.getUserId());
            userDepartment.setDepId(depId);
            userDepartment.setCreatedTime(time);
            UserDepartment userDepRes = userDepartmentDao.save(userDepartment);
            if(userDepRes != null){
                jsonRes.put("success", true);
                jsonRes.put("msg", "注册成功！");
            }else{
                userDao.deleteById(userRes.getUserId());
                jsonRes.put("success", false);
                jsonRes.put("msg", "注册失败！");
            }
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "注册失败！");
        }
        return jsonRes;
    }

    public String login(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(password);
        User user = userDao.findDistinctByUsername(username);
        if (user == null) {
            return "unregistered";
        }
        if (user.getPassword().equals(encodePassword)) {
            user.setLastLoginTime(new Date());
            userDao.save(user);
            return "success";
        } else {
            return "wrong";
        }
    }
}
