package com.github.overdrowsed.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.overdrowsed.quiz.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
