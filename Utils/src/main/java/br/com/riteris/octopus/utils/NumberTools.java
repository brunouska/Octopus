package br.com.riteris.octopus.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.collectionIsNullOrEmpty;
import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmpty;
import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public final class NumberTools {

    private NumberTools() {
    }

    public static Number avgWithScaleAndPeakTreatment( final Collection< Number > numbers, final int scale, final Number peakPercentual, boolean substitutePeakWithAvg ) {
        if ( collectionIsNullOrEmpty( numbers ) ) {
            throw new IllegalArgumentException( "The numbers collection can't be null or empty." );
        }

        if ( scale < 0 ) {
            throw new IllegalArgumentException( "The scale can't be negative." );
        }

        if ( peakPercentual.doubleValue() < 0 ) {
            throw new IllegalArgumentException( "The peak limit can't be negative." );
        }

        final BigDecimal simpleAvg = BigDecimal.valueOf( avgWithScale( numbers, scale ).doubleValue() );
        final BigDecimal factor = simpleAvg.multiply( BigDecimal.valueOf( peakPercentual.doubleValue() ).divide( BigDecimal.valueOf( 100 ),
                15, BigDecimal.ROUND_HALF_UP ) );

        final BigDecimal minimumLimit = simpleAvg.subtract( factor );
        final BigDecimal maximumLimit = simpleAvg.add( factor );

        Collection< Number > numbersToFinalAvg = new ArrayList<>();

        for ( Number number : numbers ) {
            if ( number == null ) {
                throw new IllegalArgumentException( "The number to be calculated can't be null." );
            }

            if ( number.doubleValue() < minimumLimit.doubleValue() || number.doubleValue() > maximumLimit.doubleValue() ) {
                if ( substitutePeakWithAvg ) numbersToFinalAvg.add( simpleAvg );
            } else {
                numbersToFinalAvg.add( number );
            }
        }

        return avgWithScale( numbersToFinalAvg, scale );
    }

    public static Number avgWithScale( final Collection< Number > numbers, final int scale ) {
        if ( collectionIsNullOrEmpty( numbers ) ) {
            throw new IllegalArgumentException( "The numbers collection can't be null or empty." );
        }

        if ( scale < 0 ) {
            throw new IllegalArgumentException( "The scale can't be negative." );
        }

        BigDecimal sum = BigDecimal.ZERO;

        for ( Number number : numbers ) {
            if ( number == null ) {
                throw new IllegalArgumentException( "The number to be calculated can't be null." );
            }

            sum = sum.add( BigDecimal.valueOf( number.doubleValue() ) );
        }

        return sum.divide( BigDecimal.valueOf( numbers.size() ), scale, BigDecimal.ROUND_HALF_UP );
    }

    public static String formatNumberToString( final Number rawNumber, final int scale, final String decimalSeparator, final String tousandsSeparator,
                                               final String prefix, final String suffix ) {
        if ( rawNumber == null ) {
            throw new IllegalArgumentException( "The number to be formatted can't be null." );
        }

        if ( scale < 0 ) {
            throw new IllegalArgumentException( "The scale can't be negative." );
        }

        String pattern = "#,##0.";

        for ( int i = 0; i < scale; i++ ) {
            pattern += "0";
        }

        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern( pattern );

        final DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();

        if ( !stringIsNullOrEmptyOrBlank( tousandsSeparator ) ) {
            decimalFormatSymbols.setGroupingSeparator( tousandsSeparator.charAt( 0 ) );
        }

        if ( !stringIsNullOrEmptyOrBlank( decimalSeparator ) ) {
            decimalFormatSymbols.setDecimalSeparator( decimalSeparator.charAt( 0 ) );
        }

        decimalFormat.setDecimalFormatSymbols( decimalFormatSymbols );

        String result = decimalFormat.format( BigDecimal.valueOf( rawNumber.doubleValue() ) );

        if ( !stringIsNullOrEmpty( prefix ) ) {
            result = prefix + result;
        }

        if ( !stringIsNullOrEmpty( suffix ) ) {
            result += suffix;
        }

        return result;
    }

    public static Number extractNumberFromFomattedString( final String numberformattedInString, final int scale, final String decimalSeparator,
                                                          final String tousandsSeparator, final String preffix, final String suffix ) {
        if ( stringIsNullOrEmptyOrBlank( numberformattedInString ) ) {
            throw new IllegalArgumentException( "The string with formatted number can't be null, empty or blank." );
        }

        if ( scale < 0 ) {
            throw new IllegalArgumentException( "The scale can't be negative." );
        }

        String pattern = "#,##0.";

        for ( int i = 0; i < scale; i++ ) {
            pattern += "0";
        }

        if ( !stringIsNullOrEmpty( preffix ) ) {
            pattern = preffix + pattern;
        }

        if ( !stringIsNullOrEmpty( suffix ) ) {
            pattern += suffix;
        }

        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern( pattern );

        final DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();

        if ( !stringIsNullOrEmptyOrBlank( tousandsSeparator ) ) {
            decimalFormatSymbols.setGroupingSeparator( tousandsSeparator.charAt( 0 ) );
        }

        if ( !stringIsNullOrEmptyOrBlank( decimalSeparator ) ) {
            decimalFormatSymbols.setDecimalSeparator( decimalSeparator.charAt( 0 ) );
        }

        decimalFormat.setDecimalFormatSymbols( decimalFormatSymbols );

        try {
            return decimalFormat.parse( numberformattedInString );
        } catch ( ParseException e ) {
            throw new RuntimeException( "Error trying to parse number formatted in string: " + e.getMessage() );
        }
    }

}