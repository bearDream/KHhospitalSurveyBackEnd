package com.example.questionnaire.controller;

import com.example.questionnaire.model.Department;
import com.example.questionnaire.model.Patient;
import com.example.questionnaire.service.PatientService;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PatientController {

    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * 分页获取patient表格
     * */
    @GetMapping("/api/patient/listPatients")
    public JSONObject listPatients(@Param("index") Integer index, @Param("limit") Integer limit, HttpServletRequest request){
        Integer departmentId = Integer.valueOf(request.getSession().getAttribute("depId").toString());
        return patientService.listPatients(index, limit, departmentId);
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

    @PostMapping("/api/patient/getPatientList")
    public JSONObject getPatientList(@RequestBody Patient patient){
        return patientService.getPatientList(patient);
    }
}
