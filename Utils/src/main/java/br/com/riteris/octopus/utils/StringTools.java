package br.com.riteris.octopus.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Conjunto de métodos utilitários para a manipulação de Strings. Este objeto, por tratar-se de um conjunto de
 * utilitários, foi projetado para se integrar de
 * forma transparente ao código fonte fornecendo métodos utilitários, os quais foram projetados com programação
 * defensiva para não lançarem exceções em seu
 * funcionamento interno.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 13 de out de 2015
 */
public final class StringTools {

    /**
     * Construtor do objeto, com visibilidade privada, pois o objeto é um utilitário portador de métodos estáticos.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    private StringTools() {
    }

    /**
     * Verifica se uma determinada String é nula ou vazia.
     *
     * @param stringToTest String a qual desejamos testar.
     *
     * @return True caso a String seja nula ou vazia e false caso contrário.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static boolean isNullOrEmpty( final String stringToTest ) {
        return ( StringTools.isNull( stringToTest ) || stringToTest.isEmpty() );
    }

    /**
     * Verifica se uma determinada String contém apenas caracteres em branco.
     *
     * @param stringToTest String a qual desejamos testar.
     *
     * @return True caso a String contenha apenas caracteres em branco.
     *
     * @since 1.0.0 - Criada em 23 de mar de 2016
     */
    public static boolean isBlank( final String stringToTest ) {
        return !StringTools.isNullOrEmpty( stringToTest ) && ( stringToTest.contains( " " ) && stringToTest.replace(
                " ", "" ).isEmpty() );
    }

