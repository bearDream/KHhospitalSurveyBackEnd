package com.example.questionnaire.service;

import com.example.questionnaire.model.Patient;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    JSONObject addpatient(Patient patient);

    JSONObject delPatient(Integer id);

    JSONObject listPatients(Integer index, Integer limit, Integer departmentId);

    JSONObject getPatientList(Patient patient);
}
