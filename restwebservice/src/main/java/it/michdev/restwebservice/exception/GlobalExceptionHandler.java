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
 * @version 0.2.1
 * @since 0.2.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.ErrorResponse
 */
@ControllerAdvice
public final class GlobalExceptionHandler {

    /**
     * Questo metodo gestisce l'eccezione <code>IOException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return oggetto <code>ResponseEntity<ErrorResponse></code> con le
     *         informazioni sull'eccezione generata.
     * @see it.michdev.restwebservice.exception.InternalServerException
     */
    @ExceptionHandler({IOException.class, FileNotFoundException.class})

    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("Errore interno del server",
                "Si è verificato un errore interno del server. Contatta l'amministratore per ulteriori informazioni."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>CurrencyNotFoundException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return oggetto <code>ResponseEntity<ErrorResponse></code> con le
     *         informazioni sull'eccezione generata.
     */
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyNotFoundException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse("Valuta non trovata",
                        "Impossibile richiedere dati per la valuta:" + e.getMessage() + ". Consulta la documentazione"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Questo metodo gestisce l'eccezione <code>InvalidPeriodException</code>.
     * 
     * @param e eccezione generata che si vuole gestire.
     * @return oggetto <code>ResponseEntity<ErrorResponse></code> con le
     *         informazioni sull'eccezione generata.
     */
    @ExceptionHandler(InvalidPeriodException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPeriodException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("Data inserita non valida", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
