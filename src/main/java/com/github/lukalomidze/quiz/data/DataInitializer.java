package com.github.lukalomidze.quiz.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.github.lukalomidze.quiz.model.Answer;
import com.github.lukalomidze.quiz.model.Question;
import com.github.lukalomidze.quiz.repository.AnswerRepository;
import com.github.lukalomidze.quiz.repository.QuestionRepository;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private AnswerRepository answerRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createNewQuiz();        
    }

    public void createNewQuiz() {
        String questionsString = "";
        
        try {
            questionsString = new String(
                this
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream("quiz.txt")
                .readAllBytes()
            );
        } catch (IOException exception) {
            return;
        }

        var splitQuestions = questionsString.split("(?m)^\\s*$");

        for (int i = 0; i < splitQuestions.length; i++) {
            var items = splitQuestions[i].strip().split("\n");

            //First line is the question
            String prompt = items[0];

            Question question = new Question(prompt);

            List<Answer> answers = new ArrayList<>();

            //Check that there's exactly one correct answer
            int correctAnswerCount = 0;

            for (int j = 1; j < items.length; j++) {
                if(items[j].startsWith("*")){
                    correctAnswerCount++;
                }
            }

            if (correctAnswerCount != 1) {
                continue;
            }

            //Iterate over answers
            for (int j = 1; j < items.length; j++) {
                if (items[j].startsWith("*")) {
                    Answer correctAnswer = answerRepository.save(new Answer(items[j]));
                    answers.add(correctAnswer);

                    question.setCorrectAnswer(correctAnswer);
                } else {
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
