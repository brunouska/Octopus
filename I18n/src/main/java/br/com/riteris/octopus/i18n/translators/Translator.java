package br.com.riteris.octopus.i18n.translators;

import br.com.riteris.octopus.utils.StringTools;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {

    private final Locale locale;

    public Translator( final Locale locale ) {
        if ( locale == null ) {
            throw new IllegalArgumentException( "The locale used in the translator can't be null." );
        }

        this.locale = locale;
    }

    public String formatDateUsingL10N( final Date dateToFormat, final FormatStyle formatStyle ) {
        if ( dateToFormat == null ) {
            throw new IllegalArgumentException( "The date to be formatted can't be null." );
        }

        if ( formatStyle == null ) {
            throw new IllegalArgumentException( "The desired format style can't be null." );
        }

        final DateFormat dateFormat = DateFormat.getDateInstance( this.parseFormatStyleIntoDateFormatStyle( formatStyle ), this.locale );

        return dateFormat.format( dateToFormat );
    }

    private int parseFormatStyleIntoDateFormatStyle( final FormatStyle formatStyle ) {
        switch ( formatStyle ) {
            case SHORT:
                return DateFormat.SHORT;
            case MEDIUM:
                return DateFormat.MEDIUM;
            case LONG:
                return DateFormat.LONG;
            case FULL:
                return DateFormat.FULL;
            default:
                throw new IllegalArgumentException( "Format style not recognized." );
        }
    }

    public String formatTimeUsingL10N( final Date timeToFormat, final FormatStyle formatStyle ) {
        if ( timeToFormat == null ) {
            throw new IllegalArgumentException( "The time to be formatted can't be null." );
        }

        if ( formatStyle == null ) {
            throw new IllegalArgumentException( "The desired format style can't be null." );
        }

        final DateFormat dateFormat = DateFormat.getTimeInstance( this.parseFormatStyleIntoDateFormatStyle( formatStyle ), this.locale );

        return dateFormat.format( timeToFormat );
    }

    public String formatNumberUsingL10N( final Number numberValueToFormat ) {
        if ( numberValueToFormat == null ) {
            throw new IllegalArgumentException( "The number to be formatted can't be null." );
        }

        final NumberFormat numberFormat = NumberFormat.getNumberInstance( this.locale );

        return numberFormat.format( numberValueToFormat.doubleValue() );
    }

    public String formatPercentUsingL10N( final Number percentValueToFormat ) {
        if ( percentValueToFormat == null ) {
            throw new IllegalArgumentException( "The number to be formatted can't be null." );
        }

        final NumberFormat numberFormat = NumberFormat.getPercentInstance( this.locale );

        return numberFormat.format( percentValueToFormat.doubleValue() );
    }

    public String formatCurrencyUsingL10N( final Number currencyValueToFormat ) {
        if ( currencyValueToFormat == null ) {
            throw new IllegalArgumentException( "The number to be formatted can't be null." );
        }

        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance( this.locale );

        return numberFormat.format( currencyValueToFormat.doubleValue() );
    }

    public String translateText( final String textToTranslate, final Object[] argsToText ) {
        if ( StringTools.stringIsNullOrEmptyOrBlank( textToTranslate ) ) {
            throw new IllegalArgumentException( "The text to be translated can't be null, empty or blank." );
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle( "messages", this.locale );

        if ( resourceBundle != null ) {
            String message = resourceBundle.getString( textToTranslate );

            if ( message != null && ( argsToText != null && argsToText.length > 0 ) ) {
                message = MessageFormat.format( message, argsToText );
            }

            return message;
        }

        return textToTranslate;
    }

    public enum FormatStyle {
        SHORT, MEDIUM, LONG, FULL
    }

}
