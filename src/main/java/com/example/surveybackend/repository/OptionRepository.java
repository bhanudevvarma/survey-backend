package com.example.surveybackend.repository;

import com.example.surveybackend.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Options, Long> {
    List<Options> findByQuestionId(Long questionId);
}
