package com.example.surveybackend.service;

import com.example.surveybackend.dto.OptionsDto;
import com.example.surveybackend.dto.QuestionDto;
import com.example.surveybackend.model.*;
import com.example.surveybackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SurveyService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyAnswerRepository surveyAnswerRepository;

    public List<Survey> getAllSurveys() {
        try {
            return surveyRepository.findAll();
        } catch (Exception e){
            logger.error("An error occurred while retrieving surveys.", e);
            throw new RuntimeException("An error occurred while retrieving surveys.", e);
        }
    }

    public List<QuestionDto> getSurveyQuestions(Long surveyId) {
        List<QuestionDto> questionList = new ArrayList<>();
        List<Question> question = questionRepository.findBySurveyId(surveyId);

        for(Question question1 : question) {
            List<Options> options = optionRepository.findByQuestionId(question1.getId());
            QuestionDto questionDTO = new QuestionDto();
            List<OptionsDto> optionsDtos = new ArrayList<>();
            for (Options options1 : options) {
                OptionsDto optionsDto = new OptionsDto();
                optionsDto.setText(options1.getText());
                optionsDto.setId(options1.getId());
                optionsDtos.add(optionsDto);
            }
            questionDTO.setId(question1.getId());
            questionDTO.setText(question1.getText());
            questionDTO.setOptions(optionsDtos);
            questionList.add(questionDTO);
        }

        return questionList;
    }

    public void submitSurveyAnswer(Long userId, Long questionId, Long optionId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NoSuchElementException("Question not found with id: " + questionId));

            Options selectedOption = optionRepository.findById(optionId)
                    .orElseThrow(() -> new NoSuchElementException("Option not found with id: " + optionId));

            SurveyAnswer surveyAnswer = new SurveyAnswer();
            surveyAnswer.setQuestion(question);
            surveyAnswer.setSelectedOption(selectedOption);
            surveyAnswer.setUser(user);

            surveyAnswerRepository.save(surveyAnswer);
        }catch (Exception e){
            logger.error("An error occurred while submitting survey answers.", e);
            throw new RuntimeException("An error occurred while submitting survey answers.", e);
        }
    }

    public List<SurveyAnswer> getUserSurveyAnswers(Long userId) {
        try {
            return surveyAnswerRepository.findByUserId(userId);
        } catch (Exception e){
            logger.error("An error occurred while retrieving survey answers.", e);
            throw new RuntimeException("An error occurred while retrieving survey answers.", e);
        }
    }

    public void updateSurvey(Long surveyid, String surveyName) {
        try {
            Survey survey = surveyRepository.findById(surveyid)
                  .orElseThrow(() -> new NoSuchElementException("Survey not found with id: " + surveyid));
            survey.setTitle("Updated Survey");
            surveyRepository.save(survey);
        } catch (Exception e){
            logger.error("An error occurred while updating survey.", e);
            throw new RuntimeException("An error occurred while updating survey.", e);
        }
    }
}
