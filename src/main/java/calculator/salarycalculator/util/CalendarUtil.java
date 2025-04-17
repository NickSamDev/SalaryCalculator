package calculator.salarycalculator.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class CalendarUtil {


    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isHoliday(LocalDate date) {
        return Arrays.stream(Holiday.RussianHoliday.values())
                .anyMatch(holiday -> holiday.matches(date));
    }

    public static boolean isWorkingDay(LocalDate date) {
        return !isWeekend(date) && !isHoliday(date);
    }
}
