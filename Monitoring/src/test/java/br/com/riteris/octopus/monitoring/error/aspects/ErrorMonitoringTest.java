package br.com.riteris.octopus.monitoring.error.aspects;

import br.com.riteris.octopus.messaging.email.IEmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Testes do objeto rastreador de erros de execução.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do teste.
 * @since 1.0.0 - Criada em 14 de abr de 2016
 */
public class ErrorMonitoringTest {

    /**
     * Objeto no qual criaremos o mock para utilização no {@link Signature} que se passará pela assinatura do método
     * invocado.
     */
    @Mock
    private Signature fakeSignature;

    /**
     * Mock do objeto que realiza o envio de e-mails, para controlarmos o resultado do teste unitário.
     */
    @Mock
    private IEmailSender fakeEmailSender;

    /**
     * {@link JoinPoint} utilizado para os testes com dados completos.
     */
    private JoinPoint completeJoinPoint;

    /**
     * {@link JoinPoint} utilizado para os testes com dados completos de um método sem argumentos.
     */
    private JoinPoint joinPointWithoutArgs;

    /**
     * {@link JoinPoint} utilizado para os testes com dados completos de um método que possui argumentos nulos.
     */
    private JoinPoint joinPointWithNullArgs;

    /**
     * {@link JoinPoint} utilizado para os testes com dados completos de um método que possui argumentos de coleção e
     * mapa vazios.
     */
    private JoinPoint joinPointWithEmptyArgs;

    /**
     * {@link JoinPoint} utilizado para os testes com dados completos de um método que possui argumentos de coleção e
     * mapa nulos em meio aos elementos
     * preenchidos.
     */
    private JoinPoint joinPointWithMixedArgs;

    /**
     * {@link Error} utilizado para os testes com dados completos.
     */
    private Throwable completeError;

