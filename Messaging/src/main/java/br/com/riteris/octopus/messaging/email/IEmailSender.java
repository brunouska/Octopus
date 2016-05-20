package br.com.riteris.octopus.messaging.email;

import java.util.Map;

/**
 * Deifne os objetos que enviam e-mails no sistema.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação da interface.
 * @since 1.0.0 - Criada em 29 de mar de 2016
 */
public interface IEmailSender {

    /**
     * Executa o envio do e-mail com a mensagem informada como parâmetro.
     *
     * @param mailTitle                    Título do e-mail.
     * @param receiversNamesAndAddresses   Nomes e endereços dos destinatários do e-mail.
     * @param mailMessage                  Mensagem do corpo do e-mail
     * @param rawTextFormat                Flag que indica que o texto está em formato simples ou HTML.
     * @param attachmentsNamesAndAddresses Mapa contendo os endereços de arquivos de anexo juntamente aos seus nomes
     *                                     para exibição no e-mail.
     *
     * @since 1.0.0 - Criada em 29 de mar de 2016
     */
    void send( String mailTitle, Map< String, String > receiversNamesAndAddresses, String mailMessage, boolean
            rawTextFormat,
               Map< String, String > attachmentsNamesAndAddresses );

}