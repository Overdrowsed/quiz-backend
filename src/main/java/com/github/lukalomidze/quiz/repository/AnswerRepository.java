package com.github.lukalomidze.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lukalomidze.quiz.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
