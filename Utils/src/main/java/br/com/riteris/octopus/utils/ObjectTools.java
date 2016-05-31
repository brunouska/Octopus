package br.com.riteris.octopus.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.collectionIsNullOrEmpty;
import static br.com.riteris.octopus.utils.StringTools.stringIsBlank;

public final class ObjectTools {

    private ObjectTools() {
    }

    public static boolean areEqualsUsingProperty( final Object objA, final Object objB, final Collection< String > propertyNames ) {
        if ( collectionIsNullOrEmpty( propertyNames ) ) {
            throw new IllegalArgumentException( "The property names collection can't be null or empty." );
        }

        if ( ( objA != null && objB != null ) ) {
            try {
                for ( String propertyName : propertyNames ) {
                    final Field fieldObjA = objA.getClass().getDeclaredField( propertyName );
                    final Field fieldObjB = objB.getClass().getDeclaredField( propertyName );

                    if ( fieldObjA != null && fieldObjB != null ) {
                        fieldObjA.setAccessible( true );
                        fieldObjB.setAccessible( true );

                        final Object fieldObjAValue = fieldObjA.get( objA );
                        final Object fieldObjBValue = fieldObjB.get( objB );

                        if ( !fieldObjAValue.equals( fieldObjBValue ) ) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }

                return true;
            } catch ( Exception e ) {
                throw new RuntimeException( "Error comparing objects by fields: " + e.getMessage() );
            }
        } else {
            return false;
        }
    }

    public static String getObjectDescriptionResume( final Object object ) {
        String className = "N/A";
        String value = "-NULL-";

        if ( object != null ) {
            className = object.getClass().getSimpleName();

            if ( !( object instanceof Collection ) && !( object instanceof Map ) ) {
                value = object.toString();

                if ( value.isEmpty() ) {
                    value = "-EMPTY-";
                } else if ( stringIsBlank( value ) ) {
                    value = "-BLANK-";
                }
            } else {
                final StringBuilder multipleValues = new StringBuilder();
                multipleValues.append( "[ " );

                if ( ( object instanceof Collection ) && ( !CollectionAndMapTools.collectionIsNullOrEmpty( ( Collection< Object > ) object ) ) ) {
                    describeCollection( ( Collection< Object > ) object, multipleValues );
                } else if ( ( object instanceof Map ) && ( !CollectionAndMapTools.mapIsNullOrEmpty( ( Map< Object, Object > ) object ) ) ) {
                    describeMap( ( Map< Object, Object > ) object, multipleValues );
                } else {
                    multipleValues.append( "-EMPTY-" );
                }

                multipleValues.append( " ]" );

                value = multipleValues.toString();
            }
        }

        return className + ": " + value;
    }

    private static void describeCollection( final Collection< Object > collection, final StringBuilder resultToAppend ) {
        final Iterator< Object > collectionIterator = collection.iterator();

        while ( collectionIterator.hasNext() ) {
            final Object collectionItem = collectionIterator.next();

            resultToAppend.append( getObjectDescriptionResume( collectionItem ) );

            if ( collectionIterator.hasNext() ) {
                resultToAppend.append( ", " );
            }
        }
    }

    private static void describeMap( final Map< Object, Object > map, final StringBuilder resultToAppend ) {
        final Iterator< Object > mapKeyIterator = map.keySet().iterator();

        while ( mapKeyIterator.hasNext() ) {
            final Object mapKeyItem = mapKeyIterator.next();

            resultToAppend.append( "Key: " );

            resultToAppend.append( getObjectDescriptionResume( mapKeyItem ) );

            resultToAppend.append( " - Value: " );

            resultToAppend.append( getObjectDescriptionResume( map.get( mapKeyItem ) ) );

            if ( mapKeyIterator.hasNext() ) {
                resultToAppend.append( ", " );
            }
        }
    }

}