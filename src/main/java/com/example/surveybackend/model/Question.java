package com.example.surveybackend.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
