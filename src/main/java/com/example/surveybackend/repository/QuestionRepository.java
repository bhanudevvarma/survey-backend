package com.example.surveybackend.repository;

import com.example.surveybackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySurveyId(Long surveyId);
    // Additional custom queries can be added here if needed
}

