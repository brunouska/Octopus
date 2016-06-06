package br.com.riteris.octopus.monitoring.error.aspects;

import org.aspectj.lang.Signature;

import java.time.LocalDateTime;
import java.util.Arrays;

import static br.com.riteris.octopus.utils.DateTools.formatDateToPattern;
import static br.com.riteris.octopus.utils.ObjectTools.describeObjectsCollection;

public class ErrorInfo {

    private LocalDateTime errorDate;

    private String objectName;

    private String processName;

    private String processParameters;

    private String errorObjectName;

    private String errorMessage;

    public ErrorInfo( final Object target, final Signature signature, final Object[] args, final Throwable error ) {
        if ( target == null ) {
            throw new IllegalArgumentException( "The target object can't be null." );
        }

        if ( signature == null ) {
            throw new IllegalArgumentException( "The method signature can't be null." );
        }

        if ( error == null ) {
            throw new IllegalArgumentException( "The error object can't be null." );
        }

        this.errorDate = LocalDateTime.now();
        this.objectName = target.getClass().getCanonicalName();
        this.processName = signature.getName();
        this.errorObjectName = error.getClass().getCanonicalName();
        this.errorMessage = error.getMessage() != null ? error.getMessage() : "N/A";

        if ( args != null ) {
            this.processParameters = describeObjectsCollection( Arrays.asList( args ) );
        } else {
            this.processParameters = describeObjectsCollection( null );
        }
    }

    @Override
    public String toString() {
        final String result = ">>>>>>>>>>>>>>>>>> EXECUTION ERROR DETAILS <<<<<<<<<<<<<<<<<<" + "\r\n" +
                "Error Date: " + formatDateToPattern( this.errorDate, "dd/MM/yyyy - HH:mm" ) + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Object Name: " + this.objectName + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Process Name: " + this.processName + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Process Parameters: " + "\r\n" + this.processParameters + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Error Object: " + this.errorObjectName + "\r\n" +
                "-------------------------------------------------------------" + "\r\n" +
                "Error Menssage: " + this.errorMessage + "\r\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

        return result;
    }

}