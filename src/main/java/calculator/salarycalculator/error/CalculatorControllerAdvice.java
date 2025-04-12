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
    public ResponseEntity<CalculatorResponseError> handleException(IllegalArgumentException ex) {
        logger.error("Ошибка валидации: {}", ex.getMessage());
        CalculatorResponseError calculatorResponseError = new CalculatorResponseError("validation", "400", "Ошибка аргумента: " + ex.getMessage());
        return new ResponseEntity<>(calculatorResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleArgumentNotFound(Exception ex){
        logger.error("Непредвиденная ошибка: {}", ex.getMessage());
        return new ResponseEntity<>("Непредвиденная ошибка: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleBadDate(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest()
                .body("Ошибка в параметре '" + ex.getName() + "': используйте формат ГГГГ-ММ-ДД");
    }
}

