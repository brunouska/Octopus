package br.com.riteris.octopus.messaging.email;

import java.util.Map;

public interface IEmailSender {

    void send( String mailTitle, Map< String, String > receiversNamesAndAddresses, String mailMessage, boolean rawTextFormat,
               Map< String, String > attachmentsNamesAndAddresses );

}