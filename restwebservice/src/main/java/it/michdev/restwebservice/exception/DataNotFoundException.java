package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>DataNotFoundException</b> estende <b>Exception</b>. Essa
 * viene generata quando la valuta scelta dall'utente non è disponibile
 * all'intero dell'elenco nel file <i>assets.json</i>. L'eccezione è gestita
 * dalla classe <b>GlobalExceptionHandler</b>.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.component.GlobalExceptionHandler
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
