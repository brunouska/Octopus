package br.com.riteris.octopus.i18n.translators;

import br.com.riteris.octopus.i18n.resources.IResourceBundle;

import java.time.DayOfWeek;

/**
 * Objeto que implementa a interface {@link ITranslator}, para ser utilizado como tradutor de comportamento padrão no
 * sistema.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 18 de abr de 2016
 */
public class DefaultTranslator implements ITranslator {

    /**
     * Objeto do tipo {@link IResourceBundle}, capaz de fornecer os recursos necessários à execução das traduções.
     */
    private IResourceBundle resourceBundle;

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
    public String getShortDateWithoutTimeFormatted() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortDateWithTimeFormatted() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLongDateWithoutTimeFormatted() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLongDateWithTimeFormatted() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTimeFormatted() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DayOfWeek getFirstDayOfWeek() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDayOfWeekName( DayOfWeek dayOfWeek ) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextTranslated( String textToBeTranslated ) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getI18NStartSeparator() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getI18NEndSeparator() {
        // TODO Auto-generated method stub
        return null;
    }

}
