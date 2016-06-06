package br.com.riteris.octopus.messaging.email;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.smtp.auth.EasyAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.wiser.Wiser;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmpty;
import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testes do objeto enviador de e-mails, que utiliza servidores autenticados.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do teste.
 * @since 1.0.0 - Criada em 7 de abr de 2016
 */
public class AuthenticatedEmailSenderTest {

    /**
     * Nome de usuário utilizado no servidor de testes de envio de e-mail.
     */
    private static final String REQUIRED_USERNAME = "teste123";

    /**
     * Senha de usuário utilizada no servidor de testes de envio de e-mail.
     */
    private static final String REQUIRED_PASSWORD = "password123";

    /**
     * Corpo do e-mail de teste de mensagem HTML com imagens.
     */
    private String htmlMsgWithImage;

    /**
     * Corpo do e-mail de teste de mensagem HTML sem imagens.
     */
    private String htmlMsgWithoutImg;

    /**
     * Servidor de testes de envio de e-mails.
     */
    private Wiser wiser;

    /**
     * Preparação dos requisitos necessários para a execução do teste.
     *
     * @throws java.lang.Exception
     * @since 1.0.0 - Criada em 7 de abr de 2016
     */
    @Before
    public void setUp() throws Exception {
        this.wiser = new Wiser();
        this.wiser.setHostname( "localhost" );
        this.wiser.setPort( 8080 );

        UsernamePasswordValidator usernamePasswordValidator = new RequiredUserNameAndPasswordValidator();

        EasyAuthenticationHandlerFactory easyAuthenticationHandlerFactory = new EasyAuthenticationHandlerFactory(
                usernamePasswordValidator );

        this.wiser.getServer().setAuthenticationHandlerFactory( easyAuthenticationHandlerFactory );

        this.wiser.start();

        URL htmlMsgWithEmailUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource(
                "e-mail-html-test-com-imagem.html" );

        if ( htmlMsgWithEmailUrl == null ) {
            throw new RuntimeException( "Erro ao carregar exemplo de e-mail com conteúdo HTML." );
        }

        Path htmlMsgWithEmailPath = Paths.get( htmlMsgWithEmailUrl.toURI() );

        URL htmlMsgWithoutEmailUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource(
                "e-mail-html-test-sem-imagem.html" );

        if ( htmlMsgWithoutEmailUrl == null ) {
            throw new RuntimeException( "Erro ao carregar exemplo de e-mail com conteúdo HTML." );
        }

        Path htmlMsgWithoutEmailPath = Paths.get( htmlMsgWithoutEmailUrl.toURI() );

        this.htmlMsgWithImage = new String( Files.readAllBytes( htmlMsgWithEmailPath ) );
        this.htmlMsgWithoutImg = new String( Files.readAllBytes( htmlMsgWithoutEmailPath ) );
    }

