package com.github.overdrowsed.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.overdrowsed.quiz.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
