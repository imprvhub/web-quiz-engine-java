// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuizRepositoryImpl implements QuizRepository {

    private static Long sequence = 1L;
    private final Map<Long, Quiz> quizzes = new HashMap<>();

    @Override
    public List<Quiz> findAll() {
        return quizzes.values().stream().toList();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    @Override
    public Quiz save(Quiz quiz) {
        quiz.setId(sequence);
        if (Objects.isNull(quiz.getAnswer())) {
            quiz.setAnswer(Collections.emptyList());
        }
        this.quizzes.put(sequence, quiz);
        sequence++;
        return quiz;
    }
}