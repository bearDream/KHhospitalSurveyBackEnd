package com.example.questionnaire.dao;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserDepartmentDao extends JpaRepository<UserDepartment, Integer> {
    List<UserDepartment> findAllByIdIs(Integer id);

    @Query(value = "select us.userId as userId, us.username as username from user_department ud left join user us on ud.userId = us.userId where ud.depId = ?1 ")
    List<Map<String, Object>> selectPatient(Integer depId);

    @Override
    <S extends UserDepartment> S save(S s);

    UserDepartment findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "update user_department set dep_id = :#{#userDepartment.depId} where user_id = :#{#userDepartment.userId}", nativeQuery = true)
    int updateUserDepartment(UserDepartment userDepartment);
}
