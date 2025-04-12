package calculator.salarycalculator.service;

import calculator.salarycalculator.dto.CalculatorRequest;
import calculator.salarycalculator.util.CalendarUtil;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static calculator.salarycalculator.util.CalendarUtil.AVERAGE_DAYS_IN_MONTH;

@Service
public class VacationPayServiceImpl implements VacationPayService {

    private final ValidationService validationService;

    public VacationPayServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public BigDecimal calculate(CalculatorRequest calculatorRequest) {

        validationService.validate(calculatorRequest);

        return calculateVacationPay(calculatorRequest);
    }

    private int calculateWorkingDays(CalculatorRequest calculatorRequest) {
        int workingDays = 0;

        if(calculatorRequest.getEndDate() != null && calculatorRequest.getStartDate() != null) {
            LocalDate date = calculatorRequest.getStartDate();

            while (!date.isAfter(calculatorRequest.getEndDate())) {
                if (CalendarUtil.isWorkingDay(date)) {
                    workingDays++;
                }
                date = date.plusDays(1);
            }
        }
        else workingDays = calculatorRequest.getVacationDuration();

        return workingDays;
    }

    private BigDecimal calculateVacationPay(CalculatorRequest calculatorRequest) {
        if (calculatorRequest.getEndDate() != null && calculatorRequest.getStartDate() != null) {
            return BigDecimal.valueOf(calculatorRequest.getAverageSalary())
                    .divide(AVERAGE_DAYS_IN_MONTH, 10, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(calculateWorkingDays(calculatorRequest)))
                    .setScale(2, RoundingMode.HALF_EVEN);
        } else return BigDecimal.valueOf(calculatorRequest.getAverageSalary())
                .divide(AVERAGE_DAYS_IN_MONTH, 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(calculatorRequest.getVacationDuration()))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}

