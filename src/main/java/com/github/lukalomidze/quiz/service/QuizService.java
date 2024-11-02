package com.github.lukalomidze.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lukalomidze.quiz.model.Question;
import com.github.lukalomidze.quiz.repository.QuestionRepository;

@Service
@Transactional
public class QuizService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuiz() {
        return questionRepository.findAll();
    }

    public Long getCorrectAnswerId(Long questionId) {
        return questionRepository
            .findById(questionId)
            .get()
            .getCorrectAnswer()
        .getId();
    }
}
