package br.com.riteris.octopus.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.collectionIsNullOrEmpty;

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

}