    /**
     * Verifica se uma determinada String é nula, vazia ou está em branco.
     *
     * @param stringToTest String a qual desejamos testar.
     *
     * @return True caso a String seja nula, vazia ou estiver em branco e false caso contrário.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static boolean isNullEmptyOrBlank( final String stringToTest ) {
        return ( StringTools.isNull( stringToTest ) || stringToTest.isEmpty() || StringTools.isBlank( stringToTest ) );
    }

    /**
     * Remove os espaços em branco à esquerda de uma determinada String. Caso a String seja nula ou esteja vazia, a
     * própria String será retornada sem nenhuma
     * alteração.
     *
     * @param stringToTrim String a qual desejamos remover os espaços em branco à esquerda.
     *
     * @return String com todos os espaços em branco à esquerda removidos.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String trimLeft( String stringToTrim ) {
        if ( !StringTools.isNullOrEmpty( stringToTrim ) ) {
            if ( stringToTrim.startsWith( " " ) ) {
                while ( stringToTrim.startsWith( " " ) ) {
                    stringToTrim = stringToTrim.substring( 1 );
                }

                return stringToTrim;
            }
            else {
                return stringToTrim;
            }
        }
        else {
            return stringToTrim;
        }
    }

    /**
     * Remove os espaços em branco à direita de uma determinada String. Caso a String seja nula ou esteja vazia, a
     * própria String será retornada sem nenhuma
     * alteração.
     *
     * @param stringToTrim String a qual desejamos remover os espaços em branco à direita.
     *
     * @return String com todos os espaços em branco à direita removidos.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String trimRight( String stringToTrim ) {
        if ( !StringTools.isNullOrEmpty( stringToTrim ) ) {
            if ( stringToTrim.endsWith( " " ) ) {
                while ( stringToTrim.endsWith( " " ) ) {
                    stringToTrim = stringToTrim.substring( 0, stringToTrim.length() - 1 );
                }

                return stringToTrim;
            }
            else {
                return stringToTrim;
            }
        }
        else {
            return stringToTrim;
        }
    }

    /**
     * Remove os espaços em branco à esquerda e à direita de uma determinada String. Caso a String seja nula ou
     * esteja vazia, a própria String será retornada
     * sem nenhuma alteração.
     *
     * @param stringToTrim String a qual desejamos remover os espaços em branco à esquerda e à direita.
     *
     * @return String com todos os espaços em branco à esquerda e à direita removidos.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String trim( String stringToTrim ) {
        if ( !StringTools.isNullOrEmpty( stringToTrim ) ) {
            stringToTrim = StringTools.trimLeft( stringToTrim );
            stringToTrim = StringTools.trimRight( stringToTrim );

            return stringToTrim;
        }
        else {
            return stringToTrim;
        }
    }

    /**
     * Acrescenta espaços em branco à esquerda de uma determinada String até que seja atingido o tamanho desejado.
     * Caso a String seja maior do que o tamanho
     * desejado ou o tamanho desejado for 0 ou negativo, a própria String será retornada sem nenhuma alteração. Caso a
     * String seja nula, será criada uma String
     * vazia e preenchida com os espaços em branco desejados conforme os parâmetros informados ou retornada vazia,
     * caso esteja nula e os parâmetros informados
     * não permitem o seu preenchimento.
     *
     * @param stringToFillWithBlankSpaces String a qual serão acrescentados espaços em branco à sua esquerda até que o
     *                                    tamanho desejado seja atingido.
     * @param desiredLength               Tamanho desejado para a String resultante.
     *
     * @return String preenchida com espaços em branco à sua esquerda com o tamanho informado por parâmetro.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String fillLeftWithBlankSpacesToDesiredLength( String stringToFillWithBlankSpaces, final int
            desiredLength ) {
        if ( StringTools.isNull( stringToFillWithBlankSpaces ) ) {
            stringToFillWithBlankSpaces = "";
        }

        if ( ( desiredLength <= 0 ) || ( stringToFillWithBlankSpaces.length() >= desiredLength ) ) {
            return stringToFillWithBlankSpaces;
        }
        else {
            int blankSpacesNumberToAdd = desiredLength - stringToFillWithBlankSpaces.length();

            return StringTools.addBlankSpaces( stringToFillWithBlankSpaces, blankSpacesNumberToAdd, true, false );
        }
    }

    /**
     * Verifica se uma determinada String é nula.
     *
     * @param stringToTest String a qual desejamos testar.
     *
     * @return True caso a String seja nula e false caso contrário.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static boolean isNull( final String stringToTest ) {
        return ( stringToTest == null );
    }

    /**
     * Adiciona espaços em branco no início ou ao final de uma determinada String, baseado no número de espaços a
     * serem inseridos e baseado na configuração de
     * posição (esquerda e/ou direita). Caso o numero de espaços seja 0 ou negativo ou não tenha sido escolhida
     * nenhuma das opções de preenchimento (esquerda ou
     * direita), a própria String será retornada sem nenhuma alteração. Caso a String seja nula, será criada uma
     * String vazia e preenchida com os espaços em
     * branco desejados conforme os parâmetros informados.
     *
     * @param stringToAddBlankSpaces String a qual serão adicionados os espaços em branco.
     * @param numberOfSpaces         Número de espaços em branco os quais serão adicionados.
     * @param atLeft                 Indica que devem ser adicionados espaços em branco à esquerda do início da String.
     * @param atRight                Indica que devem ser adicionados espaços em branco à direita do término da String.
     *
     * @return String com os espaços em branco adicionados conforme as configurações informadas.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String addBlankSpaces( String stringToAddBlankSpaces, final int numberOfSpaces, final boolean
            atLeft, final boolean atRight ) {
        if ( numberOfSpaces <= 0 || ( !atLeft && !atRight ) ) {
            return stringToAddBlankSpaces;
        }
        else {
            if ( StringTools.isNull( stringToAddBlankSpaces ) ) {
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
    }

    /**
     * Acrescenta espaços em branco à direita de uma determinada String até que seja atingido o tamanho desejado.
     * Caso a String seja maior do que o tamanho
     * desejado ou o tamanho desejado seja 0 ou negativo, a própria String será retornada sem nenhuma alteração. Caso
     * a String seja nula, será criada uma String
     * vazia e preenchida com os espaços em branco desejados conforme os parâmetros informados ou retornada vazia,
     * caso esteja nula e os parâmetros informados
     * não permitem o seu preenchimento.
     *
     * @param stringToFillWithBlankSpaces String a qual serão acrescentados espaços em branco à sua direita até que o
     *                                    tamanho desejado seja atingido.
     * @param desiredLength               Tamanho desejado para a String resultante.
     *
     * @return String preenchida com espaços em branco à sua direita com o tamanho informado por parâmetro.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String fillRightWithBlankSpacesToDesiredLength( String stringToFillWithBlankSpaces, final int
            desiredLength ) {
        if ( StringTools.isNull( stringToFillWithBlankSpaces ) ) {
            stringToFillWithBlankSpaces = "";
        }

        if ( ( desiredLength <= 0 ) || ( stringToFillWithBlankSpaces.length() >= desiredLength ) ) {
            return stringToFillWithBlankSpaces;
        }
        else {
            int blankSpacesNumberToAdd = desiredLength - stringToFillWithBlankSpaces.length();

            return StringTools.addBlankSpaces( stringToFillWithBlankSpaces, blankSpacesNumberToAdd, false, true );
        }
    }

    /**
     * Extrai de uma determinada String os caracteres que estiverem delimitados pelos delimitadores informados, não
     * incluindo os mesmos. Caso a String informada
     * seja nula, vazia ou esteja em branco ou seus delimitadores, será retornada uma String vazia. Caso os
     * delimitadores apontem para a mesma posição ou o
     * delimitador final ocorra antes do delimitador inicial na String informada, será retornada uma String vazia. O
     * trecho de caracteres que serão extraídos da
     * String serão somente os caracteres correspondentes ao primeiro conjunto de delimitadores encontrados.
     *
     * @param sourceString   String da qual desejamos extrair os caracteres delimitados pelos delimitadores informados.
     * @param firstDelimiter Delimitador inicial do trecho o qual desejamos extrair.
     * @param lastDelimiter  Delimitador final do trecho o qual desejamos extrair.
     *
     * @return Caracteres extraídos da String informada, delimitados pelos delimitadores informados.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static String extractPart( final String sourceString, final String firstDelimiter, final String
            lastDelimiter ) {
        if ( StringTools.isNullEmptyOrBlank( sourceString ) ) {
            return "";
        }
        else if ( StringTools.isNullEmptyOrBlank( firstDelimiter ) || StringTools.isNullEmptyOrBlank( lastDelimiter )
                ) {
            return "";
        }
        else {
            int beginIndex = ( sourceString.indexOf( firstDelimiter ) + firstDelimiter.length() );
            int endIndex = sourceString.indexOf( lastDelimiter );

            if ( ( beginIndex < 0 || endIndex < 0 ) || ( beginIndex >= endIndex ) ) {
                return "";
            }
            else {
                return sourceString.substring( beginIndex, endIndex );
            }
        }
    }

    /**
     * Extrai de uma determinada String os caracteres que estiverem delimitados pelos delimitadores informados, não
     * incluindo os mesmos. Caso a String informada
     * seja nula, vazia ou esteja em branco ou seus delimitadores, será retornada uma listagem vazia. Serão extraídos
     * todos os caracteres contidos nos conjuntos
     * de delimitadores encontrados na String, seguindo as mesmas regras do método
     * {@link #extractPart(String, String, String) extractPart} para cada conjunto
     * de delimitadores encontrados, os quais serão colocados na lista de caracteres que será retornada.
     *
     * @param sourceString   String da qual desejamos extrair os caracteres delimitados pelos delimitadores informados.
     * @param firstDelimiter Delimitador inicial do trecho o qual desejamos extrair.
     * @param lastDelimiter  Delimitador final do trecho o qual desejamos extrair.
     *
     * @return Listagem contendo os caracteres extraídos da String informada, delimitados pelos delimitadores
     * informados.
     *
     * @since 1.0.0 - Criada em 13 de out de 2015
     */
    public static Collection< String > extractParts( String sourceString, final String firstDelimiter, final String
            lastDelimiter ) {
        Collection< String > result = new ArrayList<>();

        if ( StringTools.isNullEmptyOrBlank( sourceString ) ) {
            return result;
        }
        else if ( StringTools.isNullEmptyOrBlank( firstDelimiter ) || StringTools.isNullEmptyOrBlank( lastDelimiter )
                ) {
            return result;
        }
        else {
            while ( sourceString.contains( firstDelimiter ) && sourceString.contains( lastDelimiter ) ) {
                result.add( StringTools.extractPart( sourceString, firstDelimiter, lastDelimiter ) );
                sourceString = sourceString.replaceFirst( firstDelimiter, "" );
                sourceString = sourceString.replaceFirst( lastDelimiter, "" );
            }

            return result;
        }
    }

