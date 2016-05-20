package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Testes do objeto utilitário de manipulação de Objetos.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação / Atualização em versão específica.
 * @since 1.0.0 - Criada em 10 de fev de 2016
 */
public class ObjectToolsTest {

    final private String stringNula = null;

    final private String stringVazia = "";

    final private String stringEmBranco = " ";

    final private String stringPreenchida = "Teste";

    final private Collection< String > propsWithOneProp = new ArrayList<>();

    final private Collection< String > propsWithTwoProp = new ArrayList<>();

    final private Collection< String > propsEmpty = new ArrayList<>();

    final private Collection< String > propsNull = null;

    final private Collection< String > collectionPreenchida = new ArrayList<>();

    final private Collection< String > collectionVazia = new ArrayList<>();

    final private Collection< String > collectionNula = null;

    final private Map< String, String > mapaPreenchida = new HashMap<>();

    final private Map< String, String > mapaVazio = new HashMap<>();

    final private Map< String, String > mapaNulo = null;

    final private Map< String, Collection< String > > mapaComplexoUmNivel = new HashMap<>();

    final private Map< String, Map< String, Collection< String > > > mapaComplexoDoisNiveis = new HashMap<>();

    /**
     * Preparação dos objetos utilizados no teste.
     *
     * @throws java.lang.Exception Problemas que podem ocorrer na configuração do teste.
     * @since 1.0.0 - Criada em 10 de fev de 2016
     */
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

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.ObjectTools#areEqualsUsingProperty(java.lang.Object, java.lang.Object, java.util.Collection)}
     * .
     */
    @Test
    public final void testAreEqualsUsingProperty() {
        assertFalse( ObjectTools.areEqualsUsingProperty( stringNula, stringNula, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringNula, stringVazia, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringNula, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringVazia, propsEmpty ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringVazia, propsNull ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringEmBranco, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringPreenchida, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringVazia, stringEmBranco, propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringEmBranco, stringPreenchida, propsWithOneProp ) );

        final String stringComEspacoEmBrancoEsq = " Teste";

        assertFalse( ObjectTools.areEqualsUsingProperty( stringPreenchida, stringComEspacoEmBrancoEsq,
                propsWithOneProp ) );
        assertFalse( ObjectTools.areEqualsUsingProperty( stringPreenchida, stringComEspacoEmBrancoEsq,
                propsWithTwoProp ) );
        assertTrue( ObjectTools.areEqualsUsingProperty( stringPreenchida, stringPreenchida, propsWithOneProp ) );
        assertTrue( ObjectTools.areEqualsUsingProperty( stringPreenchida, stringPreenchida, propsWithTwoProp ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.ObjectTools#getObjectDescriptionResume(java.lang.Object)} .
     */
    @Test
    public final void testGetObjectDescriptionResume() {
        assertEquals( ObjectTools.getObjectDescriptionResume( null ), "N/A: -NULL-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( stringNula ), "N/A: -NULL-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( stringVazia ), "String: -EMPTY-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( stringEmBranco ), "String: -BLANK-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( stringPreenchida ), "String: Teste" );
        assertEquals( ObjectTools.getObjectDescriptionResume( collectionPreenchida ), "ArrayList: [ String: A, " +
                "String: B, String: C ]" );
        assertEquals( ObjectTools.getObjectDescriptionResume( collectionVazia ), "ArrayList: [ -EMPTY- ]" );
        assertEquals( ObjectTools.getObjectDescriptionResume( collectionNula ), "N/A: -NULL-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( mapaPreenchida ), "HashMap: [ Key: String: 1 - Value: " +
                "String: A, Key: String: 2 - Value: String: B, Key: String: 3 - Value: String: C ]" );
        assertEquals( ObjectTools.getObjectDescriptionResume( mapaVazio ), "HashMap: [ -EMPTY- ]" );
        assertEquals( ObjectTools.getObjectDescriptionResume( mapaNulo ), "N/A: -NULL-" );
        assertEquals( ObjectTools.getObjectDescriptionResume( mapaComplexoUmNivel ), "HashMap: [ Key: String: 1A - " +
                "Value: ArrayList: [ String: AA, String: BB, String: CC ], Key: String: 1B - Value: ArrayList: [ " +
                "String: AAA, String: BBB, String: CCC ], Key: String: 1C - Value: ArrayList: [ String: AAAA, String:" +
                " BBBB, String: CCCC ] ]" );
        assertEquals( ObjectTools.getObjectDescriptionResume( mapaComplexoDoisNiveis ), "HashMap: [ Key: String: 2B -" +
                " Value: HashMap: [ Key: String: 1AA - Value: ArrayList: [ String: AA, String: BB, String: CC ] ], " +
                "Key: String: 2A - Value: HashMap: [ Key: String: 1A - Value: ArrayList: [ String: AA, String: BB, " +
                "String: CC ] ] ]" );
    }

}