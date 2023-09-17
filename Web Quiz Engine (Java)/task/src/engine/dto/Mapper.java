// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.dto;

import engine.model.CompletedQuiz;
import engine.model.Quiz;

public class Mapper {

    public static QuizDTO mapQuizToQuizDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }

    public static CompletedQuizDTO mapCompletedQuizToCompletedQuizDTO(CompletedQuiz completedQuiz) {
        return new CompletedQuizDTO(completedQuiz.getQuiz().getId(), completedQuiz.getCompletedAt());
    }
}