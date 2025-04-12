package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryCalculatorValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Test
    void validateSalary_ThrowsException_WhenSalaryIsNegative() {
        CalculatorRequest request = new CalculatorRequest(-100.0, 5, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Зарплата должна быть положительной", exception.getMessage());
    }

    @Test
    void validateSalary_ThrowsException_WhenSalaryIsTooBig() {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(10000000.0, 5, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(request)
        );

        assertEquals("Средняя зарплата не может превышать 1000000", exception.getMessage());
    }

    @Test
    void validateSalary_ThrowsException_WhenSalaryIsNull() {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(null, 5, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(request)
        );

        assertEquals("Укажите зарплату", exception.getMessage());
    }

    @Test
    void validateDates_ThrowsException_WhenDatesAndDurationAreNull() {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(100000.0, null, null, null );
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(request)
        );

        assertEquals("Введите продолжительность отпуска, или даты начала и конца отпуска", exception.getMessage());
    }

    @Test
    void validateDates_ThrowsException_WhenDurationIsTooLong() {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(100000.0, 29, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(request)
        );

        assertEquals("Продолжительность отпуска не может быть более 28 дней", exception.getMessage());
    }

    @Test
    void validateDates_ThrowsException_WhenDurationIsTooShort() {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(100000.0, 2, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(request)
        );

        assertEquals("Продолжительность отпуска не может быть менее 3 дней", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 14, 27, 28})
    void validateDates_DoesNotThrowsException_AcceptsValidDurations(int validDuration) {
        ValidationService service = new ValidationServiceImpl();
        CalculatorRequest request = new CalculatorRequest(100000.0, validDuration, null, null );

        assertDoesNotThrow(() -> service.validate(request));
    }

}