    /**
     * Elimina todos os recursos criados para a execução do teste.
     *
     * @throws java.lang.Exception
     * @since 1.0.0 - Criada em 7 de abr de 2016
     */
    @After
    public void tearDown() throws Exception {
        this.wiser.stop();
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithoutSenderAddress() {
        new AuthenticatedEmailSender( null, "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSenderAddressEmpty() {
        new AuthenticatedEmailSender( "", "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME, REQUIRED_PASSWORD,
                false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSenderAddressBlank() {
        new AuthenticatedEmailSender( " ", "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithoutSenderName() {
        new AuthenticatedEmailSender( "bruno@teste.com", null, "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSenderNameEmpty() {
        new AuthenticatedEmailSender( "bruno@teste.com", "", "localhost", 8080, REQUIRED_USERNAME, REQUIRED_PASSWORD,
                false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSenderNameBlank() {
        new AuthenticatedEmailSender( "bruno@teste.com", " ", "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithoutSmtpServerAddress() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", null, 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerAddressEmpty() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerAddressBlank() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", " ", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerPortIncorrect1() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 0, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerPortIncorrect2() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 70000, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithoutSmtpServerLogin() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, null,
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerLoginEmpty() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, "", REQUIRED_PASSWORD,
                false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerLoginBlank() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, " ",
                REQUIRED_PASSWORD, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithoutSmtpServerSecret() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME,
                null, false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerSecretEmpty() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME, "",
                false );
    }

    /**
     * Test method for {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender}.
     */
    @Test( expected = Exception.class )
    public final void testErrorInCreationWithSmtpServerSecretBlank() {
        new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas", "localhost", 8080, REQUIRED_USERNAME, " " +
                "", false );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithoutTitle() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( null, receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithTitleEmpty() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithTitleBlank() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( " ", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithoutReceivers() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        emailSender.send( "Test-HTML-With-Img", null, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithReceiversEmpty() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver1() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( null, "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver2() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver3() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( " ", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver4() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", null );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver5() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver6() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", " " );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver7() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( null, "bruno@gmail.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver8() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( "", "bruno@gmail.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver9() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( " ", "bruno@gmail.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver10() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( "Bruno Gmail", null );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver11() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( "Bruno Gmail", "" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidReceiver12() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );
        receiversNamesAndAddresses.put( "Bruno Gmail", " " );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithoutMsg() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, null, false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithMsgEmpty() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, "", false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithMsgBlank() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, " ", false, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment1() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( "", attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment2() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( " ", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( null, attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment3() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( " ", "bruno@riteris.com" );

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", null );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment4() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( " ", "bruno@riteris.com" );

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", "" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment5() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( " ", "bruno@riteris.com" );

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", " " );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment6() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( "Teste", "" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment7() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( "Teste", " " );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment8() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( "Teste", null );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test( expected = Exception.class )
    public final void testSendWithInvalidAttachment9() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );
        attachmentsNamesAndAddresses.put( " ", attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendHtmlEmailWithImage() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false, null );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-HTML-With-Img" );
            assertTrue( message.getContent() instanceof MimeMultipart );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendHtmlEmailWithImageAndAttachment() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-HTML-With-Img", receiversNamesAndAddresses, this.htmlMsgWithImage, false,
                attachmentsNamesAndAddresses );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-HTML-With-Img" );
            assertTrue( message.getContent() instanceof MimeMultipart );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendHtmlEmailWithoutImage() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-HTML-Without-Img", receiversNamesAndAddresses, this.htmlMsgWithoutImg, false, null );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-HTML-Without-Img" );
            assertTrue( message.getContent() instanceof MimeMultipart );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendHtmlEmailWithoutImageWithAttachment() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-HTML-Without-Img", receiversNamesAndAddresses, this.htmlMsgWithoutImg, false,
                attachmentsNamesAndAddresses );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-HTML-Without-Img" );
            assertTrue( message.getContent() instanceof MimeMultipart );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendTextEmail() {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        emailSender.send( "Test-Text", receiversNamesAndAddresses, "Raw Text E-Mail", true, null );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-Text" );
            assertEquals( message.getContent(), "Raw Text E-Mail\r\n" );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.messaging.email.AuthenticatedEmailSender#send(java.lang.String, java.util.Map, java.lang.String, boolean, java.util.Map)}.
     */
    @Test
    public final void testSendTextEmailWithAttachment() throws Exception {
        AuthenticatedEmailSender emailSender = new AuthenticatedEmailSender( "bruno@teste.com", "Bruno Barauskas",
                "localhost", 8080, REQUIRED_USERNAME,
                REQUIRED_PASSWORD, false );

        Map< String, String > receiversNamesAndAddresses = new HashMap<>();
        receiversNamesAndAddresses.put( "Bruno Barauskas", "bruno@riteris.com" );

        URL attachmentUrl = AuthenticatedEmailSenderTest.class.getClassLoader().getResource( "attachment.txt" );

        if ( attachmentUrl == null ) {
            return;
        }

        Map< String, String > attachmentsNamesAndAddresses = new HashMap<>();
        attachmentsNamesAndAddresses.put( "Attachment", attachmentUrl.toURI().toString().replace( "file:", "" ) );

        emailSender.send( "Test-Text", receiversNamesAndAddresses, "Raw Text E-Mail", true,
                attachmentsNamesAndAddresses );

        try {

            assertEquals( this.wiser.getMessages().size(), 1 );

            MimeMessage message;

            message = this.wiser.getMessages().iterator().next().getMimeMessage();
            assertEquals( message.getSubject(), "Test-Text" );
            assertTrue( message.getContent() instanceof MimeMultipart );

        } catch ( Exception e ) {

            e.printStackTrace();

        }
    }

    /**
     * Implementação de verificação de login para utilização no servidor de testes de envio de e-mail.
     *
     * @author Bruno Barauskas
     * @version 1.0.0 - Criação do objeto.
     * @since 1.0.0 - Criada em 7 de abr de 2016
     */
    private class RequiredUserNameAndPasswordValidator implements UsernamePasswordValidator {

        /**
         * {@inheritDoc}
         */
        @Override
        public void login( String username, String password ) throws LoginFailedException {

            if ( stringIsNullOrEmptyOrBlank( username ) || stringIsNullOrEmpty( password ) ) {
                throw new LoginFailedException( "Usuário e Senha não informados." );
            }

            if ( !username.equals( REQUIRED_USERNAME ) || !password.equals( REQUIRED_PASSWORD ) ) {
                throw new LoginFailedException( "Usuário e Senha não informados." );
            }
        }
    }

}