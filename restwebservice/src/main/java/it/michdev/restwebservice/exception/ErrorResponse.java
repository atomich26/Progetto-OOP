package it.michdev.restwebservice.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Questa classe rappresenta la struttura di un messaggio d'errore che viene
 * inoltrato al <code>client</code> quando si verifica a runtime un'eccezione.
 * Il messaggio contiene le informazioni riguardo il nome dell'errore con
 * relativa descrizione e l'istante in cui si è verificato.
 * 
 * @version 0.2.1
 * @since 0.2.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.exception.GlobalExceptionHandler
 */
public class ErrorResponse {

    private String name, message;
    private ZonedDateTime timeStamp;

    /**
     * Costruttore per la classe <code>ErrorResponse</code>
     * 
     * @param name    nome dell'errore riscontrato.
     * @param message descrizione dell'errore riscontrato.
     */
    public ErrorResponse(String name, String message) {
        this.name = name;
        this.message = message;
        this.timeStamp = ZonedDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("ECT")));
    }

    /**
     * Restituisce la descrizione dell'errore impostata.
     * 
     * @return Stringa della descrizione.
     * @see #setMessage(String)
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Imposta la descrizione dell'errore che si vuole mostrare all'utente.
     * 
     * @param errorMessage descrizione del'errore riscontrato.
     * @see #getMessage
     */
    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }

    /**
     * Restituisce il nome dell'errore riscontrato.
     * 
     * @return Stringa del nome.
     * @see #setName(String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Imposta il nome dell'errore riscontrato.
     * 
     * @param errorName nome dell'errore che si vuole impostare.
     * @see #getName
     */
    public void setName(String errorName) {
        this.name = errorName;
    }

    /**
     * Restituisce la data e l'ora in cui è stato generato l'errore.
     * 
     * @return Stringa della data.
     * @see #setTimeStamp(String)
     */
    public String getTimestamp() {
        return this.timeStamp.toString();
    }

    /**
     * Imposta la data e l'ora in cui si è verificato l'errore.
     * 
     * @param timeStamp data e ora che si vuole impostare passati come oggetto
     *                  <code>ZonedTime</code>.
     * @see #getTimeStamp
     */
    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
