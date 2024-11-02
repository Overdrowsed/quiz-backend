package com.github.lukalomidze.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lukalomidze.quiz.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
