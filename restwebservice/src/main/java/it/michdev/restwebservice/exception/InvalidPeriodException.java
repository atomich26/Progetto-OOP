package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>InvalidPeriodException</b> estende <b>Exception</b>. Essa
 * viene generata quando il periodo di tempo stabilito nel <code>body</code>
 * della richiesta <code>POST</code> non è corretto. L'eccezione è gestita dalla
 * classe <b>GlobalExceptionHandler</b>.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.component.GlobalExceptionHandler
 */
public class InvalidPeriodException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>CurrencyNotFoundException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public InvalidPeriodException(String message) {
        super(message);
    }
}
