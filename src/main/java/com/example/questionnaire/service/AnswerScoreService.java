package com.example.questionnaire.service;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author : Egoist
 * @data : 2020/12/14
 */
public interface AnswerScoreService {

    void score(String ip,Integer questionnaireId);

    JSONObject getTitleAndScore(String patientId);

}
