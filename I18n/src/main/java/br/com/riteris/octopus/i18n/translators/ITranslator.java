package br.com.riteris.octopus.i18n.translators;

import br.com.riteris.octopus.i18n.sources.I18NSource;

import java.time.DayOfWeek;

/**
 * Define os objetos capazes de utilizarem pacotes de recursos de internacionalização para traduzir os recursos
 * passíveis de internacionalização dos objetos que
 * implementam a interface {@link I18NSource}.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 16 de abr de 2016
 */
public interface ITranslator {

    /**
     * Obtém uma data formatada na forma curta e sem informação de horário, conforme os padrões do idioma utilizado
     * pelo presente tradutor.
     *
     * @return Data formatada na forma curta e sem informação de horário, conforme padrões do idioma utilizado pelo
     * presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getShortDateWithoutTimeFormatted();

    /**
     * Obtém uma data formatada na forma curta e com informação de horário, conforme os padrões do idioma utilizado
     * pelo presente tradutor.
     *
     * @return Data formatada na forma curta e com informação de horário, conforme padrões do idioma utilizado pelo
     * presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getShortDateWithTimeFormatted();

    /**
     * Obtém uma data formatada na forma longa e sem informação de horário, conforme os padrões do idioma utilizado
     * pelo presente tradutor.
     *
     * @return Data formatada na forma longa e sem informação de horário, conforme padrões do idioma utilizado pelo
     * presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getLongDateWithoutTimeFormatted();

    /**
     * Obtém uma data formatada na forma longa e com informação de horário, conforme os padrões do idioma utilizado
     * pelo presente tradutor.
     *
     * @return Data formatada na forma longa e com informação de horário, conforme padrões do idioma utilizado pelo
     * presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getLongDateWithTimeFormatted();

    /**
     * Obtém a informação de horário formatada, segundo os padrões do idioma utilizado pelo presente tradutor e
     * respeitando o formato de relógio de 24 ou 12
     * horas, conforme também configurado segundo o idioma do presente tradutor.
     *
     * @return Informação e horário formatada, segundo os padrões do idioma do presente tradutor e seguindo a
     * configuração do formato do relógio de 24 ou 12
     * horas.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getTimeFormatted();

    /**
     * Obtém o valor do enum {@link DayOfWeek} referente ao primeiro dia da semana conforme o idioma referente ao
     * presente tradutor.
     *
     * @return Valor do enum {@link DayOfWeek} referente ao primeiro dia da semana conforme o idioma referente ao
     * presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    DayOfWeek getFirstDayOfWeek();

    /**
     * Obtém o nome do dia semana traduzido conforme o idioma referente ao presente tradutor.
     *
     * @param dayOfWeek Valor do enum {@link DayOfWeek} referente ao dia da semana o qual desejamos obter o valor
     *                  traduzido.
     *
     * @return Nome do dia da semana traduzido conforme o idioma referente ao presente tradutor.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getDayOfWeekName( DayOfWeek dayOfWeek );

    /**
     * Traduz um determinado texto, utilizando os recursos fornecidos ao presente tradutor, conforme o idioma
     * configurado.
     *
     * @param textToBeTranslated Texto o qual desejamos obter a sua tradução no idioma configurado.
     *
     * @return Texto traduzido conforme o idioma configurado no presnete tradutor e os seus recursos fornecidos.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getTextTranslated( String textToBeTranslated );

    /**
     * Obtém o conjunto de caracteres que indica o início de trecho de texto a ser traduzido.
     *
     * @return Conjunto de caracteres que indica o início do trecho de texto a ser traduzido.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getI18NStartSeparator();

    /**
     * Obtém o conjunto de caracteres que indica o fim de trecho de texto a ser traduzido.
     *
     * @return Conjunto de caracteres que indica o fim do trecho de texto a ser traduzido.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    String getI18NEndSeparator();

}