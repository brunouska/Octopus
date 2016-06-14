package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static br.com.riteris.octopus.utils.NumberTools.avgWithScale;
import static br.com.riteris.octopus.utils.NumberTools.avgWithScaleAndPeakTreatment;
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

}