package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testes do objeto utilitário de manipulação de números.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação / Atualização em versão específica.
 * @since 1.0.0 - Criada em 10 de fev de 2016
 */
public class NumberToolsTest {

    private final Collection< BigDecimal > nullCollection = null;

    private final Collection< BigDecimal > emptyCollection = new ArrayList<>();

    private final Collection< BigDecimal > collectionWithOneValue = new ArrayList<>();

    private final Collection< BigDecimal > collectionWithTwoValues = new ArrayList<>();

    private final Collection< BigDecimal > collectionWithThreeValuesWithPeak = new ArrayList<>();

    /**
     * Preparação dos objetos utilizados no teste.
     *
     * @throws java.lang.Exception Problemas que podem ocorrer na configuração do teste.
     * @since 1.0.0 - Criada em 10 de fev de 2016
     */
    @Before
    public void setUp() throws Exception {
        collectionWithOneValue.add( BigDecimal.TEN );
        collectionWithTwoValues.add( BigDecimal.TEN );
        collectionWithTwoValues.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( BigDecimal.TEN );
        collectionWithThreeValuesWithPeak.add( new BigDecimal( 100 ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.NumberTools#simpleAverage(java.util.Collection, int)}.
     */
    @Test
    public final void testSimpleAverage() {
        assertNull( NumberTools.simpleAverage( nullCollection, 1 ) );
        assertNull( NumberTools.simpleAverage( emptyCollection, 1 ) );
        assertNull( NumberTools.simpleAverage( nullCollection, -1 ) );
        assertEquals( NumberTools.simpleAverage( collectionWithOneValue, 0 ), BigDecimal.TEN );
        assertEquals( NumberTools.simpleAverage( collectionWithTwoValues, 0 ), BigDecimal.TEN );
        assertEquals( NumberTools.simpleAverage( collectionWithThreeValuesWithPeak, 0 ), new BigDecimal( 40 ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.NumberTools#averageWithPeakExchange(java.util.Collection, int, java.math.BigDecimal)}.
     */
    @Test
    public final void testAverageWithPeakExchange() {
        assertNull( NumberTools.averageWithPeakExchange( nullCollection, 1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakExchange( emptyCollection, 1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakExchange( nullCollection, -1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakExchange( emptyCollection, 1, null ) );
        assertEquals( NumberTools.averageWithPeakExchange( collectionWithOneValue, 0, new BigDecimal( 10 ) ),
                BigDecimal.TEN );
        assertEquals( NumberTools.averageWithPeakExchange( collectionWithTwoValues, 0, new BigDecimal( 10 ) ),
                BigDecimal.TEN );
        assertEquals( NumberTools.averageWithPeakExchange( collectionWithThreeValuesWithPeak, 0, new BigDecimal( 10 )
        ), new BigDecimal( 40 ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.NumberTools#averageWithPeakCutOff(java.util.Collection, int, java.math.BigDecimal)}.
     */
    @Test
    public final void testAverageWithPeakCutOff() {
        assertNull( NumberTools.averageWithPeakCutOff( nullCollection, 1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakCutOff( emptyCollection, 1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakCutOff( nullCollection, -1, new BigDecimal( 10 ) ) );
        assertNull( NumberTools.averageWithPeakCutOff( emptyCollection, 1, null ) );
        assertEquals( NumberTools.averageWithPeakCutOff( collectionWithOneValue, 0, new BigDecimal( 10 ) ),
                BigDecimal.TEN );
        assertEquals( NumberTools.averageWithPeakCutOff( collectionWithTwoValues, 0, new BigDecimal( 10 ) ),
                BigDecimal.TEN );
        assertEquals( NumberTools.averageWithPeakCutOff( collectionWithThreeValuesWithPeak, 0, new BigDecimal( 90 ) )
                , BigDecimal.TEN );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.NumberTools#getNumberFormatted(java.lang.Object, int, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testGetNumberFormatted() {
        assertNull( NumberTools.getNumberFormatted( null, 1, ",", "." ) );
        assertNull( NumberTools.getNumberFormatted( "Not a Number", 1, null, null ) );
        assertNull( NumberTools.getNumberFormatted( 1000, -1, null, null ) );
        assertEquals( NumberTools.getNumberFormatted( new BigDecimal( 1000 ), 1, ",", "." ), "1.000,0" );
        assertEquals( NumberTools.getNumberFormatted( 1000, 1, ",", "." ), "1.000,0" );
        assertEquals( NumberTools.getNumberFormatted( 1000.0d, 1, ",", "." ), "1.000,0" );
        assertEquals( NumberTools.getNumberFormatted( 1000.0f, 1, ",", "." ), "1.000,0" );
        assertEquals( NumberTools.getNumberFormatted( new BigDecimal( 100 ), 1, ",", "." ), "100,0" );
        assertEquals( NumberTools.getNumberFormatted( 100, 1, ",", "." ), "100,0" );
        assertEquals( NumberTools.getNumberFormatted( 100.0d, 1, ",", "." ), "100,0" );
        assertEquals( NumberTools.getNumberFormatted( 100.0f, 1, ",", "." ), "100,0" );
    }

}
