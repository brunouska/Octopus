package br.com.riteris.octopus.i18n.translators;

import br.com.riteris.octopus.i18n.locales.Language;

/**
 * Define o comportamento do objeto responsável por fornecer tradutores configurados com os requisitos necessários ao
 * seu funcionamento e prover métodos para o
 * gerenciamento dos recursos de internacionalização.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 17 de abr de 2016
 */
public interface ITranslatorPool {

    /**
     * Método utilizado para fazr a atualização dos recursos de internacionalização para uma determinada linguagem. A
     * atualização se dá pelo processo de
     * recarregamento dos recursos por parte do pool.
     *
     * @param language Linguagem para a qual faremos a atualização dos recursos de internacionalização disponíveis.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    void reloadResouceBundle( Language language );

    /**
     * Fornece um tradutor para a linguagem desejada, informada como parâmetro. Caso não seja localizado um tradutor
     * para a linguagem informada, será fornecido
     * um tradutor para a linguagem padrão, ou nulo caso nenhum tradutor possa ser criado.
     *
     * @param language Linguagem para a qual desejamos um tradutor devidamente configurado.
     *
     * @return Tradutor configurado para a linguagem informada como parâmetro, pronto para utilização ou nulo caso
     * nenhum tradutor possa ser criado.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    ITranslator getReadyTranslator( Language language );

}