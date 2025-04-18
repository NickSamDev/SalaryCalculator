package calculator.salarycalculator.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CalculatorControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException ex) {
        logger.error("Ошибка ввода данных: {}", ex.getMessage());
        return new ResponseEntity<>("Ошибка ввода данных: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleException(ValidationException ex) {
        logger.error("Ошибка валиадации данных: {}", ex.getMessage());
        return new ResponseEntity<>("Ошибка валидации данных: {}" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleArgumentNotFound(Exception ex) {
        logger.error("Непредвиденная ошибка: {}", ex.getMessage());
        return new ResponseEntity<>("Непредвиденная ошибка: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleBadDate(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest()
                .body("Ошибка в параметре '" + ex.getName() + ex.getMessage());
    }
}

