package br.com.riteris.octopus.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.com.riteris.octopus.utils.StringTools.*;
import static org.junit.Assert.*;

public class StringToolsTest {

    @Test
    public final void testStringIsNullOrEmpty() {
        assertTrue( stringIsNullOrEmpty( null ) );
        assertTrue( stringIsNullOrEmpty( "" ) );
        assertFalse( stringIsNullOrEmpty( " " ) );
        assertFalse( stringIsNullOrEmpty( "foo" ) );
    }

    @Test
    public final void testStringIsBlank() {
        assertFalse( stringIsBlank( null ) );
        assertFalse( stringIsBlank( "" ) );
        assertTrue( stringIsBlank( " " ) );
        assertFalse( stringIsBlank( "foo" ) );
    }

    @Test
    public final void testStringIsNullOrEmptyOrBlank() {
        assertTrue( stringIsNullOrEmptyOrBlank( null ) );
        assertTrue( stringIsNullOrEmptyOrBlank( "" ) );
        assertTrue( stringIsNullOrEmptyOrBlank( " " ) );
        assertFalse( stringIsNullOrEmptyOrBlank( "foo" ) );
    }

    @Test
    public final void testTrimStringAtLeft() {
        assertEquals( null, trimStringAtLeft( null ) );
        assertEquals( "", trimStringAtLeft( "" ) );
        assertEquals( "", trimStringAtLeft( " " ) );
        assertEquals( "foo", trimStringAtLeft( "foo" ) );
        assertEquals( "foo", trimStringAtLeft( " foo" ) );
        assertEquals( "foo ", trimStringAtLeft( " foo " ) );
    }

