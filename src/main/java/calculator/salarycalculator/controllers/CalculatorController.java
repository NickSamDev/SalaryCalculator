package calculator.salarycalculator.controllers;

import calculator.salarycalculator.dto.CalculatorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import calculator.salarycalculator.service.VacationPayServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class CalculatorController {
    private final VacationPayServiceImpl service;

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    public CalculatorController(VacationPayServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(
            @RequestParam("averageSalary") Double averageSalary,
            @RequestParam(value = "vacationDuration", required = false) Integer vacationDuration,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        logger.info("Запрос расчета отпускных (/calculate): {}, {}, {}, {}", averageSalary, vacationDuration, startDate, endDate);
        CalculatorRequest request = new CalculatorRequest(averageSalary, vacationDuration, startDate, endDate);
        return ResponseEntity.ok(service.calculate(request));
    }
}

