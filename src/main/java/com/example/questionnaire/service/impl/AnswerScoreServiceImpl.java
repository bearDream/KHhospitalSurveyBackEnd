package com.example.questionnaire.service.impl;

import com.example.questionnaire.dao.AnswerDao;
import com.example.questionnaire.dao.QuestionDao;
import com.example.questionnaire.dao.QuestionnaireDao;
import com.example.questionnaire.dao.QuestionnaireIpDao;
import com.example.questionnaire.model.Answer;
import com.example.questionnaire.model.Question;
import com.example.questionnaire.model.QuestionnaireIp;
import com.example.questionnaire.service.AnswerScoreService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : Egoist
 * @data : 2020/12/14
 */
@Service
public class AnswerScoreServiceImpl implements AnswerScoreService {

    @Autowired
    AnswerDao answerDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuestionnaireDao questionnaireDao;

    @Autowired
    QuestionnaireIpDao questionnaireIpDao;

    @Autowired
    Gson gson;

//    public AnswerScoreServiceImpl(Gson gson) {
//        this.gson = gson;
//    }

    /**
     * 根据问卷id查询出问题的list，然后循环list，根据问题id，此时先判断是单选还是多选。然后根据问题id找到答案的list
     * @param questionnaireId
     * @return
     */
    @Override
    public void score(String ip,Integer questionnaireId) {
        Double totalSocre = 0.0;
        //根据问卷id找到问题集合
        List<Question> allByQuestionnaireId = questionDao.findAllByQuestionnaireId(questionnaireId);
        //Map<问题id，答案list>
        Map<Integer,List<Answer>> answer = new HashMap<>();
        for (int i = 0 ; i < allByQuestionnaireId.size() ; i++){
            answer.put(allByQuestionnaireId.get(i).getQuestionId(),answerDao.findAllByQuestionId(allByQuestionnaireId.get(i).getQuestionId()));
        }
        Iterator<Map.Entry<Integer, List<Answer>>> iterator = answer.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<Answer>> entry = iterator.next();
            //获取当前答案的问题id，并通过问题id找到相应的问题details，并details进行解析，得到问题选项和得分选项
            String details = questionDao.findDistinctByQuestionId(entry.getValue().get(0).getQuestionId()).getDetails();
            String questionOptions = gson.fromJson(details, JsonObject.class).get("questionOptions").toString();
            String questionOptionsScore = gson.fromJson(details, JsonObject.class).get("questionOptionsScore").toString();
            String[] questionOptionsSpilt = questionOptions.substring(1,questionOptions.length()-1).split(",");
            String[] questionOptionsScoreSpilt = questionOptionsScore.substring(1,questionOptionsScore.length()-1).split(",");
            for (int i = 0 ; i < questionOptionsSpilt.length; i++){
                questionOptionsSpilt[i] = questionOptionsSpilt[i].substring(1,questionOptionsSpilt[i].length()-1);
                questionOptionsScoreSpilt[i] = questionOptionsScoreSpilt[i].substring(1,questionOptionsScoreSpilt[i].length()-1);
            }
            int i = 0;
            if (questionDao.findDistinctByQuestionId(entry.getKey()).getQuestionType().equals("single_check")){
                for (; i < questionOptionsSpilt.length ; i++){
                    if (entry.getValue().get(0).getWriteValue().equals(questionOptionsSpilt[i])){
                        break;
                    }
                }
                if (questionOptionsScoreSpilt[i].equals("-")){
                    totalSocre = totalSocre - Double.parseDouble(questionOptionsScoreSpilt[i].substring(1));
                }else {
                    totalSocre = totalSocre + Double.parseDouble(questionOptionsScoreSpilt[i]);
                }

            }else if (questionDao.findDistinctByQuestionId(entry.getKey()).getQuestionType().equals("multi_check")){
                //TODO 多选选项时使用此处对总分进行计算。
            }
        }
        questionnaireIpDao.updateQuestionnaireScoreByQuestionnaireId(ip,questionnaireId,totalSocre);
    }
}
