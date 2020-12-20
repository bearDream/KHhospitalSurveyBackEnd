package com.example.questionnaire.controller;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.Patient;
import com.example.questionnaire.service.PatientService;
import net.sf.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PatientController {

    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * 增加或修改一条病人信息记录
     * */
    @PostMapping("/api/patient/addPatient")
    public JSONObject addPatient(@RequestBody Patient patient){
        patient.setStatus(0);
        JSONObject jsonRes = patientService.addpatient(patient);
        return jsonRes;
    }

    /**
     * 删除一条病人信息记录
     * */
    @PostMapping("/api/patient/delPatient")
    public JSONObject delPatient(@Param("id") Integer id){
        JSONObject jsonResult = patientService.delPatient(id);
        return jsonResult;
    }
}