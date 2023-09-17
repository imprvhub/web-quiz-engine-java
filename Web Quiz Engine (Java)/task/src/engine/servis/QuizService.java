// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.servis;

import engine.dto.CompletedQuizDTO;
import engine.exceptions.NotOwnerException;
import engine.exceptions.QuizNotFoundException;
import engine.model.*;
import engine.repository.CompletedQuizzesRepository;
import engine.repository.QuizCrudRepository;
import engine.dto.Mapper;
import engine.dto.QuizDTO;
import engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class QuizService {

    private final QuizCrudRepository quizRepository;
    private final UserRepository userRepository;
    private final CompletedQuizzesRepository completedQuizzesRepository;

    public QuizDTO createNewQuiz(Quiz quiz) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findAppUserByUsername(auth.getName()).get();
        user.addQuiz(quiz);
        quiz.setUser(user);
        Quiz tempQuiz = quizRepository.save(quiz);
        return Mapper.mapQuizToQuizDTO(tempQuiz);
    }

    public QuizDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(QuizNotFoundException::new);

        return Mapper.mapQuizToQuizDTO(quiz);
    }

    public Page<QuizDTO> getQuizzes(Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);

        Page<QuizDTO> page = quizRepository.findAll(pageRequest)
                .map(Mapper::mapQuizToQuizDTO);

        return page;
    }

    public Feedback solveQuizById(Long id, Answer answer) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(QuizNotFoundException::new);

        boolean isAnswerCorrect = new HashSet<>(quiz.getAnswer()).equals(new HashSet<>(answer.answer()));

        if (isAnswerCorrect) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            AppUser user = userRepository.findAppUserByUsername(auth.getName())
                    .orElseThrow();


            CompletedQuiz completedQuiz = new CompletedQuiz(quiz, user, new Date());
            user.addCompletedQuiz(completedQuiz);
            quiz.addCompletedQuiz(completedQuiz);
            completedQuizzesRepository.save(completedQuiz);
        }

        return new Feedback(isAnswerCorrect);
    }

    public void deleteQuizById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(QuizNotFoundException::new);

        if (!auth.getName().equals(quiz.getUser().getUsername())) {
            throw new NotOwnerException();
        }

        quizRepository.delete(quiz);
    }

    public Page<CompletedQuizDTO> getCompletedQuizzes(Integer pageNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);

        Page<CompletedQuizDTO> page = completedQuizzesRepository.findAll(auth.getName(), pageRequest)
                .map(Mapper::mapCompletedQuizToCompletedQuizDTO);
        return page;
    }
}