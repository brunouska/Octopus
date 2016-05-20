package br.com.riteris.octopus.i18n.translators;

import br.com.riteris.octopus.i18n.locales.Language;

/**
 * Implementação padrão da interface {@link ITranslatorPool}.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 18 de abr de 2016
 */
public class DefaultTranslatorPool implements ITranslatorPool {

	/* ResourceBundleProvider e Separadores */

    /**
     * Conjunto de caracteres que simbolizam o delimitador esquerdo de textos para tradução.
     */
    private String leftTextDelimiter;

    /**
     * Conjunto de caracteres que simbolizam o delimitador direito de textos para tradução.
     */
    private String rightTextDelimiter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadResouceBundle( Language language ) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITranslator getReadyTranslator( Language language ) {
        // TODO Auto-generated method stub
        return null;
    }

}