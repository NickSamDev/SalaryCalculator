package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;

public interface ValidationService {
    void validate(CalculatorRequest calculatorRequest);
}
