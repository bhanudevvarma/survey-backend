package com.example.surveybackend.repository;

import com.example.surveybackend.model.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {
    List<SurveyAnswer> findByQuestionId(Long questionId);
    List<SurveyAnswer> findByUserId(Long userId);
//    List<SurveyAnswer> findByUserIdAndQuestionId(Long userId, Long questionId);
    List<SurveyAnswer> findByUserUsernameAndQuestionId(String username, Long questionId);

}