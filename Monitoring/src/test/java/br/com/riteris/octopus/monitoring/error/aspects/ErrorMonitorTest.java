package br.com.riteris.octopus.monitoring.error.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ErrorMonitorTest {

    @Mock
    private Signature fakeSignature;

    @Mock
    private IErrorHandler fakeErrorHandler;

    private JoinPoint fakeJoinPoint;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks( this );
        when( this.fakeSignature.getName() ).thenReturn( "testMethod" );

        this.fakeJoinPoint = new JoinPointCompleteSample( new Object(), this.fakeSignature );
    }

    @Test( expected = IllegalArgumentException.class )
    public void configuringErrorMonitorWithNullErrorHandler() {
        final ErrorMonitor errorMonitor = new ErrorMonitor();
        errorMonitor.configureErrorHandler( null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testInterceptErrorAfterThrowingWithNullJoinPoint() {
        final ErrorMonitor errorMonitor = new ErrorMonitor();
        errorMonitor.configureErrorHandler( this.fakeErrorHandler );

        errorMonitor.interceptErrorAfterThrowing( null, new RuntimeException( "Test Exception" ) );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testInterceptErrorAfterThrowingWithNullError() {
        final ErrorMonitor errorMonitor = new ErrorMonitor();
        errorMonitor.configureErrorHandler( this.fakeErrorHandler );

        errorMonitor.interceptErrorAfterThrowing( this.fakeJoinPoint, null );
    }

    @Test
    public final void testInterceptErrorAfterThrowing() {
        ErrorMonitor errorMonitor = new ErrorMonitor();
        errorMonitor.configureErrorHandler( this.fakeErrorHandler );

        errorMonitor.interceptErrorAfterThrowing( this.fakeJoinPoint, new RuntimeException( "Test Exception" ) );

        ArgumentCaptor< ErrorInfo > errorInfoArgumentCaptor = ArgumentCaptor.forClass( ErrorInfo.class );

        verify( this.fakeErrorHandler, times( 1 ) ).handleError( errorInfoArgumentCaptor.capture() );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Error Date: " + DateTimeFormatter.ofPattern( "dd/MM/yy HH:mm" ).format( LocalDateTime.now() ) + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
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

        assertNotNull( errorInfoArgumentCaptor.getValue() );
        assertEquals( errorInfoArgumentCaptor.getValue().toString(), expectedMsg );
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