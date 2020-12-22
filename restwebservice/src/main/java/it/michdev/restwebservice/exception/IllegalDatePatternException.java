package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>IllegalDatePatternException</b> estende <b>Exception</b>. Essa
 * viene generata quando la data da convertire in oggetto <code>Calendar</code>
 * non si presenta nel formato accettato. L'eccezione è gestita dalla classe
 * <b>GlobalExceptionHandler</b>. Per visualizzare la struttura corretta delle
 * date consulta la documentazione.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
 */
public class IllegalDatePatternException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>IllegaldDatePatternException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public IllegalDatePatternException(String message) {
        super(message);
    }
}
