package br.com.riteris.octopus.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.mapIsNullOrEmpty;

public final class StringTools {

    private StringTools() {
    }

    public static String trimStringAtLeft( String stringToTrim ) {
        if ( stringToTrim == null || stringToTrim.isEmpty() ) {
            return stringToTrim;
        }

        while ( stringToTrim.startsWith( " " ) ) {
            stringToTrim = stringToTrim.substring( 1 );
        }

        return stringToTrim;
    }

    public static String trimStringAtRight( String stringToTrim ) {
        if ( stringToTrim == null || stringToTrim.isEmpty() ) {
            return stringToTrim;
        }

        while ( stringToTrim.endsWith( " " ) ) {
            stringToTrim = stringToTrim.substring( 0, stringToTrim.length() - 1 );
        }

        return stringToTrim;
    }

    public static String fillStringAtLeftWithBlankSpacesToDesiredLength( String stringToFillWithBlankSpaces, final int desiredLength ) {
        if ( desiredLength <= 0 ) {
            throw new IllegalArgumentException( "Desired length must be greater than 0." );
        }

        if ( stringToFillWithBlankSpaces == null ) {
            stringToFillWithBlankSpaces = "";
        }

        if ( stringToFillWithBlankSpaces.length() >= desiredLength ) {
            return stringToFillWithBlankSpaces;
        }

        int blankSpacesNumberToAdd = desiredLength - stringToFillWithBlankSpaces.length();

        return addBlankSpacesToString( stringToFillWithBlankSpaces, blankSpacesNumberToAdd, true, false );
    }

    public static String addBlankSpacesToString( String stringToAddBlankSpaces, final int numberOfSpaces, final boolean atLeft, final boolean atRight ) {
        if ( numberOfSpaces <= 0 ) {
            throw new IllegalArgumentException( "The number of blank spaces to add must be greater than 0." );
        }

        if ( !atLeft && !atRight ) {
            throw new IllegalArgumentException( "One of the optional sides to add blank spaces must be true." );
        }

        if ( stringToAddBlankSpaces == null ) {
            stringToAddBlankSpaces = "";
        }

        if ( atLeft ) {
            for ( int i = 0; i < numberOfSpaces; i++ ) {
                stringToAddBlankSpaces = " " + stringToAddBlankSpaces;
            }
        }

        if ( atRight ) {
            for ( int i = 0; i < numberOfSpaces; i++ ) {
                stringToAddBlankSpaces += " ";
            }
        }

        return stringToAddBlankSpaces;
    }

    public static String fillStringAtRightWithBlankSpacesToDesiredLength( String stringToFillWithBlankSpaces, final int desiredLength ) {
        if ( desiredLength <= 0 ) {
            throw new IllegalArgumentException( "Desired length must be greater than 0." );
        }

        if ( stringToFillWithBlankSpaces == null ) {
            stringToFillWithBlankSpaces = "";
        }

        if ( stringToFillWithBlankSpaces.length() >= desiredLength ) {
            return stringToFillWithBlankSpaces;
        }

        int blankSpacesNumberToAdd = desiredLength - stringToFillWithBlankSpaces.length();

        return StringTools.addBlankSpacesToString( stringToFillWithBlankSpaces, blankSpacesNumberToAdd, false, true );
    }

    public static String replacePartsInStringUsingMapAndDelimiters( String sourceString, final Map< String, String > replacementMap, final String leftDelimiter,
                                                                    final String rightDelimiter ) {
        if ( sourceString == null ) {
            throw new IllegalArgumentException( "String to have parts replaced can't be null." );
        }

        if ( stringIsNullOrEmptyOrBlank( leftDelimiter ) || stringIsNullOrEmptyOrBlank( rightDelimiter ) ) {
            throw new IllegalArgumentException( "Left and right delimiters can't be null, empty or blank." );
        }

        if ( mapIsNullOrEmpty( replacementMap ) ) {
            throw new IllegalArgumentException( "Replacement map can't be null or empty." );
        }

        final Pattern pattern = Pattern.compile( leftDelimiter + ".+?" + rightDelimiter );
        final Matcher matcher = pattern.matcher( sourceString );

        final Map< String, String > foundStringsToReplaceMap = new HashMap<>();

        while ( matcher.find() ) {
            final String stringToReplace = sourceString.substring( matcher.start(), matcher.end() );
            final String replaceKey = stringToReplace.substring( leftDelimiter.length(), stringToReplace.length() - rightDelimiter.length() );

            if ( replacementMap.containsKey( replaceKey ) ) {
                foundStringsToReplaceMap.put( stringToReplace, replacementMap.get( replaceKey ) );
            }
        }

        for ( String stringToReplace : foundStringsToReplaceMap.keySet() ) {
            sourceString = sourceString.replace( stringToReplace, foundStringsToReplaceMap.get( stringToReplace ) );
        }

        return sourceString;
    }

    public static boolean stringIsNullOrEmptyOrBlank( final String stringToTest ) {
        return stringIsNullOrEmpty( stringToTest ) || stringIsBlank( stringToTest );
    }

    public static boolean stringIsNullOrEmpty( final String stringToTest ) {
        return stringToTest == null || stringToTest.isEmpty();
    }

    public static boolean stringIsBlank( final String stringToTest ) {
        return stringToTest != null && stringToTest.matches( "\\s+" );
    }

}