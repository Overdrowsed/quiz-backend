package com.github.lukalomidze.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.lukalomidze.quiz.model.Question;
import com.github.lukalomidze.quiz.service.QuizService;

@RestController
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizService service;

    @GetMapping("/get-quiz")
    public List<Question> getQuestion() {
        return service.getQuiz();
    }

    @GetMapping("/get-correct-answer/{id}")
    public Long getCorrectAnswer(@PathVariable Long id) {
        return service.getCorrectAnswerId(id);
    }
}
