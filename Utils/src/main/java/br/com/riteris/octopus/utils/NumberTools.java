package br.com.riteris.octopus.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;

/**
 * Conjunto de métodos utilitários para a manipulação de números. Este objeto, por tratar-se de um conjunto de
 * utilitários, foi projetado para se integrar de
 * forma transparente ao código fonte fornecendo métodos utilitários, os quais foram projetados com programação
 * defensiva para não lançarem exceções em seu
 * funcionamento interno.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 19 de out de 2015
 */
public final class NumberTools {

    /**
     * Construtor do objeto, com visibilidade privada, pois o objeto é um utilitário portador de métodos estáticos.
     *
     * @since 1.0.0 - Criada em 20 de out de 2015
     */
    private NumberTools() {
    }

    /**
     * Efetua a média dos valores retornados, susbstituindo os valores acima ou abaixo do percentual de picos definido
     * como parâmetro pela média simples dos
     * valores e realizando uma nova média com os picos substituídos pela média. Caso a listagem de números informados
     * para o cálculo seja nula ou vazia ou o
     * indicador de precisão ou limite de picos sejam negativos, será retornado o valor NULL como resposta. O
     * arredondamento utilizado é o padrão pra cima, ex.:
     * 4,45 = 4,5 caso a precisão seja de um dígito ou 4,445 = 4,45 caso a precisão seja de dois dígitos.<br>
     * <br>
     * Ex.: Para os valores 2,3,4,5,10 com inicador de precisão 1 e limite de picos de 10.0%, a média simples entre os
     * números seria 4.8, como o limite de picos
     * é de 10.0%, os números acime de 5.28 ou abaixo de 4.32 seriam substituidos pela média simples. Por fim, após as
     * substituições dos picos abaixo e acima, o
     * cálculo da nova média seria entre os valores 4.8,4.8,4.8,5 e 4.8 resultando em um valor de 4.8 dada a precisão
     * informada de um dígito.
     *
     * @param numbers   Números os quais será aplicada a média, substituindo os picos pelo valor da média.
     * @param precision Precisão de dígitos para o resultado da média.
     * @param peakLimit Percentual de limite para estabelecimento dos picos.
     *
     * @return Valor da média dos números informados com os picos substituídos ou NULL caso haja algum problema com a
     * listagem de números, percentual de limite
     * de picos ou o indicador de precisão informados.
     *
     * @since 1.0.0 - Criada em 19 de out de 2015
     */
    public static BigDecimal averageWithPeakExchange( Collection< BigDecimal > numbers, int precision, BigDecimal
            peakLimit ) {
        if ( CollectionAndMapTools.collectionIsNullOrEmpty( numbers ) || precision < 0 || ( peakLimit == null ||
                peakLimit.doubleValue() < 0 ) ) {
            return null;
        }

        BigDecimal simpleAverage = NumberTools.simpleAverage( numbers, precision );

        if ( simpleAverage == null ) return null;

        BigDecimal limitFactor = simpleAverage.multiply( peakLimit.divide( BigDecimal.valueOf( 100 ), 2, BigDecimal
                .ROUND_HALF_UP ) );
        BigDecimal minimumLimit = simpleAverage.subtract( limitFactor );
        BigDecimal maximumLimit = simpleAverage.add( limitFactor );

        BigDecimal sum = BigDecimal.ZERO;

        for ( BigDecimal number : numbers ) {
            if ( number.compareTo( minimumLimit ) < 0 && number.compareTo( maximumLimit ) > 0 ) {
                sum = sum.add( simpleAverage );
            }
            else {
                sum = sum.add( number );
            }
        }

        return sum.divide( BigDecimal.valueOf( numbers.size() ), precision, BigDecimal.ROUND_HALF_UP );
    }

    /**
     * Efetua o cálculo de média simples retornando o resultado com a precisão de dígitos informada no parâmetro. O
     * cálculo de média utilizada o arredondamento
     * padrão pra cima, ex.: 4,45 = 4,5 caso a precisão seja de um dígito ou 4,445 = 4,45 caso a precisão seja de dois
     * dígitos. Caso a listagem de números
     * informados para o cálculo seja nula ou vazia ou o indicador de precisão seja negativo, será retornado o valor
     * NULL como resposta.
     *
     * @param numbers   Números aos quais desejamos aplicar a média.
     * @param precision Precisão de dígitos para o resultado da média.
     *
     * @return Valor da média simples dos números informados ou NULL caso haja algum problema com a listagem de
     * números ou o indicador de precisão informados.
     *
     * @since 1.0.0 - Criada em 19 de out de 2015
     */
    public static BigDecimal simpleAverage( Collection< BigDecimal > numbers, int precision ) {
        if ( CollectionAndMapTools.collectionIsNullOrEmpty( numbers ) || precision < 0 ) {
            return null;
        }

        if ( numbers.size() == 1 ) {
            return numbers.iterator().next();
        }

        BigDecimal sum = BigDecimal.ZERO;

        for ( BigDecimal number : numbers ) {
            sum = sum.add( number );
        }

        return sum.divide( BigDecimal.valueOf( numbers.size() ), precision, BigDecimal.ROUND_HALF_UP );
    }

