package br.com.riteris.octopus.i18n.aspects;

import br.com.riteris.octopus.i18n.locales.Language;
import br.com.riteris.octopus.i18n.sources.I18NSource;
import br.com.riteris.octopus.i18n.translators.ITranslator;
import br.com.riteris.octopus.i18n.translators.ITranslatorPool;
import br.com.riteris.octopus.user.valueobjects.UserInfoVO;
import br.com.riteris.octopus.utils.CollectionAndMapTools;
import br.com.riteris.octopus.utils.StringTools;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Collection;
import java.util.Map;

/**
 * Aspecto responsável por interagir com a execução de métodos demarcados pelo {@link ProceedingJoinPoint}
 * configurado pela aplicação e realizar a tradução de
 * recursos que implementem a interface {@link I18NSource} utilizando o provedor de tradutores {@link ITranslatorPool}.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do aspecto.
 * @since 1.0.0 - Criada em 18 de abr de 2016
 */
public class TranslationAspect {

    /**
     * Define qual é o idioma padrão de traduções quando o idioma solicitado não se enquadrar em nenhum dos idiomas
     * disponíveis.
     */
    private Language defaultLanguage;

    /**
     * Pool de objetos capazes de realizar a tradução de conteúdos passíveis de internacionalização para os objetos
     * resultantes da execução dos métodos maepados
     * pelo {@link ProceedingJoinPoint} configurado na aplicação, desde que implementem a interface {@link I18NSource}.
     */
    private ITranslatorPool translatorPool;

    /**
     * Configura o valor do enum {@link Language} que deverá ser utilizado como idioma padrão. Caso o código informado
     * seja inválido (não seja possível
     * recuperar um valor válido a partir do código informado) será lançada uma exceção.
     *
     * @param languageCode Código do idioma que deverá ser utilizado como idioma padrão.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public void setDefaultLanguageCode( String languageCode ) {
        if ( StringTools.isNullEmptyOrBlank( languageCode ) ) {
            throw new IllegalArgumentException( "The default language code must be informed." );
        }

        this.defaultLanguage = Language.getLanguageByCode( languageCode );

        if ( this.defaultLanguage == null ) {
            throw new IllegalStateException( "The default language code informed is invalid." );
        }
    }

    /**
     * Altera o valor atual do parâmetro {@link #translatorPool}.<br>
     *
     * @param translatorPool Novo valor para {@link #translatorPool}
     */
    public void setTranslatorPool( ITranslatorPool translatorPool ) {
        if ( translatorPool == null ) {
            throw new IllegalArgumentException( "The translator pool must be configured." );
        }

        this.translatorPool = translatorPool;
    }

