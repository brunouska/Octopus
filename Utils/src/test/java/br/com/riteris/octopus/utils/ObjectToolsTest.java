package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.com.riteris.octopus.utils.ObjectTools.areEqualsUsingProperty;
import static br.com.riteris.octopus.utils.ObjectTools.getObjectDescriptionResume;
import static org.junit.Assert.*;

public class ObjectToolsTest {

    final private String stringPreenchida = "Teste";

    final private Collection< String > propsWithOneProp = new ArrayList<>();

    final private Collection< String > propsWithTwoProp = new ArrayList<>();

    final private Collection< String > collectionPreenchida = new ArrayList<>();

    final private Map< String, String > mapaPreenchida = new HashMap<>();

    final private Map< String, Collection< String > > mapaComplexoUmNivel = new HashMap<>();

    final private Map< String, Map< String, Collection< String > > > mapaComplexoDoisNiveis = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        propsWithOneProp.add( "value" );
        propsWithTwoProp.add( "value" );
        propsWithTwoProp.add( "hash" );

        collectionPreenchida.add( "A" );
        collectionPreenchida.add( "B" );
        collectionPreenchida.add( "C" );

        mapaPreenchida.put( "1", "A" );
        mapaPreenchida.put( "2", "B" );
        mapaPreenchida.put( "3", "C" );

        mapaComplexoUmNivel.put( "1A", new ArrayList<>() );
        mapaComplexoUmNivel.get( "1A" ).add( "AA" );
        mapaComplexoUmNivel.get( "1A" ).add( "BB" );
        mapaComplexoUmNivel.get( "1A" ).add( "CC" );
        mapaComplexoUmNivel.put( "1B", new ArrayList<>() );
        mapaComplexoUmNivel.get( "1B" ).add( "AAA" );
        mapaComplexoUmNivel.get( "1B" ).add( "BBB" );
        mapaComplexoUmNivel.get( "1B" ).add( "CCC" );
        mapaComplexoUmNivel.put( "1C", new ArrayList<>() );
        mapaComplexoUmNivel.get( "1C" ).add( "AAAA" );
        mapaComplexoUmNivel.get( "1C" ).add( "BBBB" );
        mapaComplexoUmNivel.get( "1C" ).add( "CCCC" );

        mapaComplexoDoisNiveis.put( "2A", new HashMap<>() );
        mapaComplexoDoisNiveis.get( "2A" ).put( "1A", new ArrayList<>() );
        mapaComplexoDoisNiveis.get( "2A" ).get( "1A" ).add( "AA" );
        mapaComplexoDoisNiveis.get( "2A" ).get( "1A" ).add( "BB" );
        mapaComplexoDoisNiveis.get( "2A" ).get( "1A" ).add( "CC" );
        mapaComplexoDoisNiveis.put( "2B", new HashMap<>() );
        mapaComplexoDoisNiveis.get( "2B" ).put( "1AA", new ArrayList<>() );
        mapaComplexoDoisNiveis.get( "2B" ).get( "1AA" ).add( "AA" );
        mapaComplexoDoisNiveis.get( "2B" ).get( "1AA" ).add( "BB" );
        mapaComplexoDoisNiveis.get( "2B" ).get( "1AA" ).add( "CC" );
    }

    @Test
    public final void testAreEqualsUsingPropertyWithNullObjectA() {
        assertFalse( areEqualsUsingProperty( null, stringPreenchida, propsWithOneProp ) );
    }

    @Test
    public final void testAreEqualsUsingPropertyWithNullObjectB() {
        assertFalse( areEqualsUsingProperty( stringPreenchida, null, propsWithOneProp ) );
    }

    @Test
    public final void testAreEqualsUsingPropertyWithNullObjects() {
        assertFalse( areEqualsUsingProperty( null, null, propsWithOneProp ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAreEqualsUsingPropertyWithNullPropertiesMap() {
        assertFalse( areEqualsUsingProperty( stringPreenchida, stringPreenchida, null ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testAreEqualsUsingPropertyWithEmptyPropertiesMap() {
        assertFalse( areEqualsUsingProperty( stringPreenchida, stringPreenchida, new ArrayList<>() ) );
    }

    @Test
    public final void testAreEqualsUsingProperty() {
        final String stringComEspacoEmBrancoEsq = " Teste";

        assertFalse( areEqualsUsingProperty( stringPreenchida, stringComEspacoEmBrancoEsq, propsWithOneProp ) );
        assertFalse( areEqualsUsingProperty( stringPreenchida, stringComEspacoEmBrancoEsq, propsWithTwoProp ) );
        assertTrue( areEqualsUsingProperty( stringPreenchida, stringPreenchida, propsWithOneProp ) );
        assertTrue( areEqualsUsingProperty( stringPreenchida, stringPreenchida, propsWithTwoProp ) );
    }

    @Test
    public final void testGetObjectDescriptionResume() {
        assertEquals( getObjectDescriptionResume( null ), "N/A: -NULL-" );
        assertEquals( getObjectDescriptionResume( "" ), "String: -EMPTY-" );
        assertEquals( getObjectDescriptionResume( " " ), "String: -BLANK-" );
        assertEquals( getObjectDescriptionResume( stringPreenchida ), "String: Teste" );
        assertEquals( getObjectDescriptionResume( collectionPreenchida ), "ArrayList: [ String: A, String: B, String: C ]" );
        assertEquals( getObjectDescriptionResume( new ArrayList<>() ), "ArrayList: [ -EMPTY- ]" );
        assertEquals( getObjectDescriptionResume( null ), "N/A: -NULL-" );
        assertEquals( getObjectDescriptionResume( mapaPreenchida ), "HashMap: [ Key: String: 1 - Value: String: A, Key: String: 2 - Value: String: B, Key: " +
                "String: 3 - Value: String: C ]" );
        assertEquals( getObjectDescriptionResume( new HashMap<>() ), "HashMap: [ -EMPTY- ]" );
        assertEquals( getObjectDescriptionResume( null ), "N/A: -NULL-" );
        assertEquals( getObjectDescriptionResume( mapaComplexoUmNivel ), "HashMap: [ Key: String: 1A - Value: ArrayList: [ String: AA, String: BB, String: " +
                "CC ], Key: String: 1B - Value: ArrayList: [ String: AAA, String: BBB, String: CCC ], Key: String: 1C - Value: ArrayList: [ String: AAAA, String: BBBB," +
                " String: CCCC ] ]" );
        assertEquals( getObjectDescriptionResume( mapaComplexoDoisNiveis ), "HashMap: [ Key: String: 2B - Value: HashMap: [ Key: String: 1AA - Value: " +
                "ArrayList: [ String: AA, String: BB, String: CC ] ], Key: String: 2A - Value: HashMap: [ Key: String: 1A - Value: ArrayList: [ String: AA, String: BB," +
                " String: CC ] ] ]" );
    }

}