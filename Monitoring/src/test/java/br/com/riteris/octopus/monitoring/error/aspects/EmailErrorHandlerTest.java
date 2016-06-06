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

public class EmailErrorHandlerTest {

    @Mock
    private Signature fakeSignature;

    @Mock
    private IEmailSender fakeEmailSender;

    private ErrorInfo errorInfo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks( this );
        when( this.fakeSignature.getName() ).thenReturn( "testMethod" );

        this.errorInfo = new ErrorInfo( new Object(), this.fakeSignature, null, new RuntimeException( "Test Exception" ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithNullMailTitle() {
        new EmailErrorHandler( null, "teste@teste.com.br", "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithEmptyMailTitle() {
        new EmailErrorHandler( "", "teste@teste.com.br", "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithBlankMailTitle() {
        new EmailErrorHandler( " ", "teste@teste.com.br", "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithNullReceiverAddress() {
        new EmailErrorHandler( "Teste", null, "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithEmptyReceiverAddress() {
        new EmailErrorHandler( "Teste", "", "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithBlankReceiverAddress() {
        new EmailErrorHandler( "Teste", " ", "Teste", this.fakeEmailSender );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithNullEmailSender() {
        new EmailErrorHandler( "Teste", "teste@teste.com.br", "Teste", null );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testBuildingEmailErrorHandlerWithNullErrorInfo() {
        final EmailErrorHandler emailErrorHandler = new EmailErrorHandler( "Teste", "teste@teste.com.br", "Teste", this.fakeEmailSender );
        emailErrorHandler.handleError( null );
    }

    @Test
    public final void testHandleError() {
        final ErrorInfo errorInfo = new ErrorInfo( new Object(), this.fakeSignature, null, new RuntimeException( "Test Exception" ) );

        final EmailErrorHandler emailErrorHandler = new EmailErrorHandler( "Error Monitoring Mail Title", "test@receiver.com", "Test Receiver", this.fakeEmailSender );

        emailErrorHandler.handleError( errorInfo );

        ArgumentCaptor< String > mailTitleCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Map > receiversNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );
        ArgumentCaptor< String > mailMessageCaptor = ArgumentCaptor.forClass( String.class );
        ArgumentCaptor< Boolean > rawTextFormatCaptor = ArgumentCaptor.forClass( Boolean.class );
        ArgumentCaptor< Map > attachmentsNamesAndAddressesCaptor = ArgumentCaptor.forClass( Map.class );

        verify( this.fakeEmailSender, times( 1 ) ).send( mailTitleCaptor.capture(), receiversNamesAndAddressesCaptor.capture(), mailMessageCaptor.capture(),
                rawTextFormatCaptor.capture(), attachmentsNamesAndAddressesCaptor.capture() );

        Map< String, String > expectedReceivers = new HashMap<>();
        expectedReceivers.put( "Test Receiver", "test@receiver.com" );

        assertEquals( mailTitleCaptor.getValue(), "Error Monitoring Mail Title" );
        assertEquals( receiversNamesAndAddressesCaptor.getValue(), expectedReceivers );
        assertEquals( mailMessageCaptor.getValue(), errorInfo.toString() );
        assertEquals( rawTextFormatCaptor.getValue(), true );
        assertEquals( attachmentsNamesAndAddressesCaptor.getValue(), null );
    }

    private class JoinPointCompleteSample implements JoinPoint {

        private final Object target;

        private final Signature signature;

        public JoinPointCompleteSample( Object target, Signature signature ) {
            this.target = target;
            this.signature = signature;
        }

        @Override
        public String toShortString() {
            return "Joinpoint de exemplo - Short";
        }

        @Override
        public String toLongString() {
            return "Joinpoint de exemplo - Long";
        }

        @Override
        public Object getThis() {
            return this;
        }

        @Override
        public Object getTarget() {
            return this.target;
        }

        @Override
        public Object[] getArgs() {
            Collection< String > arg3 = new ArrayList<>();
            arg3.add( "Col-Arg-01" );
            arg3.add( "Col-Arg-02" );
            arg3.add( "Col-Arg-03" );

            Map< String, String > arg4 = new HashMap<>();
            arg4.put( "Map-Arg-Key-01", "Map-Arg-Value-01" );
            arg4.put( "Map-Arg-Key-02", "Map-Arg-Value-02" );
            arg4.put( "Map-Arg-Key-03", "Map-Arg-Value-03" );

            return new Object[]{ "Arg-01", new Integer( 1 ), arg3, arg4 };
        }

        @Override
        public Signature getSignature() {
            return this.signature;
        }

        @Override
        public SourceLocation getSourceLocation() {
            return null;
        }

        @Override
        public String getKind() {
            return null;
        }

        @Override
        public StaticPart getStaticPart() {
            return null;
        }

    }

}