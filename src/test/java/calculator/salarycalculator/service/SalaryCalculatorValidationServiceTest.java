package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryCalculatorValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private VacationPayServiceImpl vacationPayServiceImpl;

    @Test
    void validate_salary_throws_exception_when_salary_is_negative() {
        CalculatorRequest request = new CalculatorRequest(-100.0, 5, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Зарплата должна быть положительной", exception.getMessage());
    }

    @Test
    void validate_salary_throws_exception_when_salary_is_too_big() {
        CalculatorRequest request = new CalculatorRequest(10000000.0, 5, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Средняя зарплата не может превышать 1000000", exception.getMessage());
    }

    @Test
    void validate_dates_throws_exception_when_dates_and_duration_are_null() {
        CalculatorRequest request = new CalculatorRequest(100000.0, null, null, null );
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Введите продолжительность отпуска, или даты начала и конца отпуска", exception.getMessage());
    }

    @Test
    void validate_dates_throws_exception_when_duration_is_too_long() {
        CalculatorRequest request = new CalculatorRequest(100000.0, 29, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Продолжительность отпуска не может быть более 28 дней", exception.getMessage());
    }

    @Test
    void validate_dates_throws_exception_when_duration_is_too_short() {
        CalculatorRequest request = new CalculatorRequest(100000.0, 2, null, null );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validationService.validate(request)
        );

        assertEquals("Продолжительность отпуска не может быть менее 3 дней", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 14, 27, 28})
    void validate_dates_does_not_throws_exception_accepts_valid_durations(int validDuration) {
        CalculatorRequest request = new CalculatorRequest(100000.0, validDuration, null, null );

        assertDoesNotThrow(() -> validationService.validate(request));
    }

    @Test
    void vacation_pay_service_correct_value_calculate_with_fixed_duration() {
        CalculatorRequest request = new CalculatorRequest(100000.0, 14, null, null);
        BigDecimal result = vacationPayServiceImpl.calculate(request);
        assertEquals(new BigDecimal("47781.57"), result);
    }

    @Test
    void vacation_pay_service_correct_value_may_vacation_with_holidays() {
        CalculatorRequest request = new CalculatorRequest(
                200000.0, 10,
                LocalDate.of(2025, 4, 28),
                LocalDate.of(2025, 5, 7)
        );
        BigDecimal result = vacationPayServiceImpl.calculate(request);
        assertEquals(new BigDecimal("47781.57"), result);
    }

}
