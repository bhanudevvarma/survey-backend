package com.example.surveybackend.controller;

import com.example.surveybackend.dto.QuestionDto;
import com.example.surveybackend.model.Question;
import com.example.surveybackend.model.Survey;
import com.example.surveybackend.model.SurveyAnswer;
import com.example.surveybackend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http://localhost:3000")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/all")
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @GetMapping("/{surveyId}/questions")
    public ResponseEntity<List<QuestionDto>> getSurveyQuestions(@PathVariable Long surveyId) {
        List<QuestionDto> questionDtoList = surveyService.getSurveyQuestions(surveyId);
        return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
    }

    @PostMapping("/submit-answer")
    public ResponseEntity<Void> submitSurveyAnswer(@RequestParam Long userId,
                                                   @RequestParam Long questionId,
                                                   @RequestParam Long optionId) {
        surveyService.submitSurveyAnswer(userId, questionId, optionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/answers")
    public ResponseEntity<List<SurveyAnswer>> getUserSurveyAnswers(@PathVariable Long userId) {
        List<SurveyAnswer> answers = surveyService.getUserSurveyAnswers(userId);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    //    @GetMapping("/questions/{questionId}/options")
//    public ResponseEntity<List<Option>> getQuestionOptions(@PathVariable Long questionId) {
//        List<Option> options = surveyService.getQuestionOptions(questionId);
//        return new ResponseEntity<>(options, HttpStatus.OK);
//    }
//
//    @PostMapping("/submit-answer")
//    public ResponseEntity<Void> submitSurveyAnswer(@RequestParam Long questionId, @RequestParam Long optionId) {
//        surveyService.submitSurveyAnswer(questionId, optionId);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/questions/{questionId}/answers")
//    public ResponseEntity<List<SurveyAnswer>> getSurveyAnswers(@PathVariable Long questionId) {
//        List<SurveyAnswer> answers = surveyService.getSurveyAnswers(questionId);
//        return new ResponseEntity<>(answers, HttpStatus.OK);
//    }
}
