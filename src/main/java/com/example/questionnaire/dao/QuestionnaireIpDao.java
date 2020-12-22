package com.example.questionnaire.dao;

import com.example.questionnaire.model.QuestionnaireIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface QuestionnaireIpDao extends JpaRepository<QuestionnaireIp, Integer> {
    List<QuestionnaireIp> findAllByQuestionnaireIdAndIp(Integer questionnaireId, String ip);

    List<QuestionnaireIp> findAllByPatientId(String patientId);
    @Transactional
    @Modifying
    @Query("update questionnaire_ip set score=:totalScore where questionnaireId=:questionnaireId and ip = :ip")
    void updateQuestionnaireScoreByQuestionnaireId(@Param("ip") String ip,@Param("questionnaireId") Integer questionnaireId, @Param("totalScore") Double totalScore);
}
