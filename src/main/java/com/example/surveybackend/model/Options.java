package com.example.surveybackend.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