    /**
     * Efetua a validação dos requerimentos básicos para o funcionamento do aspecto de tradução, que é composto pelo
     * pool de objetos capazes de realizar a
     * tradução de conteúdos passíveis de internacionalização.
     *
     * @return True caso sejam localizados requerimentos inválidos.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    private boolean invalidRequeriments() {
        int invalidRequerimentsFound = 0;

        if ( translatorPool == null ) {
            invalidRequerimentsFound++;
        }

        return ( invalidRequerimentsFound > 0 );
    }

    /**
     * Efetua a tradução dos objetos retornados por métodos executados que estejam em acordo com o mapeamento do
     * {@link ProceedingJoinPoint} configurado na
     * aplicação. O processo interage com a execução do método e obtém o objeto retornado pela execução do método e
     * realiza a tradução dos conteúdos de objetos
     * que implementam a interface {@link I18NSource} ou de exceções lançadas em sua execução.
     *
     * @param proceedingJoinPoint Objeto do tipo {@link ProceedingJoinPoint} o qual contém as informações do método
     *                            que será executado para a captura do seu respectivo retorno
     *                            de execução.
     *
     * @return Retorna o objeto de retorno original do método executado, porém com seu conteúdo passível de tradução
     * devidamente traduzido.
     *
     * @throws Throwable Erro lançado caso ocorra algum problema ao solicitar ao {@link ProceedingJoinPoint} que
     *                   proceda com a execução do método.
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public final Object translateExecutionReturns( ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
        if ( this.invalidRequeriments() ) {
            throw new IllegalStateException( "The main requeriments of this aspect wasn't configured." );
        }

        if ( proceedingJoinPoint == null ) {
            throw new IllegalArgumentException( "There isn't proceeding join point informed to itercept." );
        }

        final Object[] args = proceedingJoinPoint.getArgs();

        Language language = this.getLanguageInfoFromArgs( args );

        ITranslator translator = this.translatorPool.getReadyTranslator( language );

        try {

            final Object result = proceedingJoinPoint.proceed();

            this.translateObject( result, translator );

            return result;

        } catch ( Exception ex ) {

            throw new RuntimeException( this.getExceptionMessageTranslated( ex, translator ) );

        }
    }

    /**
     * Tenta obter informações a respeito da linguagem utilizada na invocação do método, procurando pelo objeto
     * {@link UserInfoVO}, o qual possui informações
     * acerca da linguagem utilizada na invocação do método em questão.
     *
     * @param args Matriz de argumentos utilizados na invocação do método.
     *
     * @return Linguagem utilizada na invocação do método, obtida a partir do objeto {@link UserInfoVO} presente ou a
     * linguagem padrão caso não seja possível
     * obter informações acerca da linguagem utilizada.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    private Language getLanguageInfoFromArgs( Object[] args ) {
        Language result = null;

        if ( args != null && args.length > 0 ) {
            for ( Object arg : args ) {
                if ( arg != null && arg instanceof UserInfoVO ) {
                    String localeCode = ( ( UserInfoVO ) arg ).getLocaleCode();

                    result = Language.getLanguageByCode( localeCode );

                    if ( result == null )
                        result = Language.getLanguageByShortLanguageCode( localeCode );

                    if ( result == null )
                        result = Language.getLanguageByShortCountryCode( localeCode );

                    if ( result == null )
                        result = Language.getLanguageByName( localeCode );

                    break;
                }
            }
        }

        if ( result == null ) {
            return this.defaultLanguage;
        }
        else {
            return result;
        }
    }

    /**
     * Dado um objeto retornado pela execução de um método, verifica se o mesmo é um objeto que implementa a interface
     * {@link I18NSource} e traduz o seu
     * conteúdo passível de internacionalização ou caso o mesmo seja um mapa ou coleção, itera seus elementos e
     * processa de forma recursiva a procura por
     * objetos que implementem a interface {@link I18NSource} para sua respectiva tradução.
     *
     * @param object     Objeto retornado pela execução do método, o qual desejamos traduzir seu conteúdo se possível.
     * @param translator Objeto responsável por efetuar a tradução do conteúdo internacionalizável dos objetos.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    @SuppressWarnings( "unchecked" )
    private void translateObject( final Object object, final ITranslator translator ) {
        if ( translator == null ) {
            return;
        }

        if ( object == null ) {
            return;
        }

        if ( !( object instanceof Collection ) && !( object instanceof Map ) ) {
            ( ( I18NSource ) object ).translateResources( translator );
        }
        else {
            if ( object instanceof Collection && ( !CollectionAndMapTools.collectionIsNullOrEmpty( ( Collection<
                    Object > ) object ) ) ) {

                for ( final Object collectionItem : ( ( Collection< Object > ) object ) ) {
                    this.translateObject( collectionItem, translator );
                }
            }
            else if ( object instanceof Map && ( !CollectionAndMapTools.mapIsNullOrEmpty( ( Map< Object, Object > )
                    object ) ) ) {

                for ( final Object mapKeyItem : ( ( Map< Object, Object > ) object ).keySet() ) {
                    this.translateObject( ( ( Map< Object, Object > ) object ).get( mapKeyItem ), translator );
                }
            }
        }
    }

    /**
     * Efetua a tradução da mensagem de erro, caso seja lançado um erro na execução do método o qual este aspecto está
     * observando. Para que haja uma tradução de
     * mensagem de erro adequada, a mesma deve ter sido construída seguindo os padrões utilizáveis pelo tradutor
     * configurado, da mesma forma que o conteúdo
     * internacionalizável produzido nos objetos que implementam a interface {@link I18NSource}.
     *
     * @param exception  Objeto contendo o erro lançado para que possamos traduzir sua mensagem.
     * @param translator Objeto responsável por efetuar a tradução da mensagem de erro.
     *
     * @return Mensagem de erro traduzida.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    private String getExceptionMessageTranslated( final Exception exception, final ITranslator translator ) {
        String exceptionMessage = exception.getMessage();

        if ( StringTools.isNullEmptyOrBlank( exceptionMessage ) ) {
            exceptionMessage = exception.getClass().toString();
        }
        else {
            if ( translator != null ) {
                exceptionMessage = translator.getTextTranslated( exceptionMessage );
            }
        }

        return exceptionMessage;
    }

}