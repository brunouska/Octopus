package br.com.riteris.octopus.i18n.aspects;

import br.com.riteris.octopus.i18n.locales.LocaleProvider;
import br.com.riteris.octopus.i18n.sources.I18NSource;
import br.com.riteris.octopus.i18n.translators.Translator;
import br.com.riteris.octopus.user.valueobjects.UserInfoVO;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.collectionIsNullOrEmpty;
import static br.com.riteris.octopus.utils.CollectionAndMapTools.mapIsNullOrEmpty;
import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public class TranslationAspect {

    public void setDefaultLanguageCode( String languageCode ) {
        if ( stringIsNullOrEmptyOrBlank( languageCode ) ) {
            throw new IllegalArgumentException( "The default language code can't be null, empty or blank." );
        }

        final Locale locale = LocaleProvider.tryToGetLocaleByCode( languageCode );

        if ( locale == null ) {
            throw new IllegalArgumentException( "Unknown default language code informed, corresponding locale could not be found." );
        }

        Locale.setDefault( locale );
    }

    public final Object translateExecutionReturns( final ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
        if ( proceedingJoinPoint == null ) {
            throw new IllegalArgumentException( "There isn't proceeding join point informed to itercept." );
        }

        final Locale locale = this.getLocaleInfoFromArgs( proceedingJoinPoint.getArgs() );

        final Translator translator = new Translator( locale );

        try {
            final Object result = proceedingJoinPoint.proceed();

            this.translateObject( result, translator );

            return result;
        } catch ( Exception ex ) {
            final Constructor constructor = ex.getClass().getDeclaredConstructor( String.class );

            throw ( Exception ) constructor.newInstance( this.getExceptionMessageTranslated( ex, translator ) );
        }
    }

    private Locale getLocaleInfoFromArgs( final Object[] args ) {
        if ( args != null && args.length > 0 ) {
            for ( Object arg : args ) {
                if ( arg != null && arg instanceof UserInfoVO ) {
                    String languageCode = ( ( UserInfoVO ) arg ).getLocaleCode();

                    if ( stringIsNullOrEmptyOrBlank( languageCode ) ) {
                        throw new IllegalArgumentException( "The locale code at user data can't be null, empty or blank." );
                    }

                    Locale locale = LocaleProvider.tryToGetLocaleByCode( languageCode );

                    if ( locale == null ) {
                        throw new IllegalArgumentException( "Unknown language code informed at user data, corresponding locale could not be found." );
                    }

                    return locale;
                }
            }
        }

        return Locale.getDefault();
    }

    private void translateObject( final Object object, final Translator translator ) {
        if ( object == null ) {
            return;
        }

        if ( object instanceof I18NSource ) {
            ( ( I18NSource ) object ).translateResources( translator );
        }

        if ( object instanceof Collection && ( !collectionIsNullOrEmpty( ( Collection ) object ) ) ) {
            for ( final Object collectionItem : ( ( Collection ) object ) ) {
                this.translateObject( collectionItem, translator );
            }
        }

        if ( object instanceof Map && ( !mapIsNullOrEmpty( ( Map ) object ) ) ) {
            for ( final Object mapItem : ( ( Map ) object ).values() ) {
                this.translateObject( mapItem, translator );
            }
        }
    }

    private String getExceptionMessageTranslated( final Exception exception, final Translator translator ) {
        final String exceptionMessage = exception.getMessage();

        if ( stringIsNullOrEmptyOrBlank( exceptionMessage ) ) {
            return exception.getClass().getCanonicalName();
        } else {
            return translator.translateText( exceptionMessage, null );
        }
    }

}