    /**
     * Dada uma determinada String, troca todos os trechos localizados entre os conjuntos de delimitadores pelos
     * valores correspondentes existentes no mapa
     * informado, sendo os valores encontrados as chaves dos valores dos mapas. Caso a String informada esteja nula,
     * vazia ou em branco ou o mapa e os
     * delimitadores, será retornada a String informada sem qualquer tipo de alteração.
     *
     * @param sourceString   String para a qual desejamos substituir os trechos localizados entre os conjuntos de
     *                       delimitadores.
     * @param targetPartsMap Mapa contendo os valores para substituição na String informada com as chaves localizadas
     *                       entre os delimitadores.
     * @param firstDelimiter Delimitador do início do trecho a ser substituído.
     * @param lastDelimiter  Delimitador do final do trecho a ser substituído.
     *
     * @return String com os trechos localizados entre os conjuntos de delimitadores pelos valores contidos no mapa
     * informado.
     *
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    public static String replacePartsWithMap( String sourceString, final Map< String, String > targetPartsMap, final
    String firstDelimiter,
                                              final String lastDelimiter ) {
        if ( StringTools.isNullEmptyOrBlank( sourceString ) ) {
            return sourceString;
        }
        else if ( CollectionAndMapTools.mapIsNullOrEmpty( targetPartsMap ) ) {
            return sourceString;
        }
        else if ( StringTools.isNullEmptyOrBlank( firstDelimiter ) || StringTools.isNullEmptyOrBlank( lastDelimiter )
                ) {
            return sourceString;
        }
        else {
            final Collection< String > extractedParts = StringTools.extractParts( sourceString, firstDelimiter,
                    lastDelimiter );

            if ( !CollectionAndMapTools.collectionIsNullOrEmpty( extractedParts ) ) {
                for ( String extractedPart : extractedParts ) {
                    if ( targetPartsMap.containsKey( extractedPart ) ) {
                        sourceString = sourceString.replace( ( firstDelimiter + extractedPart + lastDelimiter ),
                                targetPartsMap.get( extractedPart ) );
                    }
                }
            }

            return sourceString;
        }
    }

}