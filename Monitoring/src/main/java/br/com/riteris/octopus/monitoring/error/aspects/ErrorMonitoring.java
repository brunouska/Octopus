package br.com.riteris.octopus.monitoring.error.aspects;

import br.com.riteris.octopus.messaging.email.IEmailSender;
import br.com.riteris.octopus.utils.ObjectTools;
import br.com.riteris.octopus.utils.StringTools;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Interceptador de erros de execução de métodos após o lançamento de exceções. Criado para ser utilizado mediante o
 * uso de programação orientada a aspectos. Na
 * aplicação final, será configurado o contexto de proxy utilizando Spring AOP para dar suporte ao funcionamento
 * deste interceptador.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 6 de mar de 2016
 */
public class ErrorMonitoring {

    /**
     * Título do e-mail da mensagem do monitoramento de erros da aplicação.
     */
    private String mailTitle;

    /**
     * Endereço de destino do envio de mensagens de monitoramento de erros da aplicação.
     */
    private String receiverAddress;

    /**
     * Nome do destinatário do envio de mensagens de monitoramento de erros da aplicação.
     */
    private String receiverName;

    /**
     * Objeto capaz de realizar o envio do e-mail com a mensagem de erro.
     */
    private IEmailSender emailSender;

    /**
     * Altera o valor atual do parâmetro {@link #mailTitle}.<br>
     *
     * @param mailTitle Novo valor para {@link #mailTitle}
     */
    public void setMailTitle( String mailTitle ) {
        if ( StringTools.isNullEmptyOrBlank( mailTitle ) ) {
            throw new IllegalArgumentException( "The error monitoring mail title must be configured." );
        }

        this.mailTitle = mailTitle;
    }

    /**
     * Altera o valor atual do parâmetro {@link #receiverAddress}.<br>
     *
     * @param receiverAddress Novo valor para {@link #receiverAddress}
     */
    public void setReceiverAddress( String receiverAddress ) {
        if ( StringTools.isNullEmptyOrBlank( receiverAddress ) ) {
            throw new IllegalArgumentException( "The error monitoring mail receiver address must be configured." );
        }

        this.receiverAddress = receiverAddress;
    }

    /**
     * Altera o valor atual do parâmetro {@link #receiverName}.<br>
     *
     * @param receiverName Novo valor para {@link #receiverName}
     */
    public void setReceiverName( String receiverName ) {
        if ( StringTools.isNullEmptyOrBlank( receiverName ) ) {
            throw new IllegalArgumentException( "The error monitoring mail receiver name must be configured." );
        }

        this.receiverName = receiverName;
    }

    /**
     * Altera o valor atual do parâmetro {@link #emailSender}.<br>
     *
     * @param emailSender Novo valor para {@link #emailSender}
     */
    public void setEmailSender( IEmailSender emailSender ) {
        if ( emailSender == null ) {
            throw new IllegalArgumentException( "The error monitoring mail sender object cannot be null." );
        }

        this.emailSender = emailSender;
    }

    /**
     * Pointcut do aspecto de monitoramento de erros lançados em todos os métodos públicos da aplicação, criado com a
     * finalidade de rastrear todo e qualquer
     * tipo de erro ocorrido em métodos públicos (invocados externamente à aplicação).
     *
     * @param joinPoint Objeto que contém todas as informações do método invocado e o objeto onde foi invocado o
     *                  método que causou o erro de execução.
     * @param error     Erro lançado durante a execução do método em questão.
     *
     * @since 1.0.0 - Criada em 13 de mar de 2016
     */
    public final void interceptErrorAfterThrowing( final JoinPoint joinPoint, final Throwable error ) {
        if ( this.invalidRequeriments() ) {
            throw new IllegalStateException( "The main requeriments of this aspect wasn't configured." );
        }

        if ( joinPoint == null ) {
            throw new IllegalArgumentException( "There isn't join point informed to itercept." );
        }

        if ( error == null ) {
            throw new IllegalArgumentException( "There isn't error informed to describe." );
        }

        final String mailMessage = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<" + "\r\n" +
                "Object Name: " + joinPoint.getTarget().getClass().getCanonicalName() +
                "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Process Name: " + joinPoint.getSignature().getName() + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Process Parameters: " + "\r\n" + this.getArgumentsToString( joinPoint.getArgs() ) +
                "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Error Object: " + error.getClass().getCanonicalName() + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Error Menssage: " + ( error.getMessage() != null ? error.getMessage() : "N/A" ) +
                "\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        HashMap< String, String > receivers = new HashMap<>();
        receivers.put( this.receiverName, this.receiverAddress );

        this.emailSender.send( this.mailTitle, receivers, mailMessage, true, null );

        error.printStackTrace();
    }

    /**
     * Efetua a validação dos requerimentos básicos para o funcionamento do monitoramento, que são compostos pelo
     * título do e-mail de erro, nome e endereço do
     * destinatário do e-mail e o objeto que realiza o envio dos e-mails.
     *
     * @return True caso sejam localizados requerimentos inválidos.
     *
     * @since 1.0.0 - Criada em 15 de abr de 2016
     */
    private boolean invalidRequeriments() {
        int invalidRequerimentsFound = 0;

        if ( StringTools.isNullEmptyOrBlank( mailTitle ) ) {
            invalidRequerimentsFound++;
        }

        if ( StringTools.isNullEmptyOrBlank( receiverAddress ) ) {
            invalidRequerimentsFound++;
        }

        if ( StringTools.isNullEmptyOrBlank( receiverName ) ) {
            invalidRequerimentsFound++;
        }

        if ( emailSender == null ) {
            invalidRequerimentsFound++;
        }

        return ( invalidRequerimentsFound > 0 );
    }

    /**
     * Dada a matriz de argumentos passados ao método, monta o descritivo desses argumentos com seus valores ou
     * informa que não houveram parâmetros informados
     * ao mesmo.
     *
     * @param args Matriz de argumentos informados à execução do método.
     *
     * @return Descrição detalhada dos parâmetros passados ao método executado.
     *
     * @since 1.0.0 - Criada em 13 de mar de 2016
     */
    private String getArgumentsToString( final Object[] args ) {
        final StringBuilder result = new StringBuilder();

        if ( args != null && args.length > 0 ) {
            Iterator< Object > iterator = Arrays.asList( args ).iterator();

            while ( iterator.hasNext() ) {
                Object object = iterator.next();

                result.append( "    * " );
                result.append( ObjectTools.getObjectDescriptionResume( object ) );

                if ( iterator.hasNext() ) {
                    result.append( ", " ).append( "\r\n" );
                }
                else {
                    result.append( "." );
                }
            }
        }
        else {
            result.append( "    * This process don't have parameters." );
        }

        return result.toString();
    }

}