package it.michdev.restwebservice.exception;

/**
 * L'eccezione <b>InvalidBodyException</b> estende <b>Exception</b>. Essa viene
 * generata quando il <code>body</code> delle richieste di tipo
 * <code>POST</code> è errato, quindi non contenente i campi predefiniti o
 * valori accettabili. L'eccezione è gestita dalla classe
 * <b>GlobalExceptionHandler</b>.
 * Per visualizzare la struttura corretta del <code>body</code> consulta la documentazione.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.component.GlobalExceptionHandler
 */
public class IllegalDatePatternException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore per la classe <b>InvalidBodyException</b>
     * 
     * @param message descrizione dell'eccezione generata.
     */
    public IllegalDatePatternException(String message) {
        super(message);
    }
}