    /**
     * Efetua a média dos valores retornados, retirando os valores acima ou abaixo do percentual de picos definido
     * como parâmetro pela média simples dos valores
     * e realizando uma nova média com os picos removidos. Caso a listagem de números informados para o cálculo seja
     * nula ou vazia ou o indicador de precisão ou
     * limite de picos sejam negativos, será retornado o valor NULL como resposta. O arredondamento utilizado é o
     * padrão pra cima, ex.: 4,45 = 4,5 caso a
     * precisão seja de um dígito ou 4,445 = 4,45 caso a precisão seja de dois dígitos.<br>
     * <br>
     * Ex.: Para os valores 2,3,4,5,10 com inicador de precisão 1 e limite de picos de 10.0%, a média simples entre os
     * números seria 4.8, como o limite de picos
     * é de 10.0%, os números acime de 5.28 ou abaixo de 4.32 seriam retirados. Por fim, após as remoções dos picos
     * abaixo e acima, o cálculo da nova média
     * seria apenas entre o valor 5 resultando automaticamente no valor 5.
     *
     * @param numbers   Números os quais será aplicada a média, cortando os picos.
     * @param precision Precisão de dígitos para o resultado da média.
     * @param peakLimit Percentual de limite para estabelecimento dos picos.
     *
     * @return Valor da média dos números informados com os picos cortados ou NULL caso haja algum problema com a
     * listagem de números, percentual de limite de
     * picos ou o indicador de precisão informados.
     *
     * @since 1.0.0 - Criada em 19 de out de 2015
     */
    public static BigDecimal averageWithPeakCutOff( Collection< BigDecimal > numbers, int precision, BigDecimal
            peakLimit ) {
        if ( CollectionAndMapTools.collectionIsNullOrEmpty( numbers ) || precision < 0 || ( peakLimit == null ||
                peakLimit.doubleValue() < 0 ) ) {
            return null;
        }

        BigDecimal simpleAverage = NumberTools.simpleAverage( numbers, precision );

        if ( simpleAverage == null ) return null;

        BigDecimal limitFactor = simpleAverage.multiply( peakLimit.divide( BigDecimal.valueOf( 100 ), 2, BigDecimal
                .ROUND_HALF_UP ) );
        BigDecimal minimumLimit = simpleAverage.subtract( limitFactor );
        BigDecimal maximumLimit = simpleAverage.add( limitFactor );

        BigDecimal sum = BigDecimal.ZERO;
        int elementsIncluded = 0;

        for ( BigDecimal number : numbers ) {
            if ( number.compareTo( minimumLimit ) >= 0 && number.compareTo( maximumLimit ) <= 0 ) {
                sum = sum.add( number );
                elementsIncluded++;
            }
        }

        if ( elementsIncluded > 0 ) {
            return sum.divide( BigDecimal.valueOf( elementsIncluded ), precision, BigDecimal.ROUND_HALF_UP );
        }
        else {
            return simpleAverage;
        }
    }

    /**
     * Retorna um determinado número formatado como uma String, utilizando a precisão de dígitos informada para
     * realizar o arredondamento caso necessário. O
     * arredondamento utilizado é o padrão pra cima, ex.: 4,45 = 4,5 caso a precisão seja de um dígito ou 4,445 = 4,45
     * caso a precisão seja de dois dígitos.
     * Caso o número informado seja nulo ou a precisão desejada seja negativa, será retornado NULL como resposta. Caso
     * o conjunto dos separadores não forem
     * informados será utilizado o separador padrão da linguagem do sistema operacional no qual a ferramenta esteja
     * sendo executada.
     *
     * @param rawNumber         Número para formataçao e arredondamento caso necessário.
     * @param precision         Precisão de dígitos para o número formatado.
     * @param decimalSeparator  Caractere para utilização na separação de decimais. O separador deve conter apenas um
     *                          caractere, caso contenha mais de um caractere, apenas o
     *                          primeiro caractere será considerado como separador.
     * @param tousandsSeparator Caractere para utilização na separação de milhares. O separador deve conter apenas um
     *                          caractere, caso contenha mais de um caractere, apenas o
     *                          primeiro caractere será considerado como separador.
     *
     * @return Número formatado conforme os parâmetros informados e a precisão desejada.
     *
     * @since 1.0.0 - Criada em 19 de out de 2015
     */
    public static String getNumberFormatted( Object rawNumber, int precision, String decimalSeparator, String
            tousandsSeparator ) {
        if ( rawNumber == null || precision < 0 ) {
            return null;
        }

        BigDecimal number;

        if ( rawNumber instanceof Number ) {
            number = new BigDecimal( ( ( Number ) rawNumber ).doubleValue() );
        }
        else {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat();

        String pattern = "#,##0.";

        for ( int i = 0; i < precision; i++ ) {
            pattern += "0";
        }

        decimalFormat.applyPattern( pattern );

        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();

        if ( !StringTools.isNullEmptyOrBlank( tousandsSeparator ) ) {
            decimalFormatSymbols.setGroupingSeparator( tousandsSeparator.charAt( 0 ) );
        }

        if ( !StringTools.isNullEmptyOrBlank( decimalSeparator ) ) {
            decimalFormatSymbols.setDecimalSeparator( decimalSeparator.charAt( 0 ) );
        }

        decimalFormat.setDecimalFormatSymbols( decimalFormatSymbols );

        return decimalFormat.format( number );
    }

}
