package br.com.riteris.octopus.messaging.email;

import br.com.riteris.octopus.utils.CollectionAndMapTools;
import br.com.riteris.octopus.utils.StringTools;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.net.URL;
import java.util.Map;

/**
 * Objeto para envio de e-mails, o qual implementa a interface {@link IEmailSender} para utilização com servidores
 * SMTP que requerem autenticação de usuário
 * para o envio.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do utilitário.
 * @since 1.0.0 - Criada em 13 de mar de 2016
 */
public final class AuthenticatedEmailSender implements IEmailSender {

    /**
     * Endereço do remetente do e-mail.
     */
    private final String senderAddress;

    /**
     * Nome do remetente do e-mail.
     */
    private final String senderName;

    /**
     * Endereço do servidor de envio de e-mails.
     */
    private final String smtpServerAddress;

    /**
     * Porta do endereço do servidor de envio de e-mails.
     */
    private final int smtpServerPort;

    /**
     * Login do usuário para autenticação no servidor de envio de e-mails.
     */
    private final String smtpServerLogin;

    /**
     * Senha do usuário para autenticação no servidor de envio de e-mails.
     */
    private final String smtpServerSecret;

    /**
     * Flag que indica se o servidor SMTP necessita de uma conexão SSL.
     */
    private final boolean requiresSSL;

    /**
     * Construtor do objeto. Avalia se todos os parâmetros necessários e obrigatórios ao funcionamento deste
     * utilitário foram fornecidos.
     *
     * @param senderAddress     {@link #senderAddress}
     * @param senderName        {@link #senderName}
     * @param smtpServerAddress {@link #smtpServerAddress}
     * @param smtpServerPort    {@link #smtpServerPort}
     * @param smtpServerLogin   {@link #smtpServerLogin}
     * @param smtpServerSecret  {@link #smtpServerSecret}
     * @param requiresSSL       {@link #requiresSSL}
     *
     * @since 1.0.0 - Criada em 13 de mar de 2016
     */
    public AuthenticatedEmailSender( final String senderAddress, final String senderName, final String
            smtpServerAddress, final int smtpServerPort,
                                     final String smtpServerLogin, final String smtpServerSecret, final boolean
                                             requiresSSL ) {
        if ( StringTools.isNullEmptyOrBlank( senderAddress ) ) {
            throw new IllegalArgumentException( "Sender address must be informed." );
        }

        if ( StringTools.isNullEmptyOrBlank( senderName ) ) {
            throw new IllegalArgumentException( "Sender name must be informed." );
        }

        if ( StringTools.isNullEmptyOrBlank( smtpServerAddress ) ) {
            throw new IllegalArgumentException( "The SMTP server address must be informed." );
        }

        if ( smtpServerPort <= 0 || smtpServerPort >= 65536 ) {
            throw new IllegalArgumentException( "The SMTP server address port must be informed correctly at range of " +
                    "1 to 65536." );
        }

        if ( StringTools.isNullEmptyOrBlank( smtpServerLogin ) ) {
            throw new IllegalArgumentException( "The SMTP server login must be informed." );
        }

        if ( StringTools.isNullEmptyOrBlank( smtpServerSecret ) ) {
            throw new IllegalArgumentException( "The SMTP server secret must be informed." );
        }

        this.senderAddress = senderAddress;
        this.senderName = senderName;
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
        this.smtpServerLogin = smtpServerLogin;
        this.smtpServerSecret = smtpServerSecret;
        this.requiresSSL = requiresSSL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void send( final String mailTitle, final Map< String, String > receiversNamesAndAddresses, final
    String mailMessage,
                            final boolean rawTextFormat, final Map< String, String > attachmentsNamesAndAddresses ) {
        if ( StringTools.isNullEmptyOrBlank( mailTitle ) ) {
            throw new IllegalArgumentException( "Mail title must be informed." );
        }

        if ( CollectionAndMapTools.mapIsNullOrEmpty( receiversNamesAndAddresses ) ) {
            throw new IllegalArgumentException( "At least one receiver address and his name must be informed." );
        }

        if ( receiversNamesAndAddresses.containsKey( null ) || receiversNamesAndAddresses.containsValue( null ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check " +
                    "receivers declared." );
        }

        if ( receiversNamesAndAddresses.containsKey( "" ) || receiversNamesAndAddresses.containsValue( "" ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check " +
                    "receivers declared." );
        }

        if ( receiversNamesAndAddresses.containsKey( " " ) || receiversNamesAndAddresses.containsValue( " " ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check " +
                    "receivers declared." );
        }

        if ( StringTools.isNullEmptyOrBlank( mailMessage ) ) {
            throw new IllegalArgumentException( "The mail message must be informed." );
        }

        if ( !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses ) && (
                attachmentsNamesAndAddresses.containsKey( null ) || attachmentsNamesAndAddresses.containsValue( null
                ) ) ) {
            throw new IllegalArgumentException( "The attachments map contains invalid values, please check " +
                    "attachments declared." );
        }

        if ( !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses ) && (
                attachmentsNamesAndAddresses.containsKey( "" ) || attachmentsNamesAndAddresses.containsValue( "" ) ) ) {
            throw new IllegalArgumentException( "The attachments map contains invalid values, please check " +
                    "attachments declared." );
        }

