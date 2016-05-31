package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static br.com.riteris.octopus.utils.NumberTools.*;
import static org.junit.Assert.assertEquals;

public class NumberToolsTest {

    private final Collection< Number > collectionWithOneValue = new ArrayList<>();

    private final Collection< Number > collectionWithTwoValues = new ArrayList<>();

    private final Collection< Number > collectionWithThreeValuesWithPeak = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        collectionWithOneValue.add( BigDecimal.TEN );
        collectionWithTwoValues.add( BigDecimal.TEN );
        collectionWithTwoValues.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( new BigDecimal( 100 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleWithNullCollection() {
        avgWithScale( null, 1 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleWithEmptyCollection() {
        avgWithScale( new ArrayList<>(), 1 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleWithNegativeScale() {
        Collection< Number > test = new ArrayList<>();
        test.add( 1 );

        avgWithScale( test, -1 );
    }

    @Test
    public final void testAvgWithScale() {
        assertEquals( avgWithScale( collectionWithOneValue, 0 ), BigDecimal.TEN );
        assertEquals( avgWithScale( collectionWithTwoValues, 0 ), BigDecimal.TEN );
        assertEquals( avgWithScale( collectionWithThreeValuesWithPeak, 0 ), new BigDecimal( 40 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleAndPeakTreatmentWithNullCollection() {
        avgWithScaleAndPeakTreatment( null, 1, 1, false );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleAndPeakTreatmentWithEmptyCollection() {
        avgWithScaleAndPeakTreatment( new ArrayList<>(), 1, 1, false );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleAndPeakTreatmentWithNegativeScale() {
        Collection< Number > test = new ArrayList<>();
        test.add( 1 );

        avgWithScaleAndPeakTreatment( test, -1, 1, false );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAvgWithScaleAndPeakTreatmentWithNegativePeakPercentual() {
        Collection< Number > test = new ArrayList<>();
        test.add( 1 );

        avgWithScaleAndPeakTreatment( test, 1, -1, false );
    }

    @Test
    public final void testAvgWithScaleAndPeakTreatmentWithPeakSubstitution() {
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithOneValue, 0, 10, true ).intValue(), 10 );
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithTwoValues, 0, 10, true ).intValue(), 10 );
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithThreeValuesWithPeak, 0, 10, true ).intValue(), 40 );
    }

    @Test
    public final void testAvgWithScaleAndPeakTreatmentWithPeakRemoval() {
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithOneValue, 0, 10, false ).intValue(), 10 );
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithTwoValues, 0, 10, false ).intValue(), 10 );
        assertEquals( avgWithScaleAndPeakTreatment( collectionWithThreeValuesWithPeak, 0, 90, false ).intValue(), 10 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatNumberToStringWithNullNumber() {
        formatNumberToString( null, 1, ",", ".", null, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFormatNumberToStringWithNegativeScale() {
        formatNumberToString( 1, -1, ",", ".", null, null );
    }

    @Test
    public final void testFormatNumberToString() {
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 1, ",", ".", null, null ), "1.000,0" );
        assertEquals( formatNumberToString( 1000, 1, ",", ".", null, null ), "1.000,0" );
        assertEquals( formatNumberToString( 1000.0d, 1, ",", ".", null, null ), "1.000,0" );
        assertEquals( formatNumberToString( 1000.0f, 1, ",", ".", null, null ), "1.000,0" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 1, ",", ".", null, null ), "100,0" );
        assertEquals( formatNumberToString( 100, 1, ",", ".", null, null ), "100,0" );
        assertEquals( formatNumberToString( 100.0d, 1, ",", ".", null, null ), "100,0" );
        assertEquals( formatNumberToString( 100.0f, 1, ",", ".", null, null ), "100,0" );
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 2, ",", ".", null, null ), "1.000,00" );
        assertEquals( formatNumberToString( 1000, 2, ",", ".", null, null ), "1.000,00" );
        assertEquals( formatNumberToString( 1000.0d, 2, ",", ".", null, null ), "1.000,00" );
        assertEquals( formatNumberToString( 1000.0f, 2, ",", ".", null, null ), "1.000,00" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 2, ",", ".", null, null ), "100,00" );
        assertEquals( formatNumberToString( 100, 2, ",", ".", null, null ), "100,00" );
        assertEquals( formatNumberToString( 100.0d, 2, ",", ".", null, null ), "100,00" );
        assertEquals( formatNumberToString( 100.0f, 2, ",", ".", null, null ), "100,00" );
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 2, ".", ",", null, null ), "1,000.00" );
        assertEquals( formatNumberToString( 1000, 2, ".", ",", null, null ), "1,000.00" );
        assertEquals( formatNumberToString( 1000.0d, 2, ".", ",", null, null ), "1,000.00" );
        assertEquals( formatNumberToString( 1000.0f, 2, ".", ",", null, null ), "1,000.00" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 2, ".", ",", null, null ), "100.00" );
        assertEquals( formatNumberToString( 100, 2, ".", ",", null, null ), "100.00" );
        assertEquals( formatNumberToString( 100.0d, 2, ".", ",", null, null ), "100.00" );
        assertEquals( formatNumberToString( 100.0f, 2, ".", ",", null, null ), "100.00" );
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 2, ".", ",", "R$ ", null ), "R$ 1,000.00" );
        assertEquals( formatNumberToString( 1000, 2, ".", ",", "R$ ", null ), "R$ 1,000.00" );
        assertEquals( formatNumberToString( 1000.0d, 2, ".", ",", "R$ ", null ), "R$ 1,000.00" );
        assertEquals( formatNumberToString( 1000.0f, 2, ".", ",", "R$ ", null ), "R$ 1,000.00" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 2, ".", ",", "R$ ", null ), "R$ 100.00" );
        assertEquals( formatNumberToString( 100, 2, ".", ",", "R$ ", null ), "R$ 100.00" );
        assertEquals( formatNumberToString( 100.0d, 2, ".", ",", "R$ ", null ), "R$ 100.00" );
        assertEquals( formatNumberToString( 100.0f, 2, ".", ",", "R$ ", null ), "R$ 100.00" );
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 2, ".", ",", null, " pts" ), "1,000.00 pts" );
        assertEquals( formatNumberToString( 1000, 2, ".", ",", null, " pts" ), "1,000.00 pts" );
        assertEquals( formatNumberToString( 1000.0d, 2, ".", ",", null, " pts" ), "1,000.00 pts" );
        assertEquals( formatNumberToString( 1000.0f, 2, ".", ",", null, " pts" ), "1,000.00 pts" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 2, ".", ",", null, " pts" ), "100.00 pts" );
        assertEquals( formatNumberToString( 100, 2, ".", ",", null, " pts" ), "100.00 pts" );
        assertEquals( formatNumberToString( 100.0d, 2, ".", ",", null, " pts" ), "100.00 pts" );
        assertEquals( formatNumberToString( 100.0f, 2, ".", ",", null, " pts" ), "100.00 pts" );
        assertEquals( formatNumberToString( new BigDecimal( 1000 ), 2, ".", ",", "R$ ", " pts" ), "R$ 1,000.00 pts" );
        assertEquals( formatNumberToString( 1000, 2, ".", ",", "R$ ", " pts" ), "R$ 1,000.00 pts" );
        assertEquals( formatNumberToString( 1000.0d, 2, ".", ",", "R$ ", " pts" ), "R$ 1,000.00 pts" );
        assertEquals( formatNumberToString( 1000.0f, 2, ".", ",", "R$ ", " pts" ), "R$ 1,000.00 pts" );
        assertEquals( formatNumberToString( new BigDecimal( 100 ), 2, ".", ",", "R$ ", " pts" ), "R$ 100.00 pts" );
        assertEquals( formatNumberToString( 100, 2, ".", ",", "R$ ", " pts" ), "R$ 100.00 pts" );
        assertEquals( formatNumberToString( 100.0d, 2, ".", ",", "R$ ", " pts" ), "R$ 100.00 pts" );
        assertEquals( formatNumberToString( 100.0f, 2, ".", ",", "R$ ", " pts" ), "R$ 100.00 pts" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testExtractNumberFromFomattedStringWithNullNumber() {
        extractNumberFromFomattedString( null, 1, ",", ".", null, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testExtractNumberFromFomattedStringWithEmptyNumber() {
        extractNumberFromFomattedString( "", 1, ",", ".", null, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testExtractNumberFromFomattedStringWithBlankNumber() {
        extractNumberFromFomattedString( " ", 1, ",", ".", null, null );
    }

    @Test( expected = RuntimeException.class )
    public final void testExtractNumberFromFomattedStringWithNotAnNumber() {
        extractNumberFromFomattedString( "A", 1, ",", ".", null, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testExtractNumberFromFomattedStringWithNegativeScale() {
        extractNumberFromFomattedString( "1", -1, ",", ".", null, null );
    }

    @Test
    public final void testExtractNumberFromFomattedString() {
        assertEquals( extractNumberFromFomattedString( "1.000,0", 1, ",", ".", null, null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "1.000,0", 1, ",", ".", null, null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "1,000.0", 1, ".", ",", null, null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "1,000.0", 1, ".", ",", null, null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,0", 1, ",", ".", "R$ ", null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,0", 1, ",", ".", "R$ ", null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.0", 1, ".", ",", "R$ ", null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.0", 1, ".", ",", "R$ ", null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,0 pts", 1, ",", ".", "R$ ", " pts" ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,0 pts", 1, ",", ".", "R$ ", " pts" ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.0 pts", 1, ".", ",", "R$ ", " pts" ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.0 pts", 1, ".", ",", "R$ ", " pts" ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "1.000,00", 2, ",", ".", null, null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "1.000,00", 2, ",", ".", null, null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "1,000.00", 2, ".", ",", null, null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "1,000.00", 2, ".", ",", null, null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,00", 2, ",", ".", "R$ ", null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,00", 2, ",", ".", "R$ ", null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.00", 2, ".", ",", "R$ ", null ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.00", 2, ".", ",", "R$ ", null ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,00 pts", 2, ",", ".", "R$ ", " pts" ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1.000,00 pts", 2, ",", ".", "R$ ", " pts" ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.00 pts", 2, ".", ",", "R$ ", " pts" ).intValue(), 1000 );
        assertEquals( extractNumberFromFomattedString( "R$ 1,000.00 pts", 2, ".", ",", "R$ ", " pts" ).doubleValue(), 1000.0d, 0 );
        assertEquals( extractNumberFromFomattedString( "1.000,0", 1, ".", ",", null, null ).intValue(), 1 );
        assertEquals( extractNumberFromFomattedString( "1.000,0", 1, ".", null, null, null ).intValue(), 1 );
        assertEquals( extractNumberFromFomattedString( "1.000,0", 1, null, null, null, null ).intValue(), 1000 );
    }

}