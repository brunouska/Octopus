package br.com.riteris.octopus.messaging.email;

import br.com.riteris.octopus.utils.CollectionAndMapTools;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.net.URL;
import java.util.Map;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public final class AuthenticatedEmailSender implements IEmailSender {

    private final String senderAddress;

    private final String senderName;

    private final String smtpServerAddress;

    private final int smtpServerPort;

    private final String smtpServerLogin;

    private final String smtpServerSecret;

    private final boolean requiresSSL;

    public AuthenticatedEmailSender( final String senderAddress, final String senderName, final String smtpServerAddress, final int smtpServerPort,
                                     final String smtpServerLogin, final String smtpServerSecret, final boolean requiresSSL ) {
        if ( stringIsNullOrEmptyOrBlank( senderAddress ) ) {
            throw new IllegalArgumentException( "Sender address must be informed." );
        }

        if ( stringIsNullOrEmptyOrBlank( senderName ) ) {
            throw new IllegalArgumentException( "Sender name must be informed." );
        }

        if ( stringIsNullOrEmptyOrBlank( smtpServerAddress ) ) {
            throw new IllegalArgumentException( "The SMTP server address must be informed." );
        }

        if ( smtpServerPort <= 0 || smtpServerPort >= 65536 ) {
            throw new IllegalArgumentException( "The SMTP server address port must be informed correctly at range of 1 to 65536." );
        }

        if ( stringIsNullOrEmptyOrBlank( smtpServerLogin ) ) {
            throw new IllegalArgumentException( "The SMTP server login must be informed." );
        }

        if ( stringIsNullOrEmptyOrBlank( smtpServerSecret ) ) {
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

    @Override
    public final void send( final String mailTitle, final Map< String, String > receiversNamesAndAddresses, final String mailMessage, final boolean rawTextFormat,
                            final Map< String, String > attachmentsNamesAndAddresses ) {
        if ( stringIsNullOrEmptyOrBlank( mailTitle ) ) {
            throw new IllegalArgumentException( "Mail title must be informed." );
        }

        if ( stringIsNullOrEmptyOrBlank( mailMessage ) ) {
            throw new IllegalArgumentException( "The mail message must be informed." );
        }

        this.verifyReceivers( receiversNamesAndAddresses );
        this.verifyAttachments( attachmentsNamesAndAddresses );

        try {
            boolean hasAttachments = !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses );

            Email email = this.buildEmailBodyPart( mailTitle, mailMessage, rawTextFormat, hasAttachments );

            for ( String receiverName : receiversNamesAndAddresses.keySet() ) {
                email.addTo( receiversNamesAndAddresses.get( receiverName ), receiverName );
            }

            if ( hasAttachments ) {
                for ( String attachmentName : attachmentsNamesAndAddresses.keySet() ) {
                    this.addAttachmentToEmail( email, attachmentName, attachmentsNamesAndAddresses.get( attachmentName ) );
                }
            }

            email.send();
        } catch ( Exception ex ) {
            throw new RuntimeException( "An error has occurred in attempt to send a mail message: " + ex.getMessage() );
        }
    }

    private void verifyReceivers( final Map< String, String > receiversNamesAndAddresses ) {
        if ( CollectionAndMapTools.mapIsNullOrEmpty( receiversNamesAndAddresses ) ) {
            throw new IllegalArgumentException( "At least one receiver address and his name must be informed." );
        }

        if ( receiversNamesAndAddresses.containsKey( null ) || receiversNamesAndAddresses.containsValue( null ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check receivers declared." );
        }

        if ( receiversNamesAndAddresses.containsKey( "" ) || receiversNamesAndAddresses.containsValue( "" ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check receivers declared." );
        }

        if ( receiversNamesAndAddresses.containsKey( " " ) || receiversNamesAndAddresses.containsValue( " " ) ) {
            throw new IllegalArgumentException( "The receiver addresses map contains invalid values, please check receivers declared." );
        }
    }

    private void verifyAttachments( final Map< String, String > attachmentsNamesAndAddresses ) {
        if ( !CollectionAndMapTools.mapIsNullOrEmpty( attachmentsNamesAndAddresses ) ) {
            if ( attachmentsNamesAndAddresses.containsKey( null ) || attachmentsNamesAndAddresses.containsValue( null ) ) {
                throw new IllegalArgumentException( "The attachments map contains invalid values, please check attachments declared." );
            }

            if ( attachmentsNamesAndAddresses.containsKey( "" ) || attachmentsNamesAndAddresses.containsValue( "" ) ) {
                throw new IllegalArgumentException( "The attachments map contains invalid values, please check attachments declared." );
            }

            if ( attachmentsNamesAndAddresses.containsKey( " " ) || attachmentsNamesAndAddresses.containsValue( " " ) ) {
                throw new IllegalArgumentException( "The attachments map contains invalid values, please check attachments declared." );
            }
        }
    }

    private Email buildEmailBodyPart( final String mailTitle, final String mailMessage, final boolean rawTextFormat, final boolean hasAttachments ) throws Exception {
        final Email email;

        if ( !rawTextFormat ) {
            email = new ImageHtmlEmail();

            ( ( ImageHtmlEmail ) email ).setDataSourceResolver( new DataSourceUrlResolver( new URL( "http://www.google.com" ) ) );
            ( ( ImageHtmlEmail ) email ).setHtmlMsg( mailMessage );
        } else {
            if ( hasAttachments ) {
                email = new MultiPartEmail();
            } else {
                email = new SimpleEmail();
            }

            email.setMsg( mailMessage );
        }

        if ( requiresSSL ) {
            email.setSSLOnConnect( true );
            email.setSslSmtpPort( String.valueOf( this.smtpServerPort ) );
        } else {
            email.setSSLOnConnect( false );
            email.setSmtpPort( this.smtpServerPort );
        }

        email.setHostName( this.smtpServerAddress );
        email.setAuthentication( this.smtpServerLogin, this.smtpServerSecret );
        email.setCharset( EmailConstants.UTF_8 );
        email.setDebug( true );
        email.setFrom( this.senderAddress, this.senderName );
        email.setSubject( mailTitle );

        return email;
    }

    private void addAttachmentToEmail( final Email email, final String attachmentName, final String attachmentPath ) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath( attachmentPath );
        attachment.setDisposition( EmailAttachment.ATTACHMENT );
        attachment.setName( attachmentName );

        ( ( MultiPartEmail ) email ).attach( attachment );
    }

}