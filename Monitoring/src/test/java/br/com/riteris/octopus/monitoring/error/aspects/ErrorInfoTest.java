package br.com.riteris.octopus.monitoring.error.aspects;

import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.com.riteris.octopus.utils.DateTools.formatDateToPattern;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ErrorInfoTest {

    @Mock
    private Signature fakeSignature;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks( this );
        when( this.fakeSignature.getName() ).thenReturn( "testMethod" );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testCreatingNewErrorInfoWithNullTarget() {
        new ErrorInfo( null, new Signature() {
            @Override
            public String toShortString() {
                return null;
            }

            @Override
            public String toLongString() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public int getModifiers() {
                return 0;
            }

            @Override
            public Class getDeclaringType() {
                return null;
            }

            @Override
            public String getDeclaringTypeName() {
                return null;
            }
        }, new Object[]{}, new RuntimeException() );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testCreatingNewErrorInfoWithNullSignature() {
        new ErrorInfo( new Object(), null, new Object[]{}, new RuntimeException() );
    }

    @Test( expected = IllegalArgumentException.class )
    public final void testCreatingNewErrorInfoWithNullError() {
        new ErrorInfo( new Object(), new Signature() {
            @Override
            public String toShortString() {
                return null;
            }

            @Override
            public String toLongString() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public int getModifiers() {
                return 0;
            }

            @Override
            public Class getDeclaringType() {
                return null;
            }

            @Override
            public String getDeclaringTypeName() {
                return null;
            }
        }, new Object[]{}, null );
    }

    @Test
    public final void testCreatingNewErrorInfoWithNullArgs() {
        final ErrorInfo errorInfo = new ErrorInfo( new Object(), this.fakeSignature, null, new RuntimeException( "Test Exception" ) );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Error Date: " + formatDateToPattern( LocalDateTime.now(), "dd/MM/yyyy - HH:mm" ) + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Object Name: java.lang.Object\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Name: testMethod\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Process Parameters: \r\n" +
                "    * -EMPTY-\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Object: java.lang.RuntimeException\r\n" +
                "-------------------------------------------------------------\r\n" +
                "Error Menssage: Test Exception\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        assertEquals( expectedMsg, errorInfo.toString() );
    }

    @Test
    public final void testCreatingNewErrorInfo() {
        final String arg1 = "Arg-01";
        final Integer arg2 = 1;
        final Collection< String > arg3 = new ArrayList<>();
        arg3.add( "Col-Arg-01" );
        arg3.add( "Col-Arg-02" );
        arg3.add( "Col-Arg-03" );
        final Map< String, String > arg4 = new HashMap<>();
        arg4.put( "Map-Arg-Key-01", "Map-Arg-Value-01" );
        arg4.put( "Map-Arg-Key-02", "Map-Arg-Value-02" );
        arg4.put( "Map-Arg-Key-03", "Map-Arg-Value-03" );

        final Object[] objectsArray = new Object[]{ arg1, arg2, arg3, arg4 };

        final ErrorInfo errorInfo = new ErrorInfo( new Object(), this.fakeSignature, objectsArray, new RuntimeException( "Test Exception" ) );

        final String expectedMsg = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<\r\n" +
                "Error Date: " + formatDateToPattern( LocalDateTime.now(), "dd/MM/yyyy - HH:mm" ) + "\r\n" +
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

        assertEquals( expectedMsg, errorInfo.toString() );
    }


}
