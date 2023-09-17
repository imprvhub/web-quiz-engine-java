// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.repository;

import engine.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository {
    Iterable<Quiz> findAll();

    Optional<Quiz> findById(Long id);

    <S extends Quiz> S save(S entity);
}