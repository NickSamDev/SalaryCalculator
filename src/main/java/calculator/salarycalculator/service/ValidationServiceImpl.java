package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class ValidationServiceImpl implements ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Value("${app.verification.max-days}")
    private Integer maxDays;

    @Value("${app.verification.min-days}")
    private Integer minDays;

    @Value("${app.verification.max-salary}")
    private Integer maxSalary;

    private void validateSalary(Double averageSalary) {
        if (averageSalary <= 0) {
            throw new IllegalArgumentException("Зарплата должна быть положительной");
        }
        if (averageSalary > maxSalary) {
            throw new IllegalArgumentException(String.format("Средняя зарплата не может превышать %d", maxSalary));
        }
    }

    private void validateDates(CalculatorRequest calculatorRequest) {
        if (calculatorRequest.getStartDate() == null || calculatorRequest.getEndDate() == null) {
            if (calculatorRequest.getVacationDuration() == null)
                throw new IllegalArgumentException("Введите продолжительность отпуска, или даты начала и конца отпуска");

            if (calculatorRequest.getVacationDuration() > maxDays)
                throw new IllegalArgumentException(String.format("Продолжительность отпуска не может быть более %d дней", maxDays));

            if (calculatorRequest.getVacationDuration() < minDays)
                throw new IllegalArgumentException(String.format("Продолжительность отпуска не может быть менее %d дней", minDays));
        } else {
            if (calculatorRequest.getStartDate().isAfter(calculatorRequest.getEndDate()))
                throw new IllegalArgumentException("Конец отпуска не может быть раньше начала отпуска");
            if (ChronoUnit.DAYS.between(calculatorRequest.getStartDate(), calculatorRequest.getEndDate()) > maxDays)
                throw new IllegalArgumentException(String.format("Продолжительность отпуска не может быть более %d дней", maxDays));
            if (ChronoUnit.DAYS.between(calculatorRequest.getStartDate(), calculatorRequest.getEndDate()) < minDays)
                throw new IllegalArgumentException(String.format("Продолжительность отпуска не может быть менее %d дней", minDays));
        }
    }

    public void validate(CalculatorRequest calculatorRequest) {
        validateSalary(calculatorRequest.getAverageSalary());
        validateDates(calculatorRequest);

        logger.info("Запрос прошел валидацию");
    }
}
