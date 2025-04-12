package calculator.salarycalculator.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalendarUtil {
    public static final BigDecimal AVERAGE_DAYS_IN_MONTH = new BigDecimal("29.3");

    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    public static boolean isHoliday(LocalDate date) {
        return (date.getMonthValue() == 1 && date.getDayOfMonth() == 1) ||
                (date.getMonthValue() == 1 && date.getDayOfMonth() == 7) ||
                (date.getMonthValue() == 2 && date.getDayOfMonth() == 23) ||
                (date.getMonthValue() == 3 && date.getDayOfMonth() == 8) ||
                (date.getMonthValue() == 5 && date.getDayOfMonth() == 1) ||
                (date.getMonthValue() == 5 && date.getDayOfMonth() == 9) ||
                (date.getMonthValue() == 6 && date.getDayOfMonth() == 12) ||
                (date.getMonthValue() == 11 && date.getDayOfMonth() == 4);
    }

    public static boolean isWorkingDay(LocalDate date) {
        return !isWeekend(date) && !isHoliday(date);
    }
}
