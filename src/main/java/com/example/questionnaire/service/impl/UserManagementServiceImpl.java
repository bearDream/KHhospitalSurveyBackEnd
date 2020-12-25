package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.UserDao;
import com.example.questionnaire.dao.UserDepartmentDao;
import com.example.questionnaire.model.User;
import com.example.questionnaire.model.UserDepartment;
import com.example.questionnaire.service.UserManagementService;
import net.sf.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserManagementServiceImpl implements UserDetailsService, UserManagementService {
    final
    UserDao userDao;

    public UserManagementServiceImpl(UserDao userDao, UserDepartmentDao userDepartmentDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findDistinctByUsername(s);
    }

    @Override
    public JSONObject updateUser(User user) {
        JSONObject jsonRes = new JSONObject();
        if(user.getPassword() != null && user.getPassword() != ""){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            String encodePassword = encoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
        int count = userDao.updateUser(user);
        if(count > 0){
            jsonRes.put("success", true);
            jsonRes.put("msg", "修改成功！");
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "修改失败");
        }
        return jsonRes;
    }

    @Override
    public int delUser(Integer id) {
        return userDao.delUser(id);
    }

    @Override
    public JSONObject userLists() {
        JSONObject jsonRes = new JSONObject();
        List<Map<String, String>> patientLists = userDao.userLists();
        jsonRes.put("patientLists", patientLists);
        jsonRes.put("success", true);
        return jsonRes;
    }
}
