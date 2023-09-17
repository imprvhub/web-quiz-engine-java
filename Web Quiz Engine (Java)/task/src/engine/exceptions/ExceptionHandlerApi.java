// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler(value = QuizNotFoundException.class)
    public ResponseEntity<Object> handleQuizNotFoundException() {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = UserExistException.class)
    public ResponseEntity<Object> handleUserExistException() {
        return new ResponseEntity<>(
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = NotOwnerException.class)
    public ResponseEntity<Object> handleNotOwnerException() {
        return new ResponseEntity<>(
                HttpStatus.FORBIDDEN
        );
    }
}