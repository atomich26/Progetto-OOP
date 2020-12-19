package it.michdev.restwebservice.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Questa classe rappresenta il componente <code>ControllerAdvice</code> per
 * l'applicazione Spring. Controlla e gestisce le eccezioni che si verificano a
 * runtime, inviando all'utente un oggetto <code>ErrorResponse</code> con le
 * informazioni sull'eccezione generata.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.ErrorResponse
 */
@ControllerAdvice
public final class GlobalExceptionHandler {

    /**
     * Questo metodo gestisce l'eccezione <code>IOException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return oggetto <code>ResponseEntity</code> di <code>ErrorResponse</code>
     */
    @ExceptionHandler({ IOException.class, FileNotFoundException.class })

    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("InternalServerError",
                "Si è verificato un errore interno del server. Contatta l'amministratore per ulteriori informazioni."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>CurrencyNotFoundException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return <code>ResponseEntity</code> di <code>ErrorResponse</code>
     * @see it.michdev.restwebservice.exception.CurrencyNotFoundException
     */
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("CurrencyNotFoundException", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>InvalidPeriodException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return <code>ResponseEntity</code> di<code>ErrorResponse</code>
     * @see it.michdev.restwebservice.exception.InvalidPeriodException
     */
    @ExceptionHandler(InvalidPeriodException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPeriodException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("InvalidPeriodException", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>IllegalDatePatternException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return <code>ResponseEntity</code> di <code>ErrorResponse</code>
     * @see it.michdev.restwebservice.exception.IllegalDatePatternException
     */
    @ExceptionHandler(IllegalDatePatternException.class)
    public ResponseEntity<ErrorResponse> handleIllegalDatePatternException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("IllegalDatePatternException", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>DataNotFoundException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return <code>ResponseEntity</code> di <code>ErrorResponse</code>
     * @see it.michdev.restwebservice.exception.DataNotFoundException
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("DataNotFoundException", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>InvalidStatsFieldException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return <code>ResponseEntity</code> di <code>ErrorResponse</code>
     * @see it.michdev.restwebservice.exception.InvalidStatsFieldException
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatsFieldException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("InvalidStatsFieldException", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
