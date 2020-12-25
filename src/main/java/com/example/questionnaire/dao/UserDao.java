package com.example.questionnaire.dao;

import com.example.questionnaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<User, Integer> {
    User findDistinctByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "update user set email = :#{#user.email}, phone_num = :#{#user.phoneNum}, username = :#{#user.username} where user_id = :#{#user.userId}", nativeQuery = true)
    int updateUser(User user);

    @Query(value="select u.user_id as userId, ifnull(u.email, '') as email , ifnull(u.phone_num, '') as phoneNum, u.username, ud.dep_id as depId from user u left join user_department ud on u.user_id = ud.user_id where u.status = '0' order by u.user_id desc", nativeQuery = true)
    List<Map<String, String>> userLists();

    @Modifying
    @Transactional
    @Query(value = "update user set status = 1 where user_id = ?1", nativeQuery = true)
    int delUser(Integer userId);
}