        if ( !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses ) && (
                attachmentsNamesAndAddresses.containsKey( " " ) || attachmentsNamesAndAddresses.containsValue( " " )
        ) ) {
            throw new IllegalArgumentException( "The attachments map contains invalid values, please check " +
                    "attachments declared." );
        }

        try {

            boolean hasAttachments = !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses );

            Email email = this.buildEmailBodyPart( mailTitle, mailMessage, rawTextFormat, hasAttachments );

            for ( String receiverName : receiversNamesAndAddresses.keySet() ) {
                email.addTo( receiversNamesAndAddresses.get( receiverName ), receiverName );
            }

            if ( hasAttachments ) {
                for ( String attachmentName : attachmentsNamesAndAddresses.keySet() ) {
                    this.addAttachmentToEmail( email, attachmentName, attachmentsNamesAndAddresses.get(
                            attachmentName ) );
                }
            }

            email.send();

        } catch ( Exception ex ) {

            throw new RuntimeException( "An error has occurred in attempt to send a mail message: " + ex.getMessage() );

        }
    }

    /**
     * Constrói a parte do corpo do e-mail, levando em consideração os diversos tipos de e-mail (texto puro, HTML, com
     * / sem anexos), já com os dados do
     * servidor de envio de e-mails configurados.
     *
     * @param mailTitle      Título do e-mail.
     * @param mailMessage    Mensagem do corpo do e-mail.
     * @param rawTextFormat  Flag que indica se a mensagem do corpo do e-mail é um texto puro ou HTML.
     * @param hasAttachments Flag que indica se o e-mail possui ou não anexos.
     *
     * @return Objeto do tipo {@link Email} contendo toda a parte referente ao corpo do e-mail e as informações do
     * servidor de envio.
     *
     * @throws Exception Erro lançado caso ocorra algum problema com a criação do objeto.
     * @since 1.0.0 - Criada em 9 de abr de 2016
     */
    private Email buildEmailBodyPart( final String mailTitle, final String mailMessage, final boolean
            rawTextFormat,
                                      final boolean hasAttachments ) throws Exception {
        Email email;

        if ( !rawTextFormat ) {
            email = new ImageHtmlEmail();
            ( ( ImageHtmlEmail ) email ).setDataSourceResolver( new DataSourceUrlResolver( new URL( "http://www" +
                    ".google.com" ) ) );
        }
        else {
            if ( hasAttachments ) {
                email = new MultiPartEmail();
            }
            else {
                email = new SimpleEmail();
            }
        }

        if ( requiresSSL ) {
            email.setSSLOnConnect( true );
            email.setSslSmtpPort( String.valueOf( this.smtpServerPort ) );
        }
        else {
            email.setSSLOnConnect( false );
            email.setSmtpPort( this.smtpServerPort );
        }

        email.setHostName( this.smtpServerAddress );
        email.setAuthentication( this.smtpServerLogin, this.smtpServerSecret );
        email.setCharset( EmailConstants.UTF_8 );
        email.setDebug( true );
        email.setFrom( this.senderAddress, this.senderName );
        email.setSubject( mailTitle );

        if ( rawTextFormat ) {
            email.setMsg( mailMessage );
        }
        else {
            ( ( ImageHtmlEmail ) email ).setHtmlMsg( mailMessage );
        }

        return email;
    }

    /**
     * Dado um determinado objeto do tipo {@link Email}, adiciona anexos ao mesmo.
     *
     * @param email          Objeto do tipo {@link Email}, no qual serão adicionados os anexos.
     * @param attachmentName Nome do anexo, conforme será exibido no leitor de e-mails do(s) destinatário(s).
     * @param attachmentPath Caminho do arquivo referente ao anexo, para sua leitura e inclusão no e-mail como anexo.
     *
     * @throws Exception Eror lançado caso ocorra algum problema relacionado à adição do anexo ao e-mail.
     * @since 1.0.0 - Criada em 9 de abr de 2016
     */
    private void addAttachmentToEmail( final Email email, final String attachmentName, final String
            attachmentPath ) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath( attachmentPath );
        attachment.setDisposition( EmailAttachment.ATTACHMENT );
        attachment.setName( attachmentName );

        ( ( MultiPartEmail ) email ).attach( attachment );
    }

}