package com.example.questionnaire.controller;

import com.example.questionnaire.model.User;
import com.example.questionnaire.service.LoginService;
import com.example.questionnaire.service.UserManagementService;
import net.sf.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserManagementController {
    final
    LoginService loginService;

    final UserManagementService userManagementService;

    public UserManagementController(LoginService loginService, UserManagementService userManagementService) {
        this.loginService = loginService;
        this.userManagementService = userManagementService;
    }



    @PostMapping("/login")
    public String login(@Param("username")String username,@Param("password")String password){
        return loginService.login(username,password);
    }

    //注册用户
    @PostMapping("/api/register")
    public JSONObject register(@Param("depId") Integer depId, @RequestBody User user) {
        return loginService.reg(user, depId);
    }

    //查询用户
    @GetMapping("/api/getUserLists")
    public JSONObject getUserLists(){
        return userManagementService.userLists();
    }

    //删除用户
    @PostMapping("/api/delUser")
    public JSONObject delUser(@RequestParam("userId") Integer userId){
        JSONObject jsonRes = new JSONObject();
        int count = userManagementService.delUser(userId);
        if(count > 0){
            jsonRes.put("success", true);
            jsonRes.put("msg", "删除成功");
        }else{
            jsonRes.put("success", false);
            jsonRes.put("msg", "删除失败");
        }
        return jsonRes;
    }

    //修改用户
    @PostMapping("/api/updateUser")
    public JSONObject updateUser(@RequestParam("depId") Integer depId, @RequestBody User user){
        return userManagementService.updateUser(depId, user);
    }

}
