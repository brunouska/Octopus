package br.com.riteris.octopus.monitoring.error.aspects;

import br.com.riteris.octopus.messaging.email.IEmailSender;

import java.util.HashMap;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public class EmailErrorHandler implements IErrorHandler {

    private String mailTitle;

    private String receiverAddress;

    private String receiverName;

    private IEmailSender emailSender;

    public EmailErrorHandler( final String mailTitle, final String receiverAddress, final String receiverName, final IEmailSender emailSender ) {
        if ( stringIsNullOrEmptyOrBlank( mailTitle ) ) {
            throw new IllegalArgumentException( "The mail title must be configured." );
        }

        if ( stringIsNullOrEmptyOrBlank( receiverAddress ) ) {
            throw new IllegalArgumentException( "The mail receiver address must be configured." );
        }

        if ( stringIsNullOrEmptyOrBlank( receiverName ) ) {
            throw new IllegalArgumentException( "The mail receiver name must be configured." );
        }

        if ( emailSender == null ) {
            throw new IllegalArgumentException( "The mail sender object can't be null." );
        }

        this.mailTitle = mailTitle;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.emailSender = emailSender;
    }

    @Override
    public void handleError( final ErrorInfo errorInfo ) {
        if ( errorInfo == null ) {
            throw new IllegalArgumentException( "The error information object can't be null." );
        }

        HashMap< String, String > receivers = new HashMap<>();
        receivers.put( this.receiverName, this.receiverAddress );

        this.emailSender.send( this.mailTitle, receivers, errorInfo.toString(), true, null );
    }

}
