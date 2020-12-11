package com.example.questionnaire.dao;

import com.example.questionnaire.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PatientDao extends JpaRepository<Patient, Integer> {

    @Query(value="select id, gender, patient_name, phone, birthday, department_id, hospital_id, id_number, status from patient where department_id = ?1", nativeQuery = true)
    List<Patient> findPatientByDepId(Integer departmentId);

}
