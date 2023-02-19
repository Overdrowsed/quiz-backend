package com.github.overdrowsed.quiz.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.overdrowsed.quiz.model.Answer;
import com.github.overdrowsed.quiz.model.Question;
import com.github.overdrowsed.quiz.repository.AnswerRepository;
import com.github.overdrowsed.quiz.repository.QuestionRepository;

@Service
@Transactional
public class QuizService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getQuiz(){
        return questionRepository.findAll();
    }

    public Long getCorrectAnswerId(Long questionId){
        return questionRepository.findById(questionId).get().getCorrectAnswer().getId();
    }

    public void createNewQuiz(){        
        String questionsString = ""; 

        try{
            questionsString = Files.readString(Path.of(this.getClass().getClassLoader().getResource("quiz.txt").toURI()));
        }
        catch(IOException | URISyntaxException exception){
            System.out.println(exception);
        }

        var splitQuestions = questionsString.split("(?m)^\\s*$");

        for(int i = 0; i < splitQuestions.length; i++){
            var items = splitQuestions[i].strip().split("\n");

            //First line is the question
            String prompt = items[0];

            Question question = new Question(prompt);

            List<Answer> answers = new ArrayList<>();

            //Check that there's exactly one correct answer
            int correctAnswerCount = 0;

            for(int j = 1; j < items.length; j++){
                if(items[j].startsWith("*")){
                    correctAnswerCount++;
                }
            }

            if(correctAnswerCount != 1){
                continue;
            }

            //Iterate over answers
            for(int j = 1; j < items.length; j++){
                if(items[j].startsWith("*")){
                    Answer correctAnswer = answerRepository.save(new Answer(items[j]));
                    answers.add(correctAnswer);

                    question.setCorrectAnswer(correctAnswer);
                }
                else{
                    Answer answer = answerRepository.save(new Answer(items[j]));
                    answers.add(answer);
                }
            }

            question.setAnswers(answers);

            questionRepository.save(question);
        }

        questionRepository.flush();
        answerRepository.flush();
    }
}
