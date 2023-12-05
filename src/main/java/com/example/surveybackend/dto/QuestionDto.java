package com.example.surveybackend.dto;

import com.example.surveybackend.model.Options;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private String text;
    private List<OptionsDto> options;
}
