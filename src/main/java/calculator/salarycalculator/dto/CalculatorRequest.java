package calculator.salarycalculator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CalculatorRequest {
    private Double averageSalary;

    private Integer vacationDuration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public CalculatorRequest(Double averageSalary, Integer vacationDuration, LocalDate startDate, LocalDate endDate) {
        this.averageSalary = averageSalary;
        this.vacationDuration = vacationDuration;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public Integer getVacationDuration() {
        return vacationDuration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public void setVacationDuration(Integer vacationDuration) {
        this.vacationDuration = vacationDuration;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
