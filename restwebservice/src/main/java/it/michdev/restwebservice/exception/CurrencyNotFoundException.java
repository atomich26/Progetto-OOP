package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>CurrencyNotFoundException</b> estende <b>Exception</b>. Essa
 * viene generata quando la valuta scelta dall'utente non è disponibile
 * all'intero dell'elenco nel file <i>assets.json</i>. L'eccezione è gestita
 * dalla classe <b>GlobalExceptionHandler</b>.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.component.GlobalExceptionHandler
 */
public class CurrencyNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>CurrencyNotFoundException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
