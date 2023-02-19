# quiz-backend
Backend for quiz app. Required to be self hosted. Currently configured to use PostgreSQL. Backend features three routes: 
1. `/new-quiz` puts the `quiz.txt` available in the resources folder into the database. Needs to be called before app is usable. `quiz.txt` uses the following convention:
-first up is the question prompt, written on a single line.
-next several lines are possible answers, the correct one must begin with `*`, only one correct answer is permitted per question
-each individual question ("quiz item") is separated by an empty line
2. `/get-quiz` returns the whole quiz, i.e all of the questions in the database
3. `/get-correct-answer/{id}` returns the `id` of the correct answer for a given question

Currently, there is no way to make several different quizzes. In addition, no measures have been taken to avoid cheating for now, as `/get-correct-answer` is publicly available. To implement anti-cheat, one option could be to track user's progress on the backend instead of frontend and only provide the correct answer id after a question has been submitted

Uses Spring Boot as the backend framework and Spring Data JPA for database interaction
