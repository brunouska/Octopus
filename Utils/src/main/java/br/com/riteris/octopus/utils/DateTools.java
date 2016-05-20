package br.com.riteris.octopus.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

/**
 * Conjunto de métodos utilitários para a manipulação de datas. Este objeto, por tratar-se de um conjunto de
 * utilitários, foi projetado para se integrar de
 * forma transparente ao código fonte fornecendo métodos utilitários, os quais foram projetados com programação
 * defensiva para não lançarem exceções em seu
 * funcionamento interno.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 20 de out de 2015
 */
public final class DateTools {

    /**
     * Construtor do objeto, com visibilidade privada, pois o objeto é um utilitário portador de métodos estáticos.
     *
     * @since 1.0.0 - Criada em 20 de out de 2015
     */
    private DateTools() {
    }

    /**
     * Dado um objeto que representa uma data, e um padrão de exibição, formata a data no padrão fornecido. Caso o
     * objeto de data ou formato sejam nulos, será
     * retornada uma String vazia. Caso o formato de exibição informado seja inválido, sera retornada uma String vazia.
     *
     * @param dateObj Objeto de data para obtermos a sua representação em String formatada no formato de exibição
     *                desejado.
     * @param format  Formato de exibição no qual desejamos que a data seja representada em String.
     *
     * @return Data formatada no formato de exibição desejado.
     *
     * @since 1.0.0 - Criada em 16 de nov de 2015
     */
    public static String formatDate( TemporalAccessor dateObj, String format ) {
        if ( dateObj == null || StringTools.isNullEmptyOrBlank( format ) ) {
            return "";
        }

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );

