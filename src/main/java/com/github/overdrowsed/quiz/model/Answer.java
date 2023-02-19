package com.github.overdrowsed.quiz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "answers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 500)
    private String answer;

    public Answer(String answer) {
        if(answer.startsWith("*")){
            answer = answer.replaceFirst("\\*", "");
        }

        this.answer = answer;
    }
}
