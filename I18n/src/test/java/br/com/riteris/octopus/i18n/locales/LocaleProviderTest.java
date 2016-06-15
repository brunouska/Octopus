package br.com.riteris.octopus.i18n.locales;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class LocaleProviderTest {

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithNullCode() {
        LocaleProvider.tryToGetLocaleByCode( null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithEmptyCode() {
        LocaleProvider.tryToGetLocaleByCode( "" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithBlankCode() {
        LocaleProvider.tryToGetLocaleByCode( " " );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithInvalidCode() {
        LocaleProvider.tryToGetLocaleByCode( "pt_pt_pt_BR" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithUnknowedCompleteLanguageCode() {
        LocaleProvider.tryToGetLocaleByCode( "xx_YY" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithUnknowedShortLanguageCode() {
        LocaleProvider.tryToGetLocaleByCode( "xx" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testTryToGetLocaleByCodeWithUnknowedShortCountryCode() {
        LocaleProvider.tryToGetLocaleByCode( "YY" );
    }

    @Test
    public void testTryToGetLocaleByCodeWithShortLanguageCode() {
        LocaleProvider.tryToGetLocaleByCode( "pt" );
    }

    @Test
    public void testTryToGetLocaleByCodeWithShortCountryCode() {
        LocaleProvider.tryToGetLocaleByCode( "br" );
    }

    @Test
    public void testTryToGetLocaleByCodeWithCompleteCodes() {
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt_BR" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt-BR" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt/BR" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt_br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt-br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "pt/br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT_br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT-br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT/br" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT_BR" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT-BR" ) );
        assertEquals( new Locale( "pt", "BR" ), LocaleProvider.tryToGetLocaleByCode( "PT/BR" ) );
        assertEquals( new Locale( "ja_jp", "JP" ), LocaleProvider.tryToGetLocaleByCode( "ja_jp_JP" ) );
        assertEquals( new Locale( "ja_jp", "JP" ), LocaleProvider.tryToGetLocaleByCode( "ja-jp-JP" ) );
        assertEquals( new Locale( "ja_jp", "JP" ), LocaleProvider.tryToGetLocaleByCode( "ja/jp/JP" ) );
    }

}
