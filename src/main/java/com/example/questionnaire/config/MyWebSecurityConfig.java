package com.example.questionnaire.config;

import com.example.questionnaire.dao.DepartmentDao;
import com.example.questionnaire.dao.UserDao;
import com.example.questionnaire.dao.UserDepartmentDao;
import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.User;
import com.example.questionnaire.model.UserDepartment;
import com.example.questionnaire.service.impl.UserManagementServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    final
    UserManagementServiceImpl userManagementService;
    final UserDepartmentDao userDepartmentDao;
    final DepartmentDao departmentDao;
    final UserDao userDao;

    public MyWebSecurityConfig(UserManagementServiceImpl userManagementService, UserDepartmentDao userDepartmentDao, DepartmentDao departmentDao, UserDao userDao) {
        this.userManagementService = userManagementService;
        this.userDepartmentDao = userDepartmentDao;
        this.departmentDao = departmentDao;
        this.userDao = userDao;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userManagementService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/fillin/**","/fillin/**","/register","/api/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("http://localhost/login").loginProcessingUrl("/api/login").successHandler(
                new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        JSONObject jsonRes = new JSONObject();
                        Object principal = authentication.getPrincipal();
                        String username = authentication.getName();
                        User user = userDao.findDistinctByUsername(username);
                        UserDepartment userDepartment = userDepartmentDao.findByUserId(user.getUserId());
                        Department department = departmentDao.findDepartmentById(userDepartment.getId());
                        jsonRes.put("user", user);
                        jsonRes.put("userDepartment", userDepartment);
                        jsonRes.put("department", department);
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        httpServletResponse.setStatus(200);
                        out.write(jsonRes.toString());
                        out.flush();
                        out.close();
                    }
                }
        ).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                httpServletResponse.setStatus(200);
                out.write("wrong");
                out.flush();
                out.close();
            }
        }).permitAll()
                .and().logout().logoutUrl("/api/logout").clearAuthentication(true).invalidateHttpSession(true).addLogoutHandler(new LogoutHandler() {
            @Override
            public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
            }
        }).logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            }
        })
                .and().csrf().disable();
    }
}
