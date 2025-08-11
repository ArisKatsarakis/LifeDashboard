package gr.ariskatsarakis.organizer.exception;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, HttpServletRequest req) {
                ExceptionResponse response = new ExceptionResponse();
                response.setLocation(req.getRequestURI());
                response.setTimestamp(LocalDate.now());
                response.setErrorMessage(ex.getMessage());

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        @ExceptionHandler(SavingGoalNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleAllExceptions(SavingGoalNotFoundException ex,
                        HttpServletRequest req) {
                ExceptionResponse response = new ExceptionResponse();
                response.setLocation(req.getRequestURI());
                response.setTimestamp(LocalDate.now());
                response.setErrorMessage(String.format("Goal: %d not found", ex.getSavingGoalId()));

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }

}
