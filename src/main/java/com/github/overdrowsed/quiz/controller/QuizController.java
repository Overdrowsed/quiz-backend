package com.github.overdrowsed.quiz.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.overdrowsed.quiz.model.Question;
import com.github.overdrowsed.quiz.service.QuizService;
@SuppressWarnings("unused")
@RestController
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizService service;

    @GetMapping("/get-quiz")
    public List<Question> getQuestion(){
        return service.getQuiz();
    }

    @GetMapping("/get-correct-answer/{id}")
    public Long getCorrectAnswer(@PathVariable Long id){
        return service.getCorrectAnswerId(id);
    }

    @GetMapping("/new-quiz")
    public void addNewQuiz() throws IOException{
        service.createNewQuiz();
    }
}