    /**
     * Configuração inicial dos testes.
     *
     * @throws java.lang.Exception
     * @since 1.0.0 - Criada em 14 de abr de 2016
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks( this );

        when( this.fakeSignature.getName() ).thenReturn( "testMethod" );

        this.completeJoinPoint = new JoinPointCompleteSample( new Object(), this.fakeSignature );
        this.joinPointWithoutArgs = new JoinPointSampleWithoutArguments( new Object(), this.fakeSignature );
        this.joinPointWithNullArgs = new JoinPointSampleWithNullArguments( new Object(), this.fakeSignature );
        this.joinPointWithEmptyArgs = new JoinPointSampleWithEmptyArguments( new Object(), this.fakeSignature );
        this.joinPointWithMixedArgs = new JoinPointSampleWithMixedArguments( new Object(), this.fakeSignature );

        this.completeError = new RuntimeException( "Test Exception" );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setMailTitle(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetNullMailTitle() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setMailTitle( null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setMailTitle(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetEmptyMailTitle() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setMailTitle( "" );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setMailTitle(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetBlankMailTitle() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setMailTitle( " " );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverAddress(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetNullReceiverAddress() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverAddress( null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverAddress(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetEmptyReceiverAddress() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverAddress( "" );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverAddress(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetBlankReceiverAddress() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverAddress( " " );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverName(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetNullReceiverName() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverName( null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverName(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetEmptyReceiverName() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverName( "" );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setReceiverName(java.lang.String)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetBlankReceiverName() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setReceiverName( " " );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#setEmailSender(br.com.riteris.octopus.messaging.email.IEmailSender)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testSetNullEmailSender() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();
        errorMonitoring.setEmailSender( null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalStateException.class )
    public final void testInterceptErrorAfterThrowingWithoutConfiguringRequeriments1() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalStateException.class )
    public final void testInterceptErrorAfterThrowingWithoutConfiguringRequeriments2() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalStateException.class )
    public final void testInterceptErrorAfterThrowingWithoutConfiguringRequeriments3() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalStateException.class )
    public final void testInterceptErrorAfterThrowingWithoutConfiguringRequeriments4() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalStateException.class )
    public final void testInterceptErrorAfterThrowingWithoutConfiguringRequeriments5() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "unchecked", "rawtypes" } )
    @Test
    public final void testInterceptErrorAfterThrowingCompleteCase() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, this.completeError );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * String: Arg-01, \r\n" +
                "    * Integer: 1, \r\n" +
                "    * ArrayList: [ String: Col-Arg-01, String: Col-Arg-02, String: Col-Arg-03 ], \r\n" +
                "    * HashMap: [ Key: String: Map-Arg-Key-03 - Value: String: Map-Arg-Value-03, Key: " +
                "String: Map-Arg-Key-02 - Value: String: Map-Arg-Value-02, Key: String: Map-Arg-Key-01 - Value: " +
                "String: Map-Arg-Value-01 ].\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.RuntimeException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: Test Exception\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testInterceptErrorAfterThrowingWithoutJoinPoint() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( null, this.completeError );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @Test( expected = IllegalArgumentException.class )
    public final void testInterceptErrorAfterThrowingWithoutError() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public final void testInterceptErrorAfterThrowingWithNullPointerException() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.completeJoinPoint, new NullPointerException() );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * String: Arg-01, \r\n" +
                "    * Integer: 1, \r\n" +
                "    * ArrayList: [ String: Col-Arg-01, String: Col-Arg-02, String: Col-Arg-03 ], \r\n" +
                "    * HashMap: [ Key: String: Map-Arg-Key-03 - Value: String: Map-Arg-Value-03, Key: " +
                "String: Map-Arg-Key-02 - Value: String: Map-Arg-Value-02, Key: String: Map-Arg-Key-01 - Value: " +
                "String: Map-Arg-Value-01 ].\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.NullPointerException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: N/A\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public final void testInterceptErrorAfterThrowingWithoutArgs() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.joinPointWithoutArgs, this.completeError );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * This process don't have parameters.\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.RuntimeException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: Test Exception\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public final void testInterceptErrorAfterThrowingWithNullArgs() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.joinPointWithNullArgs, this.completeError );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * N/A: -NULL-, \r\n" +
                "    * Integer: 1, \r\n" +
                "    * N/A: -NULL-, \r\n" +
                "    * N/A: -NULL-.\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.RuntimeException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: Test Exception\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public final void testInterceptErrorAfterThrowingWithEmptyCollectionAndMapInside() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.joinPointWithEmptyArgs, new NullPointerException() );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * String: Arg-01, \r\n" +
                "    * Integer: 1, \r\n" +
                "    * ArrayList: [ -EMPTY- ], \r\n" +
                "    * HashMap: [ -EMPTY- ].\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.NullPointerException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: N/A\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Test method for
     * {@link br.com.riteris.octopus.monitoring.error.aspects.ErrorMonitoring#interceptErrorAfterThrowing(org.aspectj.lang.JoinPoint, java.lang.Throwable)}.
     */
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public final void testInterceptErrorAfterThrowingWithMixedNullAndNonNullCollectionAndMapElements() {
        ErrorMonitoring errorMonitoring = new ErrorMonitoring();

        errorMonitoring.setEmailSender( this.fakeEmailSender );
        errorMonitoring.setMailTitle( "Error Monitoring Mail Title" );
        errorMonitoring.setReceiverName( "Test Receiver" );
        errorMonitoring.setReceiverAddress( "test@receiver.com" );

        errorMonitoring.interceptErrorAfterThrowing( this.joinPointWithMixedArgs, this.completeError );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor
                        .capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * String: Arg-01, \r\n" +
                "    * Integer: 1, \r\n" +
                "    * ArrayList: [ String: Col-Arg-01, N/A: -NULL-, String: Col-Arg-03 ], \r\n" +
                "    * HashMap: [ Key: N/A: -NULL- - Value: String: Map-Arg-Value-03, Key: String: " +
                "Map-Arg-Key-02 - Value: N/A: -NULL-, Key: String: Map-Arg-Key-01 - Value: String: Map-Arg-Value-01 ]" +
                ".\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.RuntimeException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: Test Exception\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), expectedMsg );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    /**
     * Objeto que implementa a interface {@link JoinPoint} para utilização do monitor de erros e representa um objeto
     * completo, com argumentos completos e
     * preenchidos.
     */
    private class JoinPointCompleteSample implements JoinPoint {

        /**
         * Objeto fakeTarget para utilização deste {@link JoinPoint}.
         */
        private final Object target;

        /**
         * Objeto {@link Signature} para utilização deste {@link JoinPoint}.
         */
        private final Signature signature;

        /**
         * Construtor do objeto.
         *
         * @param target    Objeto que será utilizado como fakeTarget pelo JoinPoint.
         * @param signature Objeto que será utilizado como fakeSignature pelo JoinPoint.
         *
         * @since 1.0.0 - Criada em 14 de abr de 2016
         */
        public JoinPointCompleteSample( Object target, Signature signature ) {
            this.target = target;
            this.signature = signature;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toShortString() {
            return "Joinpoint de exemplo - Short";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toLongString() {
            return "Joinpoint de exemplo - Long";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getThis() {
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getTarget() {
            return this.target;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getArgs() {
            String arg1 = "Arg-01";

            Integer arg2 = 1;

            Collection< String > arg3 = new ArrayList<>();
            arg3.add( "Col-Arg-01" );
            arg3.add( "Col-Arg-02" );
            arg3.add( "Col-Arg-03" );

            Map< String, String > arg4 = new HashMap<>();
            arg4.put( "Map-Arg-Key-01", "Map-Arg-Value-01" );
            arg4.put( "Map-Arg-Key-02", "Map-Arg-Value-02" );
            arg4.put( "Map-Arg-Key-03", "Map-Arg-Value-03" );

            return new Object[]{ arg1, arg2, arg3, arg4 };
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Signature getSignature() {
            return this.signature;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SourceLocation getSourceLocation() {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getKind() {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public StaticPart getStaticPart() {
            return null;
        }

    }

    /**
     * Objeto que implementa a interface {@link JoinPoint} para utilização do monitor de erros e representa um objeto
     * completo, cujo método possui argumentos
     * nulos.
     */
    private class JoinPointSampleWithNullArguments extends JoinPointCompleteSample {

        /**
         * Construtor do objeto.
         *
         * @param target    Objeto que será utilizado como fakeTarget pelo JoinPoint.
         * @param signature Objeto que será utilizado como fakeSignature pelo JoinPoint.
         *
         * @since 1.0.0 - Criada em 14 de abr de 2016
         */
        public JoinPointSampleWithNullArguments( Object target, Signature signature ) {
            super( target, signature );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getArgs() {
            Integer arg2 = 1;

            return new Object[]{ null, arg2, null, null };
        }

    }

    /**
     * Objeto que implementa a interface {@link JoinPoint} para utilização do monitor de erros e representa um objeto
     * completo, cujo método invocado não possuia
     * argumentos.
     */
    private class JoinPointSampleWithoutArguments extends JoinPointCompleteSample {

        /**
         * Construtor do objeto.
         *
         * @param target    Objeto que será utilizado como fakeTarget pelo JoinPoint.
         * @param signature Objeto que será utilizado como fakeSignature pelo JoinPoint.
         *
         * @since 1.0.0 - Criada em 14 de abr de 2016
         */
        public JoinPointSampleWithoutArguments( Object target, Signature signature ) {
            super( target, signature );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getArgs() {
            return null;
        }

    }

    /**
     * Objeto que implementa a interface {@link JoinPoint} para utilização do monitor de erros e representa um objeto
     * completo, cujo método possui argumentos de
     * coleção e mapa vazios.
     */
    private class JoinPointSampleWithEmptyArguments extends JoinPointCompleteSample {

        /**
         * Construtor do objeto.
         *
         * @param target    Objeto que será utilizado como fakeTarget pelo JoinPoint.
         * @param signature Objeto que será utilizado como fakeSignature pelo JoinPoint.
         *
         * @since 1.0.0 - Criada em 14 de abr de 2016
         */
        public JoinPointSampleWithEmptyArguments( Object target, Signature signature ) {
            super( target, signature );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getArgs() {
            String arg1 = "Arg-01";

            Integer arg2 = 1;

            Collection< String > arg3 = new ArrayList<>();

            Map< String, String > arg4 = new HashMap<>();

            return new Object[]{ arg1, arg2, arg3, arg4 };
        }

    }

    /**
     * Objeto que implementa a interface {@link JoinPoint} para utilização do monitor de erros e representa um objeto
     * completo, cujo método possui argumentos de
     * coleção e mapas que contém elementos nulos em meio a outros elementos.
     */
    private class JoinPointSampleWithMixedArguments extends JoinPointCompleteSample {

        /**
         * Construtor do objeto.
         *
         * @param target    Objeto que será utilizado como fakeTarget pelo JoinPoint.
         * @param signature Objeto que será utilizado como fakeSignature pelo JoinPoint.
         *
         * @since 1.0.0 - Criada em 14 de abr de 2016
         */
        public JoinPointSampleWithMixedArguments( Object target, Signature signature ) {
            super( target, signature );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getArgs() {
            String arg1 = "Arg-01";

            Integer arg2 = 1;

            Collection< String > arg3 = new ArrayList<>();
            arg3.add( "Col-Arg-01" );
            arg3.add( null );
            arg3.add( "Col-Arg-03" );

            Map< String, String > arg4 = new HashMap<>();
            arg4.put( "Map-Arg-Key-01", "Map-Arg-Value-01" );
            arg4.put( "Map-Arg-Key-02", null );
            arg4.put( null, "Map-Arg-Value-03" );

            return new Object[]{ arg1, arg2, arg3, arg4 };
        }

    }

}
