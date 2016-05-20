package br.com.riteris.octopus.i18n.sources;

import br.com.riteris.octopus.i18n.translators.ITranslator;
import br.com.riteris.octopus.i18n.translators.ITranslatorPool;

/**
 * Define os objetos os quais possuem recursos passíveis de internacionalização. São objetos os quais será fornecido
 * um tradutor de recursos válido para que
 * possam internacionalizar seus recursos.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 15 de abr de 2016
 */
public interface I18NSource {

    /**
     * Internacionaliza os recursos do objeto, utilizando um tradutor configurado com o idioma desejado, fornecido
     * pelo { {@link ITranslatorPool} previamente
     * configurado e instanciado pelo sistema.
     *
     * @param translator Tradutor previamente configurado com o idioma desejado, pronto para traduzir os recursos
     *                   passíveis de internacionalização do presente objeto.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    void translateResources( ITranslator translator );

}