            return formatter.format( dateObj );

        } catch ( Exception e ) {

            return "";

        }
    }

    /**
     * Dado um determinado ano, calcula no número de dias que o ano possui. Caso o ano informado seja anterior ao ano
     * 1, será retornado 0 como número de dias do
     * ano.
     *
     * @param year Ano o qual desejamos saber o número de dias.
     *
     * @return Número de dias do ano informado como parâmetro.
     *
     * @since 1.0.0 - Criada em 16 de nov de 2015
     */
    public static long getYearDaysAmount( int year ) {
        if ( year < 1 ) {
            return 0;
        }

        LocalDate dateA = LocalDate.of( year, 1, 1 );
        LocalDate dateB = dateA.plusYears( 1 );

        return ChronoUnit.DAYS.between( dateA, dateB );
    }

    /**
     * Dada uma determinada data, retorna uma nova data no último dia do mês e ano da data informada. Caso a data
     * informada esteja nula, será retornado nulo
     * como resposta.
     *
     * @param date Data a qual desejamos obter nova data no último dia do mês, para o mesmo mês e ano da data
     *             informada.
     *
     * @return Nova data no último dia do mês e ano da data informada.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static LocalDate getDateAtLastDayOfMonth( LocalDate date ) {
        if ( date == null ) {
            return null;
        }

        return DateTools.getDateAtFirstDayOfMonth( date ).plusMonths( 1 ).minusDays( 1 );
    }

    /**
     * Dada uma determinada data, retorna uma nova data no primeiro dia do mês e ano da data informada. Caso a data
     * informada esteja nula, será retornado nulo
     * como resposta.
     *
     * @param date Data a qual desejamos obter nova data no primeiro dia do mês, para o mesmo mês e ano da data
     *             informada.
     *
     * @return Nova data no primeiro dia do mês e ano da data informada.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static LocalDate getDateAtFirstDayOfMonth( LocalDate date ) {
        if ( date == null ) {
            return null;
        }

        return LocalDate.of( date.getYear(), date.getMonth(), 1 );
    }

    /**
     * Dada uma data de referência (targetDate), datas de início e final do período (periodStart e periodEnd
     * respectivamente), verifica se a data de referência
     * está contida no período informado, sendo que a data de referência pode ser igual às datas de início e/ou final
     * do período informado. Caso algumas das
     * datas informadas estejam nulas, será retornado falso como resposta.
     *
     * @param targetDate  Data de referência, a qual desejamos saber se está contida no período informado.
     * @param periodStart Data do início do período de comparação.
     * @param periodEnd   Data do final do perídoo de comparação.
     *
     * @return True caso a data esteja contida no período informado (sendo igual às datas de início e/ou final do
     * período inclusive) ou false caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isDateBetweenPeriodInclusive( LocalDate targetDate, LocalDate periodStart, LocalDate
            periodEnd ) {
        return !( targetDate == null || periodStart == null || periodEnd == null ) && ( DateTools.isEqualOrAfterDate(
                targetDate, periodStart ) && DateTools.isEqualOrBeforeDate( targetDate, periodEnd ) );

    }

    /**
     * Dadas duas datas, verifica se a primeira data é igual ou posterior à segunda data. Caso uma das datas seja
     * nula, será retornado false como resposta.
     *
     * @param firstDate  Primeira data da comparaçao entre as duas datas.
     * @param secondDate Segunda data da comparação entre as duas datas.
     *
     * @return True caso a primeira data seja igual ou posterior à segunda data e false em caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isEqualOrAfterDate( LocalDate firstDate, LocalDate secondDate ) {
        return ( DateTools.isEqualDate( firstDate, secondDate ) || DateTools.isAfterDate( firstDate, secondDate ) );
    }

    /**
     * Dadas duas datas, verifica se a primeira data é igual ou anterior à segunda data. Caso uma das datas seja nula,
     * será retornado false como resposta.
     *
     * @param firstDate  Primeira data da comparaçao entre as duas datas.
     * @param secondDate Segunda data da comparação entre as duas datas.
     *
     * @return True caso a primeira data seja igual ou anterior à segunda data e false em caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isEqualOrBeforeDate( LocalDate firstDate, LocalDate secondDate ) {
        return ( DateTools.isEqualDate( firstDate, secondDate ) || DateTools.isBeforeDate( firstDate, secondDate ) );
    }

    /**
     * Dadas duas datas, verifica se as mesmas tratam-se da mesma data. Caso uma das datas seja nula, será retornado
     * false como resposta.
     *
     * @param firstDate  Primeira data da comparaçao entre as duas datas.
     * @param secondDate Segunda data da comparação entre as duas datas.
     *
     * @return True caso as datas sejam iguais e false em caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isEqualDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween == 0;
    }

    /**
     * Dadas duas datas, verifica se a primeira data é posterior à segunda data. Caso uma das datas seja nula, será
     * retornado false como resposta.
     *
     * @param firstDate  Primeira data da comparaçao entre as duas datas.
     * @param secondDate Segunda data da comparação entre as duas datas.
     *
     * @return True caso a primeira data seja posterior à segunda data e false em caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isAfterDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween < 0;
    }

    /**
     * Dadas duas datas, verifica se a primeira data é anterior à segunda data. Caso uma das datas seja nula, será
     * retornado false como resposta.
     *
     * @param firstDate  Primeira data da comparaçao entre as duas datas.
     * @param secondDate Segunda data da comparação entre as duas datas.
     *
     * @return True caso a primeira data seja anterior à segunda data e false em caso contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isBeforeDate( LocalDate firstDate, LocalDate secondDate ) {
        if ( firstDate == null || secondDate == null ) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between( firstDate, secondDate );

        return daysBetween > 0;
    }

    /**
     * Dada uma data de referência (targetDate), datas de início e final do período (periodStart e periodEnd
     * respectivamente), verifica se a data de referência
     * está contida no período informado, sendo que a data de referência deve ser posterior à data de início e
     * anterior à data final do período informado. Caso
     * algumas das datas informadas estejam nulas, será retornado falso como resposta.
     *
     * @param targetDate  Data de referência, a qual desejamos saber se está contida no período informado.
     * @param periodStart Data do início do período de comparação.
     * @param periodEnd   Data do final do perídoo de comparação.
     *
     * @return True caso a data esteja contida no período informado (sendo posterior à data de início e anterior à
     * data final do período) ou false caso
     * contrário.
     *
     * @since 1.0.0 - Criada em 18 de nov de 2015
     */
    public static boolean isDateBetweenPeriodExclusive( LocalDate targetDate, LocalDate periodStart, LocalDate
            periodEnd ) {
        return !( targetDate == null || periodStart == null || periodEnd == null ) && ( DateTools.isAfterDate(
                targetDate, periodStart ) && DateTools.isBeforeDate( targetDate, periodEnd ) );

    }

}
