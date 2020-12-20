package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.PatientDao;
import com.example.questionnaire.model.Patient;
import com.example.questionnaire.service.PatientService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    /**
     * 增加或修改病人信息
     * */
    @Override
    public JSONObject addpatient(Patient patient) {
        JSONObject jsonRes = new JSONObject();
        try {
            int id = patient.getId();
            if(id > 0){//修改病人信息
                int patCount = patientDao.updatePatient(patient);
                if(patCount > 0){
                    jsonRes.put("success", true);
                    jsonRes.put("msg", "修改成功！");
                }else{
                    jsonRes.put("success", false);
                    jsonRes.put("msg", "操作失败!");
                }
            }else{//增加病人信息
                Patient pat = patientDao.save(patient);
                jsonRes.put("patient", pat);
                jsonRes.put("success", true);
                jsonRes.put("msg", "增加成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "操作失败" + e);
        }
        return jsonRes;
    }

    /**
     * 删除病人信息
     * */
    @Override
    public JSONObject delPatient(Integer id) {
        JSONObject jsonRes = new JSONObject();
        try {
            int count = patientDao.updateStatusById(id);
            if(count > 0){
                jsonRes.put("success", true);
                jsonRes.put("msg", "删除成功！");
            }else{
                jsonRes.put("success", false);
                jsonRes.put("msg", "删除失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonRes.put("success", false);
            jsonRes.put("msg", "删除失败" + e);
        }
        return jsonRes;
    }
}
