package br.com.riteris.octopus.utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Testes do objeto utilitário de manipulação de Datas.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do teste.
 * @since 1.0.0 - Criada em 10 de fev de 2016
 */
public class DateToolsTest {

    private final LocalDate firstJanuary = LocalDate.of( 2016, 1, 1 );

    private final LocalDate middleJanuary = LocalDate.of( 2016, 1, 15 );

    private final LocalDate endJanuary = LocalDate.of( 2016, 1, 31 );

    private final LocalDate dateNull = null;

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#formatDate(java.time.temporal.TemporalAccessor, java.lang.String)}.
     */
    @Test
    public final void testFormatDate() {
        assertEquals( DateTools.formatDate( dateNull, "dd/MM/yyyy" ), "" );
        assertEquals( DateTools.formatDate( firstJanuary, null ), "" );
        assertEquals( DateTools.formatDate( firstJanuary, "invalid" ), "" );
        assertEquals( DateTools.formatDate( firstJanuary, "dd/MM/yyyy" ), "01/01/2016" );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.DateTools#getYearDaysAmount(int)}.
     */
    @Test
    public final void testGetYearDaysAmount() {
        assertEquals( DateTools.getYearDaysAmount( -2016 ), 0 );
        assertEquals( DateTools.getYearDaysAmount( 0 ), 0 );
        assertEquals( DateTools.getYearDaysAmount( 2016 ), 366 );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.DateTools#getDateAtFirstDayOfMonth(java.time.LocalDate)}.
     */
    @Test
    public final void testGetDateAtFirstDayOfMonth() {
        assertNull( DateTools.getDateAtFirstDayOfMonth( dateNull ) );
        assertEquals( DateTools.getDateAtFirstDayOfMonth( middleJanuary ), firstJanuary );
        assertEquals( DateTools.getDateAtFirstDayOfMonth( firstJanuary ), firstJanuary );
        assertEquals( DateTools.getDateAtFirstDayOfMonth( endJanuary ), firstJanuary );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.DateTools#getDateAtLastDayOfMonth(java.time.LocalDate)}.
     */
    @Test
    public final void testGetDateAtLastDayOfMonth() {
        assertNull( DateTools.getDateAtLastDayOfMonth( dateNull ) );
        assertEquals( DateTools.getDateAtLastDayOfMonth( middleJanuary ), endJanuary );
        assertEquals( DateTools.getDateAtLastDayOfMonth( firstJanuary ), endJanuary );
        assertEquals( DateTools.getDateAtLastDayOfMonth( endJanuary ), endJanuary );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isEqualDate(java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsEqualDate() {
        assertFalse( DateTools.isEqualDate( dateNull, firstJanuary ) );
        assertFalse( DateTools.isEqualDate( firstJanuary, dateNull ) );
        assertFalse( DateTools.isEqualDate( dateNull, dateNull ) );
        assertFalse( DateTools.isEqualDate( firstJanuary, middleJanuary ) );
        assertFalse( DateTools.isEqualDate( firstJanuary, endJanuary ) );
        assertTrue( DateTools.isEqualDate( firstJanuary, firstJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isBeforeDate(java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsBeforeDate() {
        assertFalse( DateTools.isBeforeDate( dateNull, firstJanuary ) );
        assertFalse( DateTools.isBeforeDate( firstJanuary, dateNull ) );
        assertFalse( DateTools.isBeforeDate( dateNull, dateNull ) );
        assertTrue( DateTools.isBeforeDate( firstJanuary, middleJanuary ) );
        assertTrue( DateTools.isBeforeDate( firstJanuary, endJanuary ) );
        assertFalse( DateTools.isBeforeDate( firstJanuary, firstJanuary ) );
        assertFalse( DateTools.isBeforeDate( middleJanuary, firstJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isEqualOrBeforeDate(java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsEqualOrBeforeDate() {
        assertFalse( DateTools.isEqualOrBeforeDate( dateNull, firstJanuary ) );
        assertFalse( DateTools.isEqualOrBeforeDate( firstJanuary, dateNull ) );
        assertFalse( DateTools.isEqualOrBeforeDate( dateNull, dateNull ) );
        assertTrue( DateTools.isEqualOrBeforeDate( firstJanuary, middleJanuary ) );
        assertTrue( DateTools.isEqualOrBeforeDate( firstJanuary, endJanuary ) );
        assertTrue( DateTools.isEqualOrBeforeDate( firstJanuary, firstJanuary ) );
        assertFalse( DateTools.isEqualOrBeforeDate( middleJanuary, firstJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isAfterDate(java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsAfterDate() {
        assertFalse( DateTools.isAfterDate( dateNull, firstJanuary ) );
        assertFalse( DateTools.isAfterDate( firstJanuary, dateNull ) );
        assertFalse( DateTools.isAfterDate( dateNull, dateNull ) );
        assertFalse( DateTools.isAfterDate( firstJanuary, middleJanuary ) );
        assertFalse( DateTools.isAfterDate( firstJanuary, endJanuary ) );
        assertFalse( DateTools.isAfterDate( middleJanuary, middleJanuary ) );
        assertTrue( DateTools.isAfterDate( middleJanuary, firstJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isEqualOrAfterDate(java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsEqualOrAfterDate() {
        assertFalse( DateTools.isEqualOrAfterDate( dateNull, firstJanuary ) );
        assertFalse( DateTools.isEqualOrAfterDate( firstJanuary, dateNull ) );
        assertFalse( DateTools.isEqualOrAfterDate( dateNull, dateNull ) );
        assertFalse( DateTools.isEqualOrAfterDate( firstJanuary, middleJanuary ) );
        assertFalse( DateTools.isEqualOrAfterDate( firstJanuary, endJanuary ) );
        assertTrue( DateTools.isEqualOrAfterDate( middleJanuary, middleJanuary ) );
        assertTrue( DateTools.isEqualOrAfterDate( middleJanuary, firstJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isDateBetweenPeriodInclusive(java.time.LocalDate, java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsDateBetweenPeriodInclusive() {
        assertFalse( DateTools.isDateBetweenPeriodInclusive( dateNull, firstJanuary, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodInclusive( firstJanuary, dateNull, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodInclusive( firstJanuary, firstJanuary, dateNull ) );
        assertFalse( DateTools.isDateBetweenPeriodInclusive( firstJanuary, dateNull, dateNull ) );
        assertTrue( DateTools.isDateBetweenPeriodInclusive( firstJanuary, firstJanuary, endJanuary ) );
        assertTrue( DateTools.isDateBetweenPeriodInclusive( middleJanuary, firstJanuary, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodInclusive( endJanuary, firstJanuary, middleJanuary ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.DateTools#isDateBetweenPeriodExclusive(java.time.LocalDate, java.time.LocalDate, java.time.LocalDate)}.
     */
    @Test
    public final void testIsDateBetweenPeriodExclusive() {
        assertFalse( DateTools.isDateBetweenPeriodExclusive( dateNull, firstJanuary, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodExclusive( firstJanuary, dateNull, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodExclusive( firstJanuary, firstJanuary, dateNull ) );
        assertFalse( DateTools.isDateBetweenPeriodExclusive( firstJanuary, dateNull, dateNull ) );
        assertFalse( DateTools.isDateBetweenPeriodExclusive( firstJanuary, firstJanuary, endJanuary ) );
        assertTrue( DateTools.isDateBetweenPeriodExclusive( middleJanuary, firstJanuary, endJanuary ) );
        assertFalse( DateTools.isDateBetweenPeriodExclusive( endJanuary, firstJanuary, middleJanuary ) );
    }

}
