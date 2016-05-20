package br.com.riteris.octopus.i18n.resources;

import br.com.riteris.octopus.i18n.locales.Language;

import java.util.Map;

/**
 * Define o objeto capaz de providenciar os pacotes de recursos de internacionalização.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 16 de abr de 2016
 */
public interface IResourceBundleProvider {

    /**
     * Obtém os pacotes de recursos de internacionalização, separados por linguagem para utilização por parte dos
     * objetos responsáveis pela internacionalização
     * dos recursos.
     *
     * @return Pacotes de recursos de internacionalização, separados por linguagem.
     *
     * @since 1.0.0 - Criada em 17 de abr de 2016
     */
    Map< Language, IResourceBundle > getConfiguredBundles();

}