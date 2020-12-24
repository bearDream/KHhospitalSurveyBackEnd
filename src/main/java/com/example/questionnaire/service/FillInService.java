package com.example.questionnaire.service;

public interface FillInService {
    void submitAnswer(Integer questionnaireId, String answerListJson, String ip, String patientId);

    Boolean checkAlreadySubmit(Integer questionnaireId, String ip);


}
