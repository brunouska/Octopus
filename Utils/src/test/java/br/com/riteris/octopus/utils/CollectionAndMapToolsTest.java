package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testes do objeto utilitário de manipulação de coleçoes e mapas.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação dos testes.
 * @since 1.0.0 - Criada em 14 de out de 2015
 */
public class CollectionAndMapToolsTest {

    final private Collection< String > collectionNula = null;

    final private Collection< String > collectionVazia = new ArrayList<>();

    final private Collection< String > collectionComUmElemento = new ArrayList<>();

    final private Map< String, String > mapaNulo = null;

    final private Map< String, String > mapaVazio = new HashMap<>();

    final private Map< String, String > mapaComUmElemento = new HashMap<>();

    /**
     * Preparação dos objetos utilizados no teste.
     *
     * @throws java.lang.Exception Problemas que podem ocorrer na configuração do teste.
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    @Before
    public void setUp() throws Exception {
        this.collectionComUmElemento.add( "Teste" );
        this.mapaComUmElemento.put( "Teste", "Teste" );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.CollectionAndMapTools#collectionIsNullOrEmpty(java.util.Collection)}.
     */
    @Test
    public final void testCollectionIsNullOrEmpty() {
        assertTrue( CollectionAndMapTools.collectionIsNullOrEmpty( null ) );
        assertTrue( CollectionAndMapTools.collectionIsNullOrEmpty( this.collectionNula ) );
        assertTrue( CollectionAndMapTools.collectionIsNullOrEmpty( this.collectionVazia ) );
        assertFalse( CollectionAndMapTools.collectionIsNullOrEmpty( this.collectionComUmElemento ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.CollectionAndMapTools#mapIsNullOrEmpty(java.util.Map)}.
     */
    @Test
    public final void testMapIsNullOrEmpty() {
        assertTrue( CollectionAndMapTools.mapIsNullOrEmpty( null ) );
        assertTrue( CollectionAndMapTools.mapIsNullOrEmpty( this.mapaNulo ) );
        assertTrue( CollectionAndMapTools.mapIsNullOrEmpty( this.mapaVazio ) );
        assertFalse( CollectionAndMapTools.mapIsNullOrEmpty( this.mapaComUmElemento ) );
    }

}
