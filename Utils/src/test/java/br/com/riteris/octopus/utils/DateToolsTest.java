package br.com.riteris.octopus.utils;

import org.junit.Test;

import java.time.LocalDate;

import static br.com.riteris.octopus.utils.DateTools.*;
import static org.junit.Assert.*;

public class DateToolsTest {

    private final LocalDate firstJanuary = LocalDate.of( 2016, 1, 1 );

    private final LocalDate middleJanuary = LocalDate.of( 2016, 1, 15 );

    private final LocalDate endJanuary = LocalDate.of( 2016, 1, 31 );

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatDateToPatternWithNullDate() {
        formatDateToPattern( null, "dd/MM/yyyy" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatDateToPatternWithNullPattern() {
        formatDateToPattern( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatDateToPatternWithEmptyPattern() {
        formatDateToPattern( firstJanuary, "" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatDateToPatternWithBlankPattern() {
        formatDateToPattern( firstJanuary, " " );
    }

    @Test( expected = RuntimeException.class )
    public final void testFormatDateToPatternWithInvalidPattern() {
        formatDateToPattern( firstJanuary, "INVALID" );
    }

    @Test
    public final void testFormatDateToPattern() {
        assertEquals( formatDateToPattern( firstJanuary, "dd/MM/yyyy" ), "01/01/2016" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testGetTotalYearDaysWithNegativeYear() {
        getTotalYearDays( -2016 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testGetTotalYearDaysWithYearBeforeYearOne() {
        getTotalYearDays( 0 );
    }

    @Test
    public final void testGetTotalYearDays() {
        assertEquals( getTotalYearDays( 2016 ), 366 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateAtFirstDayOfActualDateMonthWithNullDate() {
        getDateAtFirstDayOfActualDateMonth( null );
    }

    @Test
    public final void testDateAtFirstDayOfActualDateMonth() {
        assertEquals( getDateAtFirstDayOfActualDateMonth( middleJanuary ), firstJanuary );
        assertEquals( getDateAtFirstDayOfActualDateMonth( firstJanuary ), firstJanuary );
        assertEquals( getDateAtFirstDayOfActualDateMonth( endJanuary ), firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateAtLastDayOfActualDateMonthWithnullDate() {
        getDateAtLastDayOfActualDateMonth( null );
    }

    @Test
    public final void testDateAtLastDayOfActualDateMonth() {
        assertEquals( getDateAtLastDayOfActualDateMonth( middleJanuary ), endJanuary );
        assertEquals( getDateAtLastDayOfActualDateMonth( firstJanuary ), endJanuary );
        assertEquals( getDateAtLastDayOfActualDateMonth( endJanuary ), endJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsSameDateExcludingTimeInfoWithFirstDateNull() {
        isSameDateExcludingTimeInfo( null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsSameDateExcludingTimeInfoWithSecondDateNull() {
        isSameDateExcludingTimeInfo( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsSameDateExcludingTimeInfoWithBoothDatesNull() {
        isSameDateExcludingTimeInfo( null, null );
    }

    @Test
    public final void testIsSameDateExcludingTimeInfo() {
        assertFalse( isSameDateExcludingTimeInfo( firstJanuary, middleJanuary ) );
        assertFalse( isSameDateExcludingTimeInfo( firstJanuary, endJanuary ) );
        assertTrue( isSameDateExcludingTimeInfo( firstJanuary, firstJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsBeforeExcludingTimeInfoWithNullDate() {
        dateIsBeforeExcludingTimeInfo( null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsBeforeExcludingTimeInfoWithNullReferenceDate() {
        dateIsBeforeExcludingTimeInfo( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsBeforeExcludingTimeInfoWithBoothDatesNull() {
        dateIsBeforeExcludingTimeInfo( null, null );
    }

    @Test
    public final void testDateIsBeforeExcludingTimeInfo() {
        assertTrue( dateIsBeforeExcludingTimeInfo( firstJanuary, middleJanuary ) );
        assertTrue( dateIsBeforeExcludingTimeInfo( firstJanuary, endJanuary ) );
        assertFalse( dateIsBeforeExcludingTimeInfo( firstJanuary, firstJanuary ) );
        assertFalse( dateIsBeforeExcludingTimeInfo( middleJanuary, firstJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrBeforeExcludingTimeInfoWithNullDate() {
        dateIsEqualOrBeforeExcludingTimeInfo( null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrBeforeExcludingTimeInfoWithNullReferenceDate() {
        dateIsEqualOrBeforeExcludingTimeInfo( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrBeforeExcludingTimeInfoWithBoothDatesNull() {
        dateIsEqualOrBeforeExcludingTimeInfo( null, null );
    }

    @Test
    public final void testDateIsEqualOrBeforeExcludingTimeInfo() {
        assertTrue( dateIsEqualOrBeforeExcludingTimeInfo( firstJanuary, middleJanuary ) );
        assertTrue( dateIsEqualOrBeforeExcludingTimeInfo( firstJanuary, endJanuary ) );
        assertTrue( dateIsEqualOrBeforeExcludingTimeInfo( firstJanuary, firstJanuary ) );
        assertFalse( dateIsEqualOrBeforeExcludingTimeInfo( middleJanuary, firstJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsAfterExcludingTimeInfoWithNullDate() {
        dateIsAfterExcludingTimeInfo( null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsAfterExcludingTimeInfoWithNullReferenceDate() {
        dateIsAfterExcludingTimeInfo( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsAfterExcludingTimeInfoWithBoothDatesNull() {
        dateIsAfterExcludingTimeInfo( null, null );
    }

    @Test
    public final void testDateIsAfterExcludingTimeInfo() {
        assertFalse( dateIsAfterExcludingTimeInfo( firstJanuary, middleJanuary ) );
        assertFalse( dateIsAfterExcludingTimeInfo( firstJanuary, endJanuary ) );
        assertFalse( dateIsAfterExcludingTimeInfo( middleJanuary, middleJanuary ) );
        assertTrue( dateIsAfterExcludingTimeInfo( middleJanuary, firstJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrAfterExcludingTimeInfoWithNullDate() {
        dateIsEqualOrAfterExcludingTimeInfo( null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrAfterExcludingTimeInfoWithNullReferenceDate() {
        dateIsEqualOrAfterExcludingTimeInfo( firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testDateIsEqualOrAfterExcludingTimeInfoWithBoothDatesNull() {
        dateIsEqualOrAfterExcludingTimeInfo( null, null );
    }

    @Test
    public final void testDateIsEqualOrAfterExcludingTimeInfo() {
        assertFalse( dateIsEqualOrAfterExcludingTimeInfo( firstJanuary, middleJanuary ) );
        assertFalse( dateIsEqualOrAfterExcludingTimeInfo( firstJanuary, endJanuary ) );
        assertTrue( dateIsEqualOrAfterExcludingTimeInfo( middleJanuary, middleJanuary ) );
        assertTrue( dateIsEqualOrAfterExcludingTimeInfo( middleJanuary, firstJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodInclusiveWithNullDate() {
        isDateBetweenPeriodInclusive( null, firstJanuary, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodInclusiveWithNullStartPeriodDate() {
        isDateBetweenPeriodInclusive( firstJanuary, null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodInclusiveWithNullEndPeriodDate() {
        isDateBetweenPeriodInclusive( firstJanuary, firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodInclusiveWithAllDatesNull() {
        isDateBetweenPeriodInclusive( null, null, null );
    }

    @Test
    public final void testIsDateBetweenPeriodInclusive() {
        assertTrue( isDateBetweenPeriodInclusive( firstJanuary, firstJanuary, endJanuary ) );
        assertTrue( isDateBetweenPeriodInclusive( middleJanuary, firstJanuary, endJanuary ) );
        assertTrue( isDateBetweenPeriodInclusive( endJanuary, firstJanuary, endJanuary ) );
        assertFalse( isDateBetweenPeriodInclusive( endJanuary, firstJanuary, middleJanuary ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodExclusiveWithNullDate() {
        isDateBetweenPeriodExclusive( null, firstJanuary, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodExclusiveWithNullStartPeriodDate() {
        isDateBetweenPeriodExclusive( firstJanuary, null, firstJanuary );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodExclusiveWithNullEndPeriodDate() {
        isDateBetweenPeriodExclusive( firstJanuary, firstJanuary, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testIsDateBetweenPeriodExclusiveWithAllDatesNull() {
        isDateBetweenPeriodExclusive( null, null, null );
    }

    @Test
    public final void testIsDateBetweenPeriodExclusive() {
        assertFalse( isDateBetweenPeriodExclusive( firstJanuary, firstJanuary, endJanuary ) );
        assertTrue( isDateBetweenPeriodExclusive( middleJanuary, firstJanuary, endJanuary ) );
        assertFalse( isDateBetweenPeriodExclusive( endJanuary, firstJanuary, endJanuary ) );
        assertFalse( isDateBetweenPeriodExclusive( endJanuary, firstJanuary, middleJanuary ) );
    }

}
