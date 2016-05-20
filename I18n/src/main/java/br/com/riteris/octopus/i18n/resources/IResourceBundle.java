package br.com.riteris.octopus.i18n.resources;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * Define o comportamento dos objetos que provêm recursos de internacionalização.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 16 de abr de 2016
 */
public interface IResourceBundle {

    /**
     * Obtém o primeiro dia da semana, segundo o idioma referente ao pacote de recursos de internacionalização,
     * encapsulado no valor do enum {@link DayOfWeek}.
     *
     * @return Valor do enum {@link DayOfWeek} representando o primeiro dia da semana conforme o idioma referente ao
     * pacote de recursos de internacionalização.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    DayOfWeek getFirstDayOfWeek();

    /**
     * Retorna um mapa contendo os nomes dos dias da semana para os valores dos enums {@link DayOfWeek}.
     *
     * @return Mapa contendo os nomes dos dias da semana para os valores dos enums {@link DayOfWeek}.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    Map< DayOfWeek, String > getDaysOfWeekNames();

    /**
     * Retorna uma flag que indica se conforme o idioma referete ao pacote de recursos de internacionalização, o
     * formato de relógio utilizado é de 24 horas
     * (true) ou 12 horas (false).
     *
     * @return Flag indicando se conforme o idioma referente ao pacote de recursos de internacionalização é de 24
     * horas (true) ou de 12 horas (false).
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    boolean use24HourFormat();

    /**
     * Obtém o formato de data longo para uso em formatações de data que não contenham informação de horário em sua
     * representação.
     *
     * @return Formato de data longo, para datas que não usem informações de horário em sua representação.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getLongDateFormatWithoutTime();

    /**
     * Obtém o formato de data longo para uso em formatações de data que contenham informação de horário em sua
     * representação.
     *
     * @return Formato de data longo, para datas que usem informações de horário em sua representação.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getLongDateFormatWithTime();

    /**
     * Obtém o formato de data curto para uso em formatações de data que não contenham informação de horário em sua
     * representação.
     *
     * @return Formato de data curto, para datas que não usem informações de horário em sua representação.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getShortDateFormatWithoutTime();

    /**
     * Obtém o formato de data curto para uso em formatações de data que contenham informação de horário em sua
     * representação.
     *
     * @return Formato de data curto, para datas que usem informações de horário em sua representação.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getShortDateFormatWithTime();

    /**
     * Obtém o dígito utilizado para separação dos milhares, conforme o idioma referente ao pacote de recursos de
     * internacionalização.
     *
     * @return Dígito utilizado para separação dos milhares, conforme o idioma referente ao pacote de recursos de
     * internacionalização.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getThousandsSeparator();

    /**
     * Obtém o dígito utilizado para separação dos decimais, conforme o idioma referente ao pacote de recursos de
     * internacionalização.
     *
     * @return Dígito utilizado para separação dos decimais, conforme o idioma referente ao pacote de recursos de
     * internacionalização.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getDecimalSeparator();

    /**
     * Obtém a sigla monetária, conforme o idioma referente ao pacote de recursos de internacionalização.
     *
     * @return Sigla monetária, conforme o idioma referente ao pacote de recursos de internacionalização.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getDefaultCurrencySymbol();

    /**
     * Obtém um determinado recurso internacionalizado, baseado na chave do recurso.
     *
     * @param resourceKey Chave para localização do recurso internacionalizado.
     *
     * @return Recurso internacionalizado, baseado na chave fornecida.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    String getI18NResourceByKey( String resourceKey );

}