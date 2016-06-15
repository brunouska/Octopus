package br.com.riteris.octopus.i18n.translators;

import org.junit.Test;

import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class TranslatorTest {

    @Test( expected = IllegalArgumentException.class )
    public void testConstructorWithNullLocale() {
        new Translator( null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatDateUsingL10NWithNullDateToFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );
        translator.formatDateUsingL10N( null, Translator.FormatStyle.SHORT );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatDateUsingL10NWithNullFormatStyle() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );
        translator.formatDateUsingL10N( new Date(), null );
    }

    @Test
    public void testFormatDateUsingL10NWithShortDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "01/01/16", translator.formatDateUsingL10N( new Date( 116, 0, 1 ), Translator.FormatStyle.SHORT ) );
    }

    @Test
    public void testFormatDateUsingL10NWithMediumDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "01/01/2016", translator.formatDateUsingL10N( new Date( 116, 0, 1 ), Translator.FormatStyle.MEDIUM ) );
    }

    @Test
    public void testFormatDateUsingL10NWithLongDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "1 de Janeiro de 2016", translator.formatDateUsingL10N( new Date( 116, 0, 1 ), Translator.FormatStyle.LONG ) );
    }

    @Test
    public void testFormatDateUsingL10NWithFullDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "Sexta-feira, 1 de Janeiro de 2016", translator.formatDateUsingL10N( new Date( 116, 0, 1 ), Translator.FormatStyle.FULL ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatTimeUsingL10NWithNullDateToFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );
        translator.formatTimeUsingL10N( null, Translator.FormatStyle.SHORT );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatTimeUsingL10NWithNullFormatStyle() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );
        translator.formatTimeUsingL10N( new Date(), null );
    }

    @Test
    public void testFormatTimeUsingL10NWithShortDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "01:00", translator.formatTimeUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.SHORT ) );
    }

    @Test
    public void testFormatTimeUsingL10NWithMediumDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "01:00:00", translator.formatTimeUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.MEDIUM ) );
    }

    @Test
    public void testFormatTimeUsingL10NWithLongDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "1h0min0s BRST", translator.formatTimeUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.LONG ) );
    }

    @Test
    public void testFormatTimeUsingL10NWithFullDateFormat() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "01h00min00s BRST", translator.formatTimeUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.FULL ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatNumberUsingL10NWithNullNumber() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.formatNumberUsingL10N( null );
    }

    @Test
    public void testFormatNumbersUsingL10N() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "1", translator.formatNumberUsingL10N( 1 ) );
        assertEquals( "1", translator.formatNumberUsingL10N( 1.0 ) );
        assertEquals( "1,01", translator.formatNumberUsingL10N( 1.01 ) );
        assertEquals( "1.000", translator.formatNumberUsingL10N( 1000 ) );
        assertEquals( "1.000", translator.formatNumberUsingL10N( 1000.0 ) );
        assertEquals( "1.000,01", translator.formatNumberUsingL10N( 1000.01 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatPercentUsingL10NWithNullNumber() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.formatPercentUsingL10N( null );
    }

    @Test
    public void testFormatPercentsUsingL10N() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "1,00%", translator.formatPercentUsingL10N( 0.01 ) );
        assertEquals( "1,01%", translator.formatPercentUsingL10N( 0.0101 ) );
        assertEquals( "100,00%", translator.formatPercentUsingL10N( 1 ) );
        assertEquals( "100,01%", translator.formatPercentUsingL10N( 1.0001 ) );
        assertEquals( "1.000,00%", translator.formatPercentUsingL10N( 10 ) );
        assertEquals( "1.000,01%", translator.formatPercentUsingL10N( 10.0001 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFormatCurrencyUsingL10NWithNullNumber() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.formatCurrencyUsingL10N( null );
    }

    @Test
    public void testFormatCurrenciesUsingL10N() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        assertEquals( "R$ 1,00", translator.formatCurrencyUsingL10N( 1 ) );
        assertEquals( "R$ 1,00", translator.formatCurrencyUsingL10N( 1.0 ) );
        assertEquals( "R$ 1,00", translator.formatCurrencyUsingL10N( 1.00 ) );
        assertEquals( "R$ 1,01", translator.formatCurrencyUsingL10N( 1.01 ) );
        assertEquals( "R$ 1.000,00", translator.formatCurrencyUsingL10N( 1000 ) );
        assertEquals( "R$ 1.000,00", translator.formatCurrencyUsingL10N( 1000.0 ) );
        assertEquals( "R$ 1.000,01", translator.formatCurrencyUsingL10N( 1000.01 ) );
        assertEquals( "R$ 1.000,00", translator.formatCurrencyUsingL10N( 1000.00 ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTranslateTextWithNullText() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.translateText( null, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTranslateTextWithEmptyText() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.translateText( "", null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTranslateTextWithBlankText() {
        final Translator translator = new Translator( new Locale( "pt", "BR" ) );

        translator.translateText( " ", null );
    }

    @Test
    public void testTranslateTexts() {
        Locale.setDefault( new Locale( "pt", "BR" ) );

        final Translator translatorPtBR = new Translator( new Locale( "pt", "BR" ) );
        final Translator translatorEnUS = new Translator( new Locale( "en", "US" ) );
        final Translator translatorEsAR = new Translator( new Locale( "es", "AR" ) );

        assertEquals( "Maçã", translatorPtBR.translateText( "fruit", null ) );
        assertEquals( "Olá Bruno, seja bem-vindo!", translatorPtBR.translateText( "hello_word", new Object[]{ "Bruno" } ) );
        String currencyPtBRVal = translatorPtBR.formatCurrencyUsingL10N( 1 );
        String datePtBRVal = translatorPtBR.formatDateUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.SHORT );
        assertEquals( "Eu tenho R$ 1,00, e hoje é 01/01/16!", translatorPtBR.translateText( "currency_msg", new Object[]{ currencyPtBRVal, datePtBRVal } ) );
        assertEquals( "Só tem essa msg no idioma padrão.", translatorPtBR.translateText( "default_msg", null ) );

        assertEquals( "Apple", translatorEnUS.translateText( "fruit", null ) );
        assertEquals( "Hello Bruno, be welcome!", translatorEnUS.translateText( "hello_word", new Object[]{ "Bruno" } ) );
        String currencyEnUSVal = translatorEnUS.formatCurrencyUsingL10N( 1 );
        String dateEnUSVal = translatorEnUS.formatDateUsingL10N( new Date( 116, 1, 1, 1, 0, 0 ), Translator.FormatStyle.SHORT );
        assertEquals( "I have $1.00, and today is 2/1/16!", translatorEnUS.translateText( "currency_msg", new Object[]{ currencyEnUSVal, dateEnUSVal } ) );
        assertEquals( "Só tem essa msg no idioma padrão.", translatorEnUS.translateText( "default_msg", null ) );

        assertEquals( "Maçã", translatorEsAR.translateText( "fruit", null ) );
        assertEquals( "Olá Bruno, seja bem-vindo!", translatorEsAR.translateText( "hello_word", new Object[]{ "Bruno" } ) );
        String currencyVal = translatorEsAR.formatCurrencyUsingL10N( 1 );
        String dateVal = translatorEsAR.formatDateUsingL10N( new Date( 116, 0, 1, 1, 0, 0 ), Translator.FormatStyle.SHORT );
        assertEquals( "Eu tenho $1,00, e hoje é 01/01/16!", translatorEsAR.translateText( "currency_msg", new Object[]{ currencyVal, dateVal } ) );
        assertEquals( "Só tem essa msg no idioma padrão.", translatorEsAR.translateText( "default_msg", null ) );
    }

}