package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>InvalidStatsFieldException</b> estende <b>Exception</b>. Essa
 * viene generata quando il campo del valore di una quotazione di una serie
 * storica non esiste. L'eccezione è gestita dalla classe
 * <b>GlobalExceptionHandler</b>.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
 */
public class InvalidStatsFieldException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>InvalidStatsFieldException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public InvalidStatsFieldException(String message) {
        super(message);
    }
}
