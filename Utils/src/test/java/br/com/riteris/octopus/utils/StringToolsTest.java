package br.com.riteris.octopus.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Testes do objeto utilitário de manipulação de Strings.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação dos testes.
 * @since 1.0.0 - Criada em 14 de out de 2015
 */
public class StringToolsTest {

    final private String stringNula = null;

    final private String stringVazia = "";

    final private String stringEmBranco = " ";

    final private String stringPreenchida = "Teste";

    final private String stringComEspacoEmBrancoEsq = " Teste";

    final private String stringComEspacoEmBrancoDir = "Teste ";

    final private String stringComEspacosEmBrancoEsqDir = " Teste ";

    final private Collection< String > coleçãoVazia = new ArrayList<>();

    final private Collection< String > coleçãoComStringVazia = new ArrayList<>();

    final private Collection< String > coleçãoComUmElemento = new ArrayList<>();

    final private Collection< String > coleçãoComDoisElementosRepetidos = new ArrayList<>();

    final private Collection< String > coleçãoComDoisElementos = new ArrayList<>();

    final private Map< String, String > mapaSubstituicaoNulo = null;

    final private Map< String, String > mapaSubstituicaoVazio = new HashMap<>();

    final private Map< String, String > mapaSubstituicaoComUmElemento = new HashMap<>();

    final private Map< String, String > mapaSubstituicaoComDoisElementos = new HashMap<>();

    /**
     * Preparação dos objetos utilizados no teste.
     *
     * @throws java.lang.Exception Problemas que podem ocorrer na configuração do teste.
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    @Before
    public void setUp() throws Exception {
        this.coleçãoComStringVazia.add( "" );
        this.coleçãoComUmElemento.add( this.stringPreenchida );
        this.coleçãoComDoisElementosRepetidos.add( this.stringPreenchida );
        this.coleçãoComDoisElementosRepetidos.add( this.stringPreenchida );
        this.coleçãoComDoisElementos.add( this.stringPreenchida );
        this.coleçãoComDoisElementos.add( this.stringComEspacoEmBrancoEsq );
        this.mapaSubstituicaoComUmElemento.put( "TESTE_SUBSTITUIR", "Parte 1" );
        this.mapaSubstituicaoComDoisElementos.put( "TESTE_SUBSTITUIR", "Parte 1" );
        this.mapaSubstituicaoComDoisElementos.put( "TESTE_SUBSTITUIR_2", "Parte 2" );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#isNull(java.lang.String)}.
     */
    @Test
    public final void testIsNull() {
        assertTrue( StringTools.isNull( null ) );
        assertTrue( StringTools.isNull( this.stringNula ) );
        assertFalse( StringTools.isNull( this.stringVazia ) );
        assertFalse( StringTools.isNull( this.stringEmBranco ) );
        assertFalse( StringTools.isNull( this.stringPreenchida ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#isNullOrEmpty(java.lang.String)}.
     */
    @Test
    public final void testIsNullOrEmpty() {
        assertTrue( StringTools.isNullOrEmpty( null ) );
        assertTrue( StringTools.isNullOrEmpty( this.stringNula ) );
        assertTrue( StringTools.isNullOrEmpty( this.stringVazia ) );
        assertFalse( StringTools.isNullOrEmpty( this.stringEmBranco ) );
        assertFalse( StringTools.isNullOrEmpty( this.stringPreenchida ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#isBlank(java.lang.String)}.
     */
    @Test
    public final void testIsBlank() {
        assertFalse( StringTools.isBlank( null ) );
        assertFalse( StringTools.isBlank( this.stringNula ) );
        assertFalse( StringTools.isBlank( this.stringVazia ) );
        assertTrue( StringTools.isBlank( this.stringEmBranco ) );
        assertFalse( StringTools.isBlank( this.stringPreenchida ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#isNullEmptyOrBlank(java.lang.String)}.
     */
    @Test
    public final void testIsNullOrEmptyOrBlank() {
        assertTrue( StringTools.isNullEmptyOrBlank( null ) );
        assertTrue( StringTools.isNullEmptyOrBlank( this.stringNula ) );
        assertTrue( StringTools.isNullEmptyOrBlank( this.stringVazia ) );
        assertTrue( StringTools.isNullEmptyOrBlank( this.stringEmBranco ) );
        assertFalse( StringTools.isNullEmptyOrBlank( this.stringPreenchida ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#trimLeft(java.lang.String)}.
     */
    @Test
    public final void testTrimLeft() {
        assertEquals( null, StringTools.trimLeft( null ) );
        assertEquals( this.stringNula, StringTools.trimLeft( this.stringNula ) );
        assertEquals( this.stringVazia, StringTools.trimLeft( this.stringVazia ) );
        assertEquals( this.stringVazia, StringTools.trimLeft( this.stringEmBranco ) );
        assertEquals( this.stringPreenchida, StringTools.trimLeft( this.stringPreenchida ) );
        assertEquals( this.stringPreenchida, StringTools.trimLeft( this.stringComEspacoEmBrancoEsq ) );
        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.trimLeft( this.stringComEspacoEmBrancoDir ) );
        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.trimLeft( this.stringComEspacosEmBrancoEsqDir ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#trimRight(java.lang.String)}.
     */
    @Test
    public final void testTrimRight() {
        assertEquals( null, StringTools.trimRight( null ) );
        assertEquals( this.stringNula, StringTools.trimRight( this.stringNula ) );
        assertEquals( this.stringVazia, StringTools.trimRight( this.stringVazia ) );
        assertEquals( this.stringVazia, StringTools.trimRight( this.stringEmBranco ) );
        assertEquals( this.stringPreenchida, StringTools.trimRight( this.stringPreenchida ) );
        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.trimRight( this.stringComEspacoEmBrancoEsq ) );
        assertEquals( this.stringPreenchida, StringTools.trimRight( this.stringComEspacoEmBrancoDir ) );
        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.trimRight( this.stringComEspacosEmBrancoEsqDir ) );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.utils.StringTools#trim(java.lang.String)}.
     */
    @Test
    public final void testTrim() {
        assertEquals( null, StringTools.trim( null ) );
        assertEquals( this.stringNula, StringTools.trim( this.stringNula ) );
        assertEquals( this.stringVazia, StringTools.trim( this.stringVazia ) );
        assertEquals( this.stringVazia, StringTools.trim( this.stringEmBranco ) );
        assertEquals( this.stringPreenchida, StringTools.trim( this.stringPreenchida ) );
        assertEquals( this.stringPreenchida, StringTools.trim( this.stringComEspacoEmBrancoEsq ) );
        assertEquals( this.stringPreenchida, StringTools.trim( this.stringComEspacoEmBrancoDir ) );
        assertEquals( this.stringPreenchida, StringTools.trim( this.stringComEspacosEmBrancoEsqDir ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#addBlankSpaces(java.lang.String, int, boolean, boolean)}.
     */
    @Test
    public final void testAddBlankSpaces() {
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( null, 1, true, false ) );
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( null, 1, false, true ) );
        assertEquals( "  ", StringTools.addBlankSpaces( null, 1, true, true ) );

        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringNula, 1, true, false ) );
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringNula, 1, false, true ) );
        assertEquals( "  ", StringTools.addBlankSpaces( this.stringNula, 1, true, true ) );

        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringVazia, 1, true, false ) );
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringVazia, 1, false, true ) );
        assertEquals( "  ", StringTools.addBlankSpaces( this.stringVazia, 1, true, true ) );

        assertEquals( "  ", StringTools.addBlankSpaces( this.stringEmBranco, 1, true, false ) );
        assertEquals( "  ", StringTools.addBlankSpaces( this.stringEmBranco, 1, false, true ) );
        assertEquals( "   ", StringTools.addBlankSpaces( this.stringEmBranco, 1, true, true ) );

        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.addBlankSpaces( this.stringPreenchida, 1, true,
                false ) );
        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.addBlankSpaces( this.stringPreenchida, 1, false,
                true ) );
        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.addBlankSpaces( this.stringPreenchida, 1,
                true, true ) );

        assertEquals( ( " " + this.stringComEspacoEmBrancoEsq ), StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoEsq, 1, true, false ) );
        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoEsq, 1, false, true ) );
        assertEquals( ( " " + this.stringComEspacosEmBrancoEsqDir ), StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoEsq, 1, true, true ) );

        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoDir, 1, true, false ) );
        assertEquals( ( this.stringComEspacoEmBrancoDir + " " ), StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoDir, 1, false, true ) );
        assertEquals( ( this.stringComEspacosEmBrancoEsqDir + " " ), StringTools.addBlankSpaces( this
                .stringComEspacoEmBrancoDir, 1, true, true ) );

        assertEquals( ( " " + this.stringComEspacosEmBrancoEsqDir ), StringTools.addBlankSpaces( this
                .stringComEspacosEmBrancoEsqDir, 1, true, false ) );
        assertEquals( ( this.stringComEspacosEmBrancoEsqDir + " " ), StringTools.addBlankSpaces( this
                .stringComEspacosEmBrancoEsqDir, 1, false, true ) );
        assertEquals( ( " " + this.stringComEspacosEmBrancoEsqDir + " " ), StringTools.addBlankSpaces( this
                .stringComEspacosEmBrancoEsqDir, 1, true, true ) );

        assertEquals( null, StringTools.addBlankSpaces( null, -1, true, false ) );
        assertEquals( null, StringTools.addBlankSpaces( null, 1, false, false ) );
        assertEquals( null, StringTools.addBlankSpaces( null, -1, false, false ) );

        assertEquals( this.stringNula, StringTools.addBlankSpaces( this.stringNula, -1, true, false ) );
        assertEquals( this.stringNula, StringTools.addBlankSpaces( this.stringNula, 1, false, false ) );
        assertEquals( this.stringNula, StringTools.addBlankSpaces( this.stringNula, -1, false, false ) );

        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringEmBranco, -1, true, false ) );
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringEmBranco, 1, false, false ) );
        assertEquals( this.stringEmBranco, StringTools.addBlankSpaces( this.stringEmBranco, -1, false, false ) );

        assertEquals( this.stringPreenchida, StringTools.addBlankSpaces( this.stringPreenchida, -1, true, false ) );
        assertEquals( this.stringPreenchida, StringTools.addBlankSpaces( this.stringPreenchida, 1, false, false ) );
        assertEquals( this.stringPreenchida, StringTools.addBlankSpaces( this.stringPreenchida, -1, false, false ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#fillLeftWithBlankSpacesToDesiredLength(java.lang.String, int)}.
     */
    @Test
    public final void testFillLeftWithBlankSpacesToDesiredLength() {
        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( null, 1 ) );
        assertEquals( "  ", StringTools.fillLeftWithBlankSpacesToDesiredLength( null, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringNula, 1 ) );
        assertEquals( "  ", StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringNula, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringVazia, 1 ) );
        assertEquals( "  ", StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringVazia, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringEmBranco, 1
        ) );
        assertEquals( "  ", StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringEmBranco, 2 ) );

        assertEquals( this.stringPreenchida, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 5 ) );
        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 6 ) );

        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoEsq, 6 ) );
        assertEquals( ( " " + this.stringComEspacoEmBrancoEsq ), StringTools.fillLeftWithBlankSpacesToDesiredLength(
                this.stringComEspacoEmBrancoEsq, 7 ) );

        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoDir, 6 ) );
        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoDir, 7 ) );

        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringComEspacosEmBrancoEsqDir, 7 ) );
        assertEquals( ( " " + this.stringComEspacosEmBrancoEsqDir ),
                StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringComEspacosEmBrancoEsqDir, 8 ) );

        assertEquals( this.stringVazia, StringTools.fillLeftWithBlankSpacesToDesiredLength( null, -1 ) );
        assertEquals( this.stringVazia, StringTools.fillLeftWithBlankSpacesToDesiredLength( null, 0 ) );

        assertEquals( this.stringVazia, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringNula, -1 ) );
        assertEquals( this.stringVazia, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringNula, 0 ) );

        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringEmBranco,
                -1 ) );
        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringEmBranco, 0
        ) );
        assertEquals( this.stringEmBranco, StringTools.fillLeftWithBlankSpacesToDesiredLength( this.stringEmBranco, 1
        ) );

        assertEquals( this.stringPreenchida, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, -1 ) );
        assertEquals( this.stringPreenchida, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 0 ) );
        assertEquals( this.stringPreenchida, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 4 ) );
        assertEquals( this.stringPreenchida, StringTools.fillLeftWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 5 ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#fillRightWithBlankSpacesToDesiredLength(java.lang.String, int)}.
     */
    @Test
    public final void testFillRightWithBlankSpacesToDesiredLength() {
        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( null, 1 ) );
        assertEquals( "  ", StringTools.fillRightWithBlankSpacesToDesiredLength( null, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringNula, 1 ) );
        assertEquals( "  ", StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringNula, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringVazia, 1 ) );
        assertEquals( "  ", StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringVazia, 2 ) );

        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringEmBranco,
                1 ) );
        assertEquals( "  ", StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringEmBranco, 2 ) );

        assertEquals( this.stringPreenchida, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 5 ) );
        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 6 ) );

        assertEquals( this.stringComEspacoEmBrancoEsq, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoEsq, 6 ) );
        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoEsq, 7 ) );

        assertEquals( this.stringComEspacoEmBrancoDir, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringComEspacoEmBrancoDir, 6 ) );
        assertEquals( ( this.stringComEspacoEmBrancoDir + " " ), StringTools.fillRightWithBlankSpacesToDesiredLength(
                this.stringComEspacoEmBrancoDir, 7 ) );

        assertEquals( this.stringComEspacosEmBrancoEsqDir, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringComEspacosEmBrancoEsqDir, 7 ) );
        assertEquals( ( this.stringComEspacosEmBrancoEsqDir + " " ),
                StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringComEspacosEmBrancoEsqDir, 8 ) );

        assertEquals( this.stringVazia, StringTools.fillRightWithBlankSpacesToDesiredLength( null, -1 ) );
        assertEquals( this.stringVazia, StringTools.fillRightWithBlankSpacesToDesiredLength( null, 0 ) );

        assertEquals( this.stringVazia, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringNula, -1 ) );
        assertEquals( this.stringVazia, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringNula, 0 ) );

        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringEmBranco,
                -1 ) );
        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringEmBranco,
                0 ) );
        assertEquals( this.stringEmBranco, StringTools.fillRightWithBlankSpacesToDesiredLength( this.stringEmBranco,
                1 ) );

        assertEquals( this.stringPreenchida, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, -1 ) );
        assertEquals( this.stringPreenchida, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 0 ) );
        assertEquals( this.stringPreenchida, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 4 ) );
        assertEquals( this.stringPreenchida, StringTools.fillRightWithBlankSpacesToDesiredLength( this
                .stringPreenchida, 5 ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#extractPart(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testExtractPart() {
        assertEquals( this.stringVazia, StringTools.extractPart( null, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( null, null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( null, "<<", null ) );
        assertEquals( this.stringVazia, StringTools.extractPart( null, null, null ) );

        assertEquals( this.stringVazia, StringTools.extractPart( this.stringNula, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringNula, null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringNula, "<<", null ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringNula, null, null ) );

        assertEquals( this.stringVazia, StringTools.extractPart( this.stringVazia, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringVazia, null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringVazia, "<<", null ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringVazia, null, null ) );

        assertEquals( this.stringVazia, StringTools.extractPart( this.stringEmBranco, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringEmBranco, null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringEmBranco, "<<", null ) );
        assertEquals( this.stringVazia, StringTools.extractPart( this.stringEmBranco, null, null ) );

        assertEquals( this.stringPreenchida, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), "<<",
                ">>" ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), null, ">>"
        ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), "<<", null
        ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), null, null
        ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), "<<", "<<"
        ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), ">>", ">>"
        ) );
        assertEquals( this.stringVazia, StringTools.extractPart( ( "<<" + this.stringPreenchida + ">>" ), ">>", "<<"
        ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#extractParts(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testExtractParts() {
        assertEquals( this.coleçãoVazia, StringTools.extractParts( null, "<<", ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( null, null, ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( null, "<<", null ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( null, null, null ) );

        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringNula, "<<", ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringNula, null, ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringNula, "<<", null ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringNula, null, null ) );

        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringVazia, "<<", ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringVazia, null, ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringVazia, "<<", null ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringVazia, null, null ) );

        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringEmBranco, "<<", ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringEmBranco, null, ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringEmBranco, "<<", null ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( this.stringEmBranco, null, null ) );

        assertEquals( this.coleçãoComUmElemento, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ),
                "<<", ">>" ) );
        assertEquals( this.coleçãoComDoisElementosRepetidos,
                StringTools.extractParts( ( ( "<<" + this.stringPreenchida + ">>" ) + ( "<<" + this.stringPreenchida
                        + ">>" ) ), "<<", ">>" ) );
        assertEquals( this.coleçãoComDoisElementos,
                StringTools.extractParts( ( ( "<<" + this.stringPreenchida + ">>" ) + ( "<<" + this
                        .stringComEspacoEmBrancoEsq + ">>" ) ), "<<", ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ), null,
                ">>" ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ), "<<",
                null ) );
        assertEquals( this.coleçãoVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ), null,
                null ) );
        assertEquals( this.coleçãoComStringVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ),
                "<<", "<<" ) );
        assertEquals( this.coleçãoComStringVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ),
                ">>", ">>" ) );
        assertEquals( this.coleçãoComStringVazia, StringTools.extractParts( ( "<<" + this.stringPreenchida + ">>" ),
                ">>", "<<" ) );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.utils.StringTools#replacePartsWithMap(java.lang.String, java.util.Map, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testReplacePartsWithMap() {
        assertEquals( null, StringTools.replacePartsWithMap( null, null, "<<", ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoNulo, "<<", ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoNulo, null, ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoNulo, "<<", null ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoNulo, null, null ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoVazio, "<<", ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( null, StringTools.replacePartsWithMap( null, this.mapaSubstituicaoComDoisElementos, "<<", ">>"
        ) );

        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, null, "<<", ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this.mapaSubstituicaoNulo,
                "<<", ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this.mapaSubstituicaoNulo,
                null, ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this.mapaSubstituicaoNulo,
                "<<", null ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this.mapaSubstituicaoNulo,
                null, null ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this.mapaSubstituicaoVazio,
                "<<", ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this
                .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this
                .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this
                .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this
                .mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( this.stringNula, StringTools.replacePartsWithMap( this.stringNula, this
                .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );

        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, null, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this.mapaSubstituicaoNulo,
                "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this.mapaSubstituicaoNulo,
                null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this.mapaSubstituicaoNulo,
                "<<", null ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this.mapaSubstituicaoNulo,
                null, null ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoVazio, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( this.stringVazia, StringTools.replacePartsWithMap( this.stringVazia, this
                .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );

        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, null, "<<", ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoNulo, "<<", ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoNulo, null, ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoNulo, "<<", null ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoNulo, null, null ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoVazio, "<<", ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( this.stringEmBranco, StringTools.replacePartsWithMap( this.stringEmBranco, this
                .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );

        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, null, "<<", ">>"
        ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoNulo, "<<", ">>" ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoNulo, null, ">>" ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoNulo, "<<", null ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoNulo, null, null ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoVazio, "<<", ">>" ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( this.stringPreenchida, StringTools.replacePartsWithMap( this.stringPreenchida, this
                .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );

        final String stringParaSubstituirTrechosComUmTrecho = "<<TESTE_SUBSTITUIR>>";

        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, null, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoNulo, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoNulo, null, ">>" ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoNulo, "<<", null ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoNulo, null, null ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoVazio, "<<", ">>" ) );

        final String stringSubstituidaComUmTrecho = "Parte 1";

        assertEquals( stringSubstituidaComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( stringParaSubstituirTrechosComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoComUmElemento, null, null ) );
        assertEquals( stringSubstituidaComUmTrecho,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComUmTrecho, this
                        .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );

        final String stringParaSubstituirTrechosComDoisTrechos = "<<TESTE_SUBSTITUIR>> e também a " +
                "<<TESTE_SUBSTITUIR_2>>";

        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, null, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoNulo, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoNulo, null, ">>" ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoNulo, "<<", null ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoNulo, null, null ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoVazio, "<<", ">>" ) );

        final String stringSubstituidaComUmTrechoTrocadoOutroNao = "Parte 1 e também a <<TESTE_SUBSTITUIR_2>>";

        assertEquals( stringSubstituidaComUmTrechoTrocadoOutroNao,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoComUmElemento, "<<", ">>" ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoComUmElemento, null, ">>" ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoComUmElemento, "<<", null ) );
        assertEquals( stringParaSubstituirTrechosComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoComUmElemento, null, null ) );

        final String stringSubstituidaComDoisTrechos = "Parte 1 e também a Parte 2";

        assertEquals( stringSubstituidaComDoisTrechos,
                StringTools.replacePartsWithMap( stringParaSubstituirTrechosComDoisTrechos, this
                        .mapaSubstituicaoComDoisElementos, "<<", ">>" ) );
    }

}
