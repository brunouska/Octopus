package br.com.riteris.octopus.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Conjunto de métodos utilitários para a manipulação de objetos. Este objeto, por tratar-se de um conjunto de
 * utilitários, foi projetado para se integrar de
 * forma transparente ao código fonte fornecendo métodos utilitários, os quais foram projetados com programação
 * defensiva para não lançarem exceções em seu
 * funcionamento interno.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 19 de out de 2015
 */
public final class ObjectTools {

    /**
     * Construtor do objeto, com visibilidade privada, pois o objeto é um utilitário portador de métodos estáticos.
     *
     * @since 1.0.0 - Criada em 20 de out de 2015
     */
    private ObjectTools() {
    }

    /**
     * Dados dois objetos, avalia se os mesmos são iguais utilizando as propriedades fornecidas como parâmetro,
     * independente de suas classes. Útil ao comparar
     * uma entidade de domínio e um VO por exemplo, os quais estariam fora do escopo do método "java.lang.Object
     * .equals" padrão. Caso um dos objetos seja nulo,
     * ou a listagem de propriedades a serem utilizadas esteja nula ou vazia, a comparação retornará falso.
     *
     * @param objA          Objeto o qual desejamos saber se é igual a um outro objeto.
     * @param objB          Objeto para comparação.
     * @param propertyNames Nomes das propriedades as quais desejamos utilizar na comparação.
     *
     * @return True se os dois objetos forem iguais utilizando os valores das propriedades fornecidas e false caso
     * contrário.
     *
     * @since 1.0.0 - Criada em 19 de out de 2015
     */
    @SuppressWarnings( "rawtypes" )
    public static boolean areEqualsUsingProperty( Object objA, Object objB, Collection< String > propertyNames ) {
        if ( !( objA != null && objB != null ) ) {
            return false;
        }

        if ( CollectionAndMapTools.collectionIsNullOrEmpty( propertyNames ) ) {
            return false;
        }

        try {

            Class classObjA = objA.getClass();
            Class classObjB = objB.getClass();

            for ( String propertyName : propertyNames ) {
                Field fieldObjA = classObjA.getDeclaredField( propertyName );
                Field fieldObjB = classObjB.getDeclaredField( propertyName );

                if ( fieldObjA != null && fieldObjB != null ) {
                    fieldObjA.setAccessible( true );
                    fieldObjB.setAccessible( true );

                    Object fieldObjAValue = fieldObjA.get( objA );
                    Object fieldObjBValue = fieldObjB.get( objB );

                    if ( !fieldObjAValue.equals( fieldObjBValue ) ) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }

            return true;

        } catch ( Exception e ) {

            return false;

        }
    }

    /**
     * Obtém a descrição resumida do objeto, percorrendo de forma recursiva as coleções e mapas para compor a
     * descrição do tipo de objeto e seu respectivo
     * valor.
     *
     * @param object Objeto o qual desejamos uma descrição resumida de seu valor e tipo.
     *
     * @return Descrição resumida do objeto, atuando inclusive de forma recursiva caso o objeto seja uma coleção ou
     * mapa.
     *
     * @since 1.0.0 - Criada em 17 de mar de 2016
     */
    @SuppressWarnings( "unchecked" )
    public static String getObjectDescriptionResume( final Object object ) {
        String className = "N/A";
        String value = "-NULL-";

        if ( object != null ) {
            if ( !StringTools.isNullEmptyOrBlank( object.getClass().getSimpleName() ) ) {
                className = object.getClass().getSimpleName();
            }

            if ( !( object instanceof Collection ) && !( object instanceof Map ) ) {
                value = object.toString();

                if ( value.isEmpty() ) {
                    value = "-EMPTY-";
                }
                else if ( StringTools.isBlank( value ) ) {
                    value = "-BLANK-";
                }
            }
            else {
                StringBuilder multipleValues = new StringBuilder();
                multipleValues.append( "[ " );

                if ( object instanceof Collection && ( !CollectionAndMapTools.collectionIsNullOrEmpty( ( Collection<
                        Object > ) object ) ) ) {
                    Iterator< Object > collectionIterator = ( ( Collection< Object > ) object ).iterator();

                    while ( collectionIterator.hasNext() ) {
                        Object collectionItem = collectionIterator.next();

                        multipleValues.append( ObjectTools.getObjectDescriptionResume( collectionItem ) );

                        if ( collectionIterator.hasNext() ) {
                            multipleValues.append( ", " );
                        }
                    }
                }
                else if ( object instanceof Map && ( !CollectionAndMapTools.mapIsNullOrEmpty( ( Map< Object, Object >
                        ) object ) ) ) {
                    Iterator< Object > mapKeyIterator = ( ( Map< Object, Object > ) object ).keySet().iterator();

                    while ( mapKeyIterator.hasNext() ) {
                        Object mapKeyItem = mapKeyIterator.next();

                        multipleValues.append( "Key: " );

                        multipleValues.append( ObjectTools.getObjectDescriptionResume( mapKeyItem ) );

                        multipleValues.append( " - Value: " );

                        multipleValues.append( ObjectTools.getObjectDescriptionResume( ( ( Map< Object, Object > )
                                object ).get( mapKeyItem ) ) );

                        if ( mapKeyIterator.hasNext() ) {
                            multipleValues.append( ", " );
                        }
                    }
                }
                else {
                    multipleValues.append( "-EMPTY-" );
                }

                multipleValues.append( " ]" );

                value = multipleValues.toString();
            }
        }

        return className +
                ": " +
                value;
    }

}