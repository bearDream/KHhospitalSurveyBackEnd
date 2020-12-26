package com.example.questionnaire.service;

import javax.servlet.http.HttpServletRequest;

public interface CreateService {

    String createQuestionnaire(Object user, HttpServletRequest httpServletRequest);

    String deleteQuestionnaire(Integer questionnaireId);

    String getQuestionList(Integer questionnaireId);

    String saveQuestionnaireOutline(String questionnaire);

    String saveQuestionnaire(String questionnaire, String questionList);

    String getQuestionnaireOutline(Integer questionnaireId);

    String saveOneQuestion(String question, Integer questionnaireId);

    String releaseQuestionnaire(Integer questionnaireId);

    String closeQuestionnaire(Integer questionnaireId);
}
