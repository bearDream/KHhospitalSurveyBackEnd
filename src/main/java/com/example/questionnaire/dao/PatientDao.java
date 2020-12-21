package com.example.questionnaire.dao;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PatientDao extends JpaRepository<Patient, Integer> {

    @Query(value="select id, gender, patient_name, phone, birthday, department_id, hospital_id, id_number, status from patient where department_id = ?1", nativeQuery = true)
    List<Patient> findPatientByDepId(Integer departmentId);

    @Override
    <S extends Patient> S save(S s);

    @Modifying
    @Transactional
    @Query(value = "update patient set patient_name = :#{#patient.patientName}, gender = :#{#patient.gender}," +
            "phone = :#{#patient.phone}, birthday = :#{#patient.birthday}," +
            "department_id = :#{#patient.departmentId}, hospital_id = :#{#patient.hospitalId}," +
            "id_number = :#{#patient.idNumber}, status = :#{#patient.status}}, where id = :#{#patient.id}", nativeQuery = true)
    int updatePatient(@Param("patient") Patient patient);

    @Modifying
    @Transactional
    @Query(value = "update patient set status = 1 where id = ?1", nativeQuery = true)
    int updateStatusById(@Param("id") Integer id);

    @Query(value="select patient_id, id, gender, patient_name, phone, birthday, department_id, hospital_id, id_number, status from patient order by id desc limit ?1,?2", nativeQuery = true)
    List<Patient> findAllByPage(@Param("index") Integer index, @Param("limit") Integer limit);

    @Query(value="select count(id) from patient ", nativeQuery = true)
    int findPatientsCount();
}
