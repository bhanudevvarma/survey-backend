package com.example.surveybackend.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Options selectedOption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

