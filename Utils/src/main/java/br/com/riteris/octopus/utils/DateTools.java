package br.com.riteris.octopus.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateTools {

    private DateTools() {
    }

    public static int getTotalYearDays( int year ) {
        if ( year < 1 ) {
            throw new IllegalArgumentException( "The year can't be less than 1." );
        }

        return LocalDate.of( year, 12, 31 ).getDayOfYear();
    }

    public static LocalDate getDateAtLastDayOfActualDateMonth( LocalDate date ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "The date can't be null." );
        }

        return getDateAtFirstDayOfActualDateMonth( date ).plusMonths( 1 ).minusDays( 1 );
    }

    public static LocalDate getDateAtFirstDayOfActualDateMonth( LocalDate date ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "The date can't be null." );
        }

        return LocalDate.of( date.getYear(), date.getMonth(), 1 );
    }

    public static boolean isDateBetweenPeriodInclusive( LocalDate date, LocalDate periodStartDate, LocalDate periodEndDate ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "The date can't be null." );
        }

        if ( periodStartDate == null ) {
            throw new IllegalArgumentException( "The date of period start can't be null." );
        }

        if ( periodEndDate == null ) {
            throw new IllegalArgumentException( "The date of period end can't be null." );
        }

        return dateIsEqualOrAfterExcludingTimeInfo( date, periodStartDate ) && dateIsEqualOrBeforeExcludingTimeInfo( date, periodEndDate );
    }

    public static boolean dateIsEqualOrAfterExcludingTimeInfo( LocalDate date, LocalDate referenceDate ) {
        if ( date == null || referenceDate == null ) {
            throw new IllegalArgumentException( "The date objects to be compared can't be null." );
        }

        return isSameDateExcludingTimeInfo( date, referenceDate ) || dateIsAfterExcludingTimeInfo( date, referenceDate );
    }

    public static boolean dateIsEqualOrBeforeExcludingTimeInfo( LocalDate date, LocalDate referenceDate ) {
        if ( date == null || referenceDate == null ) {
            throw new IllegalArgumentException( "The date objects to be compared can't be null." );
        }

        return isSameDateExcludingTimeInfo( date, referenceDate ) || dateIsBeforeExcludingTimeInfo( date, referenceDate );
    }

    public static boolean isSameDateExcludingTimeInfo( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            throw new IllegalArgumentException( "The date objects to be compared can't be null." );
        }

        return ChronoUnit.DAYS.between( firstDate, secondDate ) == 0;
    }

    public static boolean dateIsAfterExcludingTimeInfo( LocalDate date, LocalDate referenceDate ) {
        if ( date == null || referenceDate == null ) {
            throw new IllegalArgumentException( "The date objects to be compared can't be null." );
        }

        return ChronoUnit.DAYS.between( date, referenceDate ) < 0;
    }

    public static boolean dateIsBeforeExcludingTimeInfo( LocalDate date, LocalDate referenceDate ) {
        if ( date == null || referenceDate == null ) {
            throw new IllegalArgumentException( "The date objects to be compared can't be null." );
        }

        return ChronoUnit.DAYS.between( date, referenceDate ) > 0;
    }

    public static boolean isDateBetweenPeriodExclusive( LocalDate date, LocalDate periodStartDate, LocalDate periodEndDate ) {
        if ( date == null ) {
            throw new IllegalArgumentException( "The date can't be null." );
        }

        if ( periodStartDate == null ) {
            throw new IllegalArgumentException( "The date of period start can't be null." );
        }

        if ( periodEndDate == null ) {
            throw new IllegalArgumentException( "The date of period end can't be null." );
        }

        return dateIsAfterExcludingTimeInfo( date, periodStartDate ) && dateIsBeforeExcludingTimeInfo( date, periodEndDate );
    }

}
