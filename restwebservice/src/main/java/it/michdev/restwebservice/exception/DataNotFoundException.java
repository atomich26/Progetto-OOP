package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>DataNotFoundException</b> estende <b>Exception</b>. Essa viene
 * generata quando le richieste alle <i>fxmarketapi.com</i> non restituiscono
 * dati validi per l'elaborazione delle statistiche. L'eccezione è gestita dalla classe
 * <b>GlobalExceptionHandler</b>.
 * 
 * @version 1.1.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
 */
public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>DataNotFoundException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
