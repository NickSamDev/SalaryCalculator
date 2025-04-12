package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;

import java.math.BigDecimal;

public interface VacationPayService {
    BigDecimal calculate(CalculatorRequest calculatorRequest);
}
