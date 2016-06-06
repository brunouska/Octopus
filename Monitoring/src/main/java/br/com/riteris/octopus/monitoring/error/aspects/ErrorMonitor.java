package br.com.riteris.octopus.monitoring.error.aspects;

import org.aspectj.lang.JoinPoint;

public class ErrorMonitor {

    private IErrorHandler errorHandler;

    public void configureErrorHandler( IErrorHandler errorHandler ) {
        if ( errorHandler == null ) {
            throw new IllegalArgumentException( "The error handler object must be configured." );
        }

        this.errorHandler = errorHandler;
    }

    public final void interceptErrorAfterThrowing( final JoinPoint joinPoint, final Throwable error ) {
        if ( this.errorHandler == null ) {
            throw new IllegalStateException( "The main requeriments of this aspect wasn't configured." );
        }

        if ( joinPoint == null ) {
            throw new IllegalArgumentException( "There isn't join point informed to itercept." );
        }

        if ( error == null ) {
            throw new IllegalArgumentException( "There isn't error informed to describe." );
        }

        final ErrorInfo errorInfo = new ErrorInfo( joinPoint.getTarget(), joinPoint.getSignature(), joinPoint.getArgs(), error );

        this.errorHandler.handleError( errorInfo );

        error.printStackTrace();
    }

}