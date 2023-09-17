// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.controller;

import engine.dto.CompletedQuizDTO;
import engine.dto.QuizDTO;
import engine.model.Answer;
import engine.model.Feedback;
import engine.model.Quiz;
import engine.servis.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quizzes")
    public ResponseEntity<QuizDTO> createNewQuiz(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createNewQuiz(quiz));
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Page<QuizDTO>> getQuizzes(@RequestParam Integer page) {
        return ResponseEntity.ok(quizService.getQuizzes(page));
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<Feedback> solveQuizById(@PathVariable Long id, @Valid @RequestBody(required = false) Answer answer) {
        return ResponseEntity.ok(quizService.solveQuizById(id, answer));
    }

    @DeleteMapping("quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quizzes/completed")
    public ResponseEntity<Page<CompletedQuizDTO>> getCompletedQuizzes(@RequestParam Integer page) {
        return ResponseEntity.ok(quizService.getCompletedQuizzes(page));
    }
}