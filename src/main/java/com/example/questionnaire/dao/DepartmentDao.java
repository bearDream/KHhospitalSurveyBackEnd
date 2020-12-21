package com.example.questionnaire.dao;

import com.example.questionnaire.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao extends JpaRepository<Department, Integer> {
    List<Department> findAllByIdIs(Integer id);

    List<Department> findAll();

    void deleteById(Integer id);

    @Override
    <S extends Department> S save(S s);

    @Modifying
    @Transactional
    @Query(value = "update department set dep_name = :#{#department.depName}, parent_id = :#{#department.parentId} where id = :#{#department.id}", nativeQuery = true)
    int updateDepartment(@Param("department") Department department);

    List<Department> findAllByParentId(Integer parentId);

    @Transactional
    int removeByParentId(@Param("parentId") Integer parentId);

    Department findDepartmentById(Integer id);
}