    @Test
    public final void testTrimStringAtRight() {
        assertEquals( null, trimStringAtRight( null ) );
        assertEquals( "", trimStringAtRight( "" ) );
        assertEquals( "", trimStringAtRight( " " ) );
        assertEquals( "foo", trimStringAtRight( "foo" ) );
        assertEquals( "foo", trimStringAtRight( "foo " ) );
        assertEquals( " foo", trimStringAtRight( " foo " ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAddBlankSpacesToStringWithZeroAtSpacesNumber() {
        addBlankSpacesToString( null, 0, true, true );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAddBlankSpacesToStringWithNegativeSpacesNumber() {
        addBlankSpacesToString( null, -1, true, true );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAddBlankSpacesToStringWithNoSideSelected() {
        addBlankSpacesToString( null, 1, false, false );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAddBlankSpacesToStringWithAllConditionsWrong() {
        addBlankSpacesToString( null, -1, false, false );
    }

    @Test
    public final void testAddBlankSpacesToString() {
        assertEquals( "  ", addBlankSpacesToString( null, 1, true, true ) );
        assertEquals( " ", addBlankSpacesToString( null, 1, true, false ) );
        assertEquals( " ", addBlankSpacesToString( null, 1, false, true ) );

        assertEquals( "  ", addBlankSpacesToString( "", 1, true, true ) );
        assertEquals( " ", addBlankSpacesToString( "", 1, true, false ) );
        assertEquals( " ", addBlankSpacesToString( "", 1, false, true ) );

        assertEquals( "   ", addBlankSpacesToString( " ", 1, true, true ) );
        assertEquals( "  ", addBlankSpacesToString( " ", 1, true, false ) );
        assertEquals( "  ", addBlankSpacesToString( " ", 1, false, true ) );

        assertEquals( " foo ", addBlankSpacesToString( "foo", 1, true, true ) );
        assertEquals( " foo", addBlankSpacesToString( "foo", 1, true, false ) );
        assertEquals( "foo ", addBlankSpacesToString( "foo", 1, false, true ) );

        assertEquals( "  foo  ", addBlankSpacesToString( "foo", 2, true, true ) );
        assertEquals( "  foo", addBlankSpacesToString( "foo", 2, true, false ) );
        assertEquals( "foo  ", addBlankSpacesToString( "foo", 2, false, true ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFillStringAtLeftWithBlankSpacesToDesiredLengthWithZeroLength() {
        fillStringAtLeftWithBlankSpacesToDesiredLength( null, 0 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFillStringAtLeftWithBlankSpacesToDesiredLengthWithNegativeLength() {
        fillStringAtLeftWithBlankSpacesToDesiredLength( null, -1 );
    }

    @Test
    public final void testFillStringAtLeftWithBlankSpacesToDesiredLength() {
        assertEquals( " ", fillStringAtLeftWithBlankSpacesToDesiredLength( null, 1 ) );
        assertEquals( "  ", fillStringAtLeftWithBlankSpacesToDesiredLength( null, 2 ) );

        assertEquals( " ", fillStringAtLeftWithBlankSpacesToDesiredLength( "", 1 ) );
        assertEquals( "  ", fillStringAtLeftWithBlankSpacesToDesiredLength( "", 2 ) );

        assertEquals( "foo", fillStringAtLeftWithBlankSpacesToDesiredLength( "foo", 1 ) );
        assertEquals( "foo", fillStringAtLeftWithBlankSpacesToDesiredLength( "foo", 3 ) );

        assertEquals( " a", fillStringAtLeftWithBlankSpacesToDesiredLength( "a", 2 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFillStringAtRightWithBlankSpacesToDesiredLengthWithZeroLength() {
        fillStringAtRightWithBlankSpacesToDesiredLength( null, 0 );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testFillStringAtRightWithBlankSpacesToDesiredLengthWithNegativeLength() {
        fillStringAtRightWithBlankSpacesToDesiredLength( null, -1 );
    }

    @Test
    public final void testFillStringAtRightWithBlankSpacesToDesiredLength() {
        assertEquals( " ", fillStringAtRightWithBlankSpacesToDesiredLength( null, 1 ) );
        assertEquals( "  ", fillStringAtRightWithBlankSpacesToDesiredLength( null, 2 ) );

        assertEquals( " ", fillStringAtRightWithBlankSpacesToDesiredLength( "", 1 ) );
        assertEquals( "  ", fillStringAtRightWithBlankSpacesToDesiredLength( "", 2 ) );

        assertEquals( "foo", fillStringAtRightWithBlankSpacesToDesiredLength( "foo", 1 ) );
        assertEquals( "foo", fillStringAtRightWithBlankSpacesToDesiredLength( "foo", 3 ) );

        assertEquals( "a ", fillStringAtRightWithBlankSpacesToDesiredLength( "a", 2 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithNullSource() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( null, map, "i18n<", ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithNullMap() {
        replacePartsInStringUsingMapAndDelimiters( "", null, "i18n<", ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithEmptyMap() {
        replacePartsInStringUsingMapAndDelimiters( "", new HashMap<>(), "i18n<", ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithNullLeftDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, null, ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithEmptyLeftDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, "", ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithBlankLeftDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, " ", ">i18n" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithNullRightDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, "i18n<", null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithEmptyRightDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, "i18n<", "" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testReplacePartsInStringUsingMapAndDelimitersWithBlankRightDelimiter() {
        final HashMap< String, String > map = new HashMap<>();
        map.put( "", "" );

        replacePartsInStringUsingMapAndDelimiters( "", map, "i18n<", " " );
    }

    @Test
    public final void testReplacePartsInStringUsingMapAndDelimiters() {
        final String ld = "i18n<";
        final String rd = ">i18n";

        final Map< String, String > replacementMap = new HashMap<>();
        replacementMap.put( "dog", "cat" );
        replacementMap.put( "mouse", "pig" );

        final String phraseWithoutI18n = "The dog bark at the moon with other dogs at night.";

        final String phraseA = "The i18n<dog>i18n bark at the moon with other dogs at night.";
        final String phraseAresultExpected = "The cat bark at the moon with other dogs at night.";

        final String phraseB = "The i18n<dog>i18n bark at the moon with other i18n<dog>i18ns at night.";
        final String phraseBresultExpected = "The cat bark at the moon with other cats at night.";

        final String phraseC = "The i18n<cat>i18n bark at the moon with other i18n<dog>i18ns at night.";
        final String phraseCresultExpected = "The i18n<cat>i18n bark at the moon with other cats at night.";

        final String phraseD = "The i18n<mouse>i18n does óinc!.";
        final String phraseDresultExpected = "The pig does óinc!.";

        final String phraseE = "i18n<mouse>i18n";
        final String phraseEresultExpected = "pig";

        final String phraseF = "The i18n<mouse>i18n\r\ndoes óinc!.";
        final String phraseFresultExpected = "The pig\r\ndoes óinc!.";


        assertEquals( "", replacePartsInStringUsingMapAndDelimiters( "", replacementMap, ld, rd ) );
        assertEquals( phraseWithoutI18n, replacePartsInStringUsingMapAndDelimiters( phraseWithoutI18n, replacementMap, ld, rd ) );

        assertEquals( phraseA, replacePartsInStringUsingMapAndDelimiters( phraseA, replacementMap, "<<", ">>" ) );
        assertEquals( phraseAresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseA, replacementMap, ld, rd ) );
        assertEquals( phraseBresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseB, replacementMap, ld, rd ) );
        assertEquals( phraseCresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseC, replacementMap, ld, rd ) );
        assertEquals( phraseDresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseD, replacementMap, ld, rd ) );
        assertEquals( phraseEresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseE, replacementMap, ld, rd ) );
        assertEquals( phraseFresultExpected, replacePartsInStringUsingMapAndDelimiters( phraseF, replacementMap, ld, rd ) );
    }

}
