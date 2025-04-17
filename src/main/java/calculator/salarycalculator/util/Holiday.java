package calculator.salarycalculator.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Holiday {

    public static final BigDecimal AVERAGE_DAYS_IN_MONTH = new BigDecimal("29.3");

    public enum RussianHoliday {
        NEW_YEAR(Month.JANUARY, 1),
        CHRISTMAS(Month.JANUARY, 7),
        DEFENDER_DAY(Month.FEBRUARY, 23),
        WOMEN_DAY(Month.MARCH, 8),
        LABOR_DAY(Month.MAY, 1),
        VICTORY_DAY(Month.MAY, 9),
        RUSSIA_DAY(Month.JUNE, 12),
        UNITY_DAY(Month.NOVEMBER, 4);

        private final Month month;
        private final int day;

        RussianHoliday(Month month, int day) {
            this.month = month;
            this.day = day;
        }

        public boolean matches(LocalDate date) {
            return date.getMonth() == month && date.getDayOfMonth() == day;
        }
    }
}