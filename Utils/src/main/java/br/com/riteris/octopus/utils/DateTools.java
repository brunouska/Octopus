package br.com.riteris.octopus.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public final class DateTools {

    private DateTools() {
    }

    public static String formatDate( TemporalAccessor dateObj, String format ) {
        if ( dateObj == null || stringIsNullOrEmptyOrBlank( format ) ) {
            return "";
        }

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );

            return formatter.format( dateObj );

        } catch ( Exception e ) {

            return "";

        }
    }

    public static long getYearDaysAmount( int year ) {
        if ( year < 1 ) {
            return 0;
        }

        LocalDate dateA = LocalDate.of( year, 1, 1 );
        LocalDate dateB = dateA.plusYears( 1 );

        return ChronoUnit.DAYS.between( dateA, dateB );
    }

    public static LocalDate getDateAtLastDayOfMonth( LocalDate date ) {
        if ( date == null ) {
            return null;
        }

        return DateTools.getDateAtFirstDayOfMonth( date ).plusMonths( 1 ).minusDays( 1 );
    }

    public static LocalDate getDateAtFirstDayOfMonth( LocalDate date ) {
        if ( date == null ) {
            return null;
        }

        return LocalDate.of( date.getYear(), date.getMonth(), 1 );
    }

    public static boolean isDateBetweenPeriodInclusive( LocalDate targetDate, LocalDate periodStart, LocalDate
            periodEnd ) {
        return !( targetDate == null || periodStart == null || periodEnd == null ) && ( DateTools.isEqualOrAfterDate(
                targetDate, periodStart ) && DateTools.isEqualOrBeforeDate( targetDate, periodEnd ) );

    }

    public static boolean isEqualOrAfterDate( LocalDate firstDate, LocalDate secondDate ) {
        return ( DateTools.isEqualDate( firstDate, secondDate ) || DateTools.isAfterDate( firstDate, secondDate ) );
    }

    public static boolean isEqualOrBeforeDate( LocalDate firstDate, LocalDate secondDate ) {
        return ( DateTools.isEqualDate( firstDate, secondDate ) || DateTools.isBeforeDate( firstDate, secondDate ) );
    }

    public static boolean isEqualDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween == 0;
    }

    public static boolean isAfterDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween < 0;
    }

    public static boolean isBeforeDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween > 0;
    }

    public static boolean isDateBetweenPeriodExclusive( LocalDate targetDate, LocalDate periodStart, LocalDate
            periodEnd ) {
        return !( targetDate == null || periodStart == null || periodEnd == null ) && ( DateTools.isAfterDate(
                targetDate, periodStart ) && DateTools.isBeforeDate( targetDate, periodEnd ) );

    }

}
