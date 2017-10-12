package br.com.riteris.octopus.i18n.aspects;

import br.com.riteris.octopus.i18n.sources.I18NSource;
import br.com.riteris.octopus.i18n.translators.Translator;
import br.com.riteris.octopus.user.valueobjects.UserInfoVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TranslationAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPointWithI18NSourceResult;

    @Mock
    private ProceedingJoinPoint proceedingJoinPointWithI18NSourceResultAndUserInfoArg;

    @Mock
    private ProceedingJoinPoint proceedingJoinPointWithNullPointerErrorResult;

    @Mock
    private ProceedingJoinPoint proceedingJoinPointWithI18NSampleErrorResult;

    @Mock
    private ProceedingJoinPoint proceedingJoinPointWithNoI18NSourceResult;

    @Before
    public void setup() throws Throwable {
        final UserInfoVO userInfoVO = new UserInfoVO( "teste", "en_US" );

        MockitoAnnotations.initMocks( this );

        when( proceedingJoinPointWithI18NSourceResult.proceed() ).thenReturn( new I18NSourceSample() );
        when( proceedingJoinPointWithI18NSourceResultAndUserInfoArg.proceed() ).thenReturn( new I18NSourceSample() );
        when( proceedingJoinPointWithI18NSourceResultAndUserInfoArg.getArgs() ).thenReturn( new Object[]{ userInfoVO } );
        when( proceedingJoinPointWithNullPointerErrorResult.proceed() ).thenThrow( new NullPointerException() );
        when( proceedingJoinPointWithI18NSampleErrorResult.proceed() ).thenThrow( new IllegalArgumentException( "fruit" ) );
        when( proceedingJoinPointWithNoI18NSourceResult.proceed() ).thenReturn( "Teste" );
    }

    private class I18NSourceSample implements I18NSource {

        private String translatedText = "";

        @Override
        public void translateResources( final Translator translator ) {
            this.translatedText = translator.translateText( "hello_word", new Object[]{ "Bruno" } );
        }

        public String getTranslatedText() {
            return this.translatedText;
        }

    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetDefaultLanguageCodeWithNullCode() {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetDefaultLanguageCodeWithEmptyCode() {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetDefaultLanguageCodeWithBlankCode() {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( " " );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetDefaultLanguageCodeWithInvalidCode() {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "xx_YY" );
    }

    @Test
    public void testSetDefaultLanguageCode() {
        final Locale originalDefaultLocale = Locale.getDefault();

        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "es_AR" );

        assertEquals( Locale.getDefault(), new Locale( "es", "AR" ) );

        Locale.setDefault( originalDefaultLocale );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTranslateExecutionReturnsWithNullProceedingJoinPoint() throws Throwable {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "pt_BR" );

        translationAspect.translateExecutionReturns( null );
    }

    @Test
    public void testTranslateExecutionReturnsWithReturns() throws Throwable {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "pt_BR" );

        final Object returnA = translationAspect.translateExecutionReturns( this.proceedingJoinPointWithI18NSourceResult );
        final Object returnB = translationAspect.translateExecutionReturns( this.proceedingJoinPointWithI18NSourceResultAndUserInfoArg );
        final Object returnC = translationAspect.translateExecutionReturns( this.proceedingJoinPointWithNoI18NSourceResult );

        assertEquals( "Olá Bruno, seja bem-vindo!", ( ( I18NSourceSample ) returnA ).getTranslatedText() );
        assertEquals( "Hello Bruno, be welcome!", ( ( I18NSourceSample ) returnB ).getTranslatedText() );
        assertEquals( "Teste", returnC );
    }

    @Test
    public void testTranslateExecutionReturnsWithErrors() throws Throwable {
        final TranslationAspect translationAspect = new TranslationAspect();
        translationAspect.setDefaultLanguageCode( "pt_BR" );

        Throwable errorA = null;
        Throwable errorB = null;

        try {
            translationAspect.translateExecutionReturns( this.proceedingJoinPointWithNullPointerErrorResult );
        } catch ( Exception ex ) {
            errorA = ex;
        }

        try {
            translationAspect.translateExecutionReturns( this.proceedingJoinPointWithI18NSampleErrorResult );
        } catch ( Exception ex ) {
            errorB = ex;
        }

        assertEquals( errorA.getClass(), NullPointerException.class );
        assertEquals( "java.lang.NullPointerException", errorA.getMessage() );
        assertEquals( errorB.getClass(), IllegalArgumentException.class );
        assertEquals( "Maçã", errorB.getMessage() );
    }

}
