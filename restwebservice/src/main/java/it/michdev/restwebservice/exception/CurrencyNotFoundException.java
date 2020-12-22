package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>CurrencyNotFoundException</b> estende <b>Exception</b>. Essa
 * viene generata quando la valuta scelta dall'utente non è disponibile
 * all'interno dell'elenco nel file <i>assets.json</i>. L'eccezione è gestita
 * dalla classe <b>GlobalExceptionHandler</b>.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